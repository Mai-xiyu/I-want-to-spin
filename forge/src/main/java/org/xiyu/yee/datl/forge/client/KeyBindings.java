package org.xiyu.yee.datl.forge.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.config.DatlConfig;
import org.xiyu.yee.datl.client.ClientTickHandler;

public class KeyBindings {
    private static KeyMapping spinToggleKey;
    private static KeyMapping bhopToggleKey;
    private static KeyMapping speedIncreaseKey;
    private static KeyMapping speedDecreaseKey;
    
    @Mod.EventBusSubscriber(modid = Datl.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
            spinToggleKey = new KeyMapping(
                "key.datl.spin_toggle",
                GLFW.GLFW_KEY_R,
                "category.datl.keys"
            );
            
            bhopToggleKey = new KeyMapping(
                "key.datl.bhop_toggle", 
                GLFW.GLFW_KEY_B,
                "category.datl.keys"
            );
            
            speedIncreaseKey = new KeyMapping(
                "key.datl.speed_increase",
                GLFW.GLFW_KEY_KP_ADD,
                "category.datl.keys"
            );
            
            speedDecreaseKey = new KeyMapping(
                "key.datl.speed_decrease",
                GLFW.GLFW_KEY_KP_SUBTRACT,
                "category.datl.keys"
            );
            
            event.register(spinToggleKey);
            event.register(bhopToggleKey);
            event.register(speedIncreaseKey);
            event.register(speedDecreaseKey);
        }
    }
    
    @Mod.EventBusSubscriber(modid = Datl.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                handleKeyInputs();
                ClientTickHandler.onClientTick();
            }
        }
    }
    
    private static void handleKeyInputs() {
        Minecraft client = Minecraft.getInstance();
        DatlConfig config = DatlConfig.getInstance();
        
        // 处理旋转切换
        if (spinToggleKey != null && spinToggleKey.consumeClick()) {
            config.toggleSpin();
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "旋转陀螺: " + (config.isSpinEnabled() ? "开启" : "关闭")
                ), false);
            }
        }
        
        // 处理连跳切换
        if (bhopToggleKey != null && bhopToggleKey.consumeClick()) {
            config.toggleBhop();
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "连跳: " + (config.isBhopEnabled() ? "开启" : "关闭")
                ), false);
            }
        }
        
        // 处理速度调整
        if (speedIncreaseKey != null && speedIncreaseKey.consumeClick()) {
            float newSpeed = Math.min(config.getSpinSpeed() + 0.5f, 10.0f);
            config.setSpinSpeed(newSpeed);
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "旋转速度: " + String.format("%.1f", newSpeed)
                ), false);
            }
        }
        
        if (speedDecreaseKey != null && speedDecreaseKey.consumeClick()) {
            float newSpeed = Math.max(config.getSpinSpeed() - 0.5f, 0.5f);
            config.setSpinSpeed(newSpeed);
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "旋转速度: " + String.format("%.1f", newSpeed)
                ), false);
            }
        }
    }
}
