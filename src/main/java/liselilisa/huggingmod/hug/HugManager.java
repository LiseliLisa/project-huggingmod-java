package liselilisa.huggingmod.hug;

import liselilisa.huggingmod.config.HugConfigManager;
import liselilisa.huggingmod.network.HugNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HugManager {

    private static final List<HugSession> ACTIVE = new ArrayList<>();

    private static final double MAX_DISTANCE = HugConfigManager.hugDistance;


    public static void handleAttempt(
            ServerPlayer player,
            int targetEntityId
    ) {

        // Check if the player is already being hugged.
        for (HugSession session : ACTIVE) {

            if (session.receiver == player) {

                // Return the hug.
                if (!session.mutual
                        && isNear(session.giver, player)) {

                    session.mutual = true;

                    HugNetworking.broadcastState(
                            session.giver,
                            session.receiver,
                            2
                    );
                }

                return;
            }

            // Already giving a hug.
            if (session.giver == player) {
                return;
            }
        }

        // Find the targeted entity.
        Entity entity = player.level().getEntity(targetEntityId);

        if (!(entity instanceof ServerPlayer target)) {
            return;
        }

        if (target == player || !isNear(player, target)) {
            return;
        }

        // Avoid overlapping hug sessions.
        for (HugSession session : ACTIVE) {
            if (session.giver == target
                    || session.receiver == target) {
                return;
            }
        }

        // Create a one-sided hug.
        HugSession session = new HugSession(player, target);
        ACTIVE.add(session);

        HugNetworking.broadcastState(player, target, 1);
    }

    public static void tick(MinecraftServer server) {

        Iterator<HugSession> iterator = ACTIVE.iterator();

        while (iterator.hasNext()) {
            HugSession session = iterator.next();

            session.ticksLeft--;

            boolean disconnected =
                    server.getPlayerList().getPlayer(
                            session.giver.getUUID()
                    ) != session.giver
                            || server.getPlayerList().getPlayer(
                            session.receiver.getUUID()
                    ) != session.receiver;

            if (session.ticksLeft <= 0
                    || disconnected
                    || !isNear(session.giver, session.receiver)) {

                iterator.remove();

                HugNetworking.broadcastState(
                        session.giver,
                        session.receiver,
                        0
                );
            }
        }
    }

    private static boolean isNear(
            ServerPlayer a,
            ServerPlayer b
    ) {
        return a.level() == b.level()
                && a.isAlive()
                && b.isAlive()
                && a.closerThan(b, MAX_DISTANCE);
    }
}
