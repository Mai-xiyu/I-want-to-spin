package org.xiyu.yee.datl.neoforge.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import org.xiyu.yee.datl.Datl;
import org.xiyu.yee.datl.config.DatlConfig;
import org.xiyu.yee.datl.client.ClientTickHandler;

@EventBusSubscriber(modid = Datl.MOD_ID)
public class KeyBindings {
    private static KeyMapping spinToggleKey;
    private static KeyMapping bhopToggleKey;
    private static KeyMapping speedIncreaseKey;
    private static KeyMapping speedDecreaseKey;
    
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
    
    @EventBusSubscriber(modid = Datl.MOD_ID)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            handleKeyInputs();
            ClientTickHandler.onClientTick();
        }
    }
    
    private static void handleKeyInputs() {
        Minecraft client = Minecraft.getInstance();
        DatlConfig config = DatlConfig.getInstance();
        if (spinToggleKey.consumeClick()) {
            config.toggleSpin();
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "旋转陀螺: " + (config.isSpinEnabled() ? "开启" : "关闭")
                ), false);
            }
        }
        if (bhopToggleKey.consumeClick()) {
            config.toggleBhop();
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "连跳: " + (config.isBhopEnabled() ? "开启" : "关闭")
                ), false);
            }
        }
        if (speedIncreaseKey.consumeClick()) {
            float newSpeed = Math.min(config.getSpinSpeed() + 0.5f, 100.0f);
            config.setSpinSpeed(newSpeed);
            if (client.player != null) {
                client.player.displayClientMessage(Component.literal(
                    "旋转速度: " + String.format("%.1f", newSpeed)
                ), false);
            }
        }
        if (speedDecreaseKey.consumeClick()) {
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
