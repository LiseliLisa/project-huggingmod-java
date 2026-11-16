package liselilisa.huggingmod.network;

import liselilisa.huggingmod.hug.HugManager;
import liselilisa.huggingmod.network.payload.HugAttemptPayload;
import liselilisa.huggingmod.network.payload.HugStatePayload;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class HugNetworking {

    public static void register() {

        // Register client-to-server packet
        PayloadTypeRegistry.serverboundPlay().register(
                HugAttemptPayload.TYPE,
                HugAttemptPayload.CODEC
        );

        // Register server-to-client packet
        PayloadTypeRegistry.clientboundPlay().register(
                HugStatePayload.TYPE,
                HugStatePayload.CODEC
        );

        // Handle H key requests from players
        ServerPlayNetworking.registerGlobalReceiver(
                HugAttemptPayload.TYPE,
                (payload, context) -> {

                    ServerPlayer player = context.player();

                    // Implement this method in HugManager.
                    HugManager.handleAttempt(
                            player,
                            payload.targetEntityId()
                    );
                }
        );
    }

    public static void broadcastState(
            ServerPlayer giver,
            ServerPlayer receiver,
            int state
    ) {

        HugStatePayload payload =
                new HugStatePayload(
                        giver.getId(),
                        receiver.getId(),
                        state
                );

        ServerLevel level = (ServerLevel) giver.level();

        for (ServerPlayer viewer : PlayerLookup.level(level)) {
            if (ServerPlayNetworking.canSend(
                    viewer, HugStatePayload.TYPE
            )) {
                ServerPlayNetworking.send(viewer, payload);
            }
        }
    }
}
