package liselilisa.huggingmod.client.input;


import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;

public class HugTargeting {

    public static int getHugTarget(Minecraft client){
        Entity target = client.crosshairPickEntity;

        if (target == null) return 0;

        if (!target.is(EntityTypes.PLAYER)) {
            client.player.sendSystemMessage(Component.literal(
                    "[HuggingMod] I'm sorry you may not hug " + target.getType().toShortString()
            ));
            return 0;
        }
        return target.getId();
    }
}
