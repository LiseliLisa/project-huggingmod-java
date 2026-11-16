package liselilisa.huggingmod.hug;

import liselilisa.huggingmod.config.HugConfigManager;
import net.minecraft.server.level.ServerPlayer;

public class HugSession {
    private static final int HUG_DURATION = HugConfigManager.hugDurationTicks;
    final ServerPlayer giver;
    final ServerPlayer receiver;

    int ticksLeft = HUG_DURATION;
    boolean mutual = false;

    HugSession(ServerPlayer giver, ServerPlayer receiver) {
        this.giver = giver;
        this.receiver = receiver;
    }
}
