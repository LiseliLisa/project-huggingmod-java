package liselilisa.huggingmod.client.network;

import liselilisa.huggingmod.network.payload.HugStatePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.chat.Component;

public class HugClientNetworking {

    public static void register() {

        ClientPlayNetworking.registerGlobalReceiver(
                HugStatePayload.TYPE,
                (payload, context) -> {

                    var client = context.client();

                    if (client.player == null) {
                        return;
                    }

                    int giverId = payload.giverId();
                    int receiverId = payload.receiverId();
                    int state = payload.state();

                }
        );
    }
}
