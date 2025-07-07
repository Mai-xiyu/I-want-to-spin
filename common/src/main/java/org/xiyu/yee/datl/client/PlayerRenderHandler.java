package org.xiyu.yee.datl.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import org.xiyu.yee.datl.config.DatlConfig;

public class PlayerRenderHandler {
    
    /**
     * 在渲染玩家时应用旋转效果
     * 这个方法应该在玩家渲染事件中调用
     */
    public static void onRenderPlayer(PoseStack poseStack) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null) {
            DatlConfig config = DatlConfig.getInstance();
            if (config.isSpinEnabled()) {
                // 只在第三人称视角中应用旋转
                if (!mc.options.getCameraType().isFirstPerson() && poseStack != null) {
                    float spinRotation = ClientTickHandler.getCurrentSpinRotation();
                    poseStack.mulPose(Axis.YP.rotationDegrees(spinRotation));
                }
            }
        }
    }
    
    /**
     * 在渲染玩家时应用旋转效果 (带 Player 参数的版本)
     */
    public static void onRenderPlayer(Player player, PoseStack poseStack) {
        // 只对本地玩家应用旋转效果
        if (player == Minecraft.getInstance().player) {
            onRenderPlayer(poseStack);
        }
    }

}
