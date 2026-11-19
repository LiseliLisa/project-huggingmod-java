package liselilisa.huggingmod.client.network;

import liselilisa.huggingmod.client.hug.HugClientState;
import liselilisa.huggingmod.network.payload.HugAttemptPayload;
import liselilisa.huggingmod.network.payload.HugStatePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class HugClientNetworking {

    public static void register() {

        ClientPlayNetworking.registerGlobalReceiver(
                HugStatePayload.TYPE,
                (payload, context) -> {

                    var client = context.client();

                    HugClientState.update(payload,client.player.getId());
                    ClientPlayConnectionEvents.DISCONNECT.register(
                            (handler, minecraftClient) -> HugClientState.clear()
                    );


                    if (client.player == null) {
                        return;
                    }

                    int giverId = payload.giverId();
                    int receiverId = payload.receiverId();
                    int state = payload.state();

                }
        );
    }

    public static void sendHugAttempt(int targetId){
        if (targetId != 0 && ClientPlayNetworking.canSend(HugAttemptPayload.TYPE)){
            ClientPlayNetworking.send(
                    new HugAttemptPayload(targetId)
            );
        }
    }

}
