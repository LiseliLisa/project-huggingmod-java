package liselilisa.huggingmod.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import liselilisa.huggingmod.Huggingmod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

public class HugKeybinds {
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(
                    Huggingmod.id("huggingmod")
            );
    private static KeyMapping huggingKey;

    public static void register(){
        huggingKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.huggingmod.huggingKey",
                        InputConstants.Type.KEYBOARD,
                        InputConstants.KEY_H,
                        CATEGORY
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (huggingKey.consumeClick()){
                if (client.player == null) return;

                client.player.sendSystemMessage(
                        Component.literal("[HuggingMod] Hugging button pressed")
                );
            }
        });
    }
}
