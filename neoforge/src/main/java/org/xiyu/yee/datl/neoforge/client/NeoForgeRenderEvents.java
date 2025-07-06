package org.xiyu.yee.datl.neoforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.client.PlayerRenderHandler;

@EventBusSubscriber(modid = Datl.MOD_ID)
public class NeoForgeRenderEvents {
    
    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        // 在渲染前应用旋转效果
        Player player = Minecraft.getInstance().player;
        PlayerRenderHandler.onRenderPlayer(player, event.getPoseStack());
    }
}
