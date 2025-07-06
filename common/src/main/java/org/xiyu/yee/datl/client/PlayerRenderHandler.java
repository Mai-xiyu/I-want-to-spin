package org.xiyu.yee.datl.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.xiyu.yee.datl.config.DatlConfig;

public class PlayerRenderHandler {
    
    /**
     * 在渲染玩家时应用旋转效果
     * 这个方法应该在玩家渲染事件中调用
     */
    public static void onRenderPlayer(Player player, PoseStack poseStack) {
        if (player == Minecraft.getInstance().player) {
            DatlConfig config = DatlConfig.getInstance();
            if (config.isSpinEnabled()) {
                // 只在第三人称视角或其他玩家视角中应用旋转
                if (!Minecraft.getInstance().options.getCameraType().isFirstPerson()) {
                    float spinRotation = ClientTickHandler.getCurrentSpinRotation();
                    poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(spinRotation));
                }
            }
        }
    }

}
