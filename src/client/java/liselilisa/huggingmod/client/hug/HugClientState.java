package liselilisa.huggingmod.client.hug;

import liselilisa.huggingmod.network.payload.HugStatePayload;

public class HugClientState {

    private static int giverId = -1;
    private static int receiverId = -1;
    public static int state = 0;

    public static void update(HugStatePayload payload, int localPlayerId){
        giverId = payload.giverId();
        receiverId = payload.receiverId();

        boolean involved = receiverId == localPlayerId || giverId == localPlayerId;

        if (!involved) return;
        if (payload.state() == 0) clear();

        giverId = payload.giverId();
        receiverId = payload.receiverId();
        state = payload.state();

    }

    public static boolean isBeingHuged(int playerId){
        return state == 1 && playerId == receiverId;
    }

    public static boolean isGivingHug(int playerId){
        return state != 0 && playerId == giverId;
    }

    public static boolean isInHug(int playerId){
        return state != 0 && (playerId == giverId || playerId == receiverId);
    }

    public static void clear(){
        giverId = -1;
        receiverId = -1;
        state = 0;
    }
}
