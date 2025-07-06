package org.xiyu.yee.datl.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import org.xiyu.yee.datl.config.DatlConfig;

public class ClientTickHandler {
    private static float currentSpinRotation = 0.0f;
    private static boolean wasOnGround = false;
    private static int bhopCooldown = 0;
    private static float originalYaw = 0.0f;
    private static boolean spinningStarted = false;
    
    public static void onClientTick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        
        Player player = mc.player;
        DatlConfig config = DatlConfig.getInstance();
        
        // 处理旋转陀螺
        if (config.isSpinEnabled()) {
            if (!spinningStarted) {
                // 记录开始旋转时的原始yaw
                originalYaw = player.getYRot();
                spinningStarted = true;
            }
            
            // 更新旋转角度
            currentSpinRotation = (currentSpinRotation + config.getSpinSpeed()) % 360;
            
            // 发送旋转数据包到服务器（让其他玩家看到旋转）
            sendRotationPacket(player, currentSpinRotation);
        } else {
            if (spinningStarted) {
                // 停止旋转时恢复原始视角
                sendRotationPacket(player, originalYaw);
                spinningStarted = false;
                currentSpinRotation = 0.0f;
            }
        }
        
        // 处理连跳
        if (config.isBhopEnabled()) {
            handleBhop(player);
        }
        
        // 减少冷却时间
        if (bhopCooldown > 0) {
            bhopCooldown--;
        }
    }
    
    private static void sendRotationPacket(Player player, float yaw) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getConnection() != null) {
            // 发送旋转数据包，但不影响客户端视角
            mc.getConnection().send(new ServerboundMovePlayerPacket.Rot(
                yaw,
                player.getXRot(),
                player.onGround(),
                player.isShiftKeyDown()
            ));
        }
    }
    
    private static void handleBhop(Player player) {
        boolean isOnGround = player.onGround();
        
        // 检测刚着地的时刻
        if (isOnGround && !wasOnGround && bhopCooldown == 0) {
            // 检查玩家是否在移动
            double horizontalSpeed = Math.sqrt(
                player.getDeltaMovement().x * player.getDeltaMovement().x + 
                player.getDeltaMovement().z * player.getDeltaMovement().z
            );
            
            if (horizontalSpeed > 0.1) {
                // 给予跳跃冲量
                player.jumpFromGround();
                bhopCooldown = 5; // 设置冷却时间防止过于频繁
            }
        }
        
        wasOnGround = isOnGround;
    }
    
    public static float getCurrentSpinRotation() {
        return currentSpinRotation;
    }

}
