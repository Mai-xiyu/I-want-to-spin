package org.xiyu.yee.datl.fabric.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import org.xiyu.yee.datl.config.DatlConfig;
import org.xiyu.yee.datl.client.ClientTickHandler;

public class KeyBindings {
    private static KeyMapping spinToggleKey;
    private static KeyMapping bhopToggleKey;
    private static KeyMapping speedIncreaseKey;
    private static KeyMapping speedDecreaseKey;
    
    public static void register() {
        // 注册按键绑定
        spinToggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.datl.spin_toggle",
            GLFW.GLFW_KEY_R,
            "category.datl.keys"
        ));
        
        bhopToggleKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.datl.bhop_toggle", 
            GLFW.GLFW_KEY_B,
            "category.datl.keys"
        ));
        
        speedIncreaseKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.datl.speed_increase",
            GLFW.GLFW_KEY_KP_ADD,
            "category.datl.keys"
        ));
        
        speedDecreaseKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.datl.speed_decrease",
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "category.datl.keys"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            handleKeyInputs(client);
            ClientTickHandler.onClientTick();
        });
    }
    
    private static void handleKeyInputs(Minecraft client) {
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
