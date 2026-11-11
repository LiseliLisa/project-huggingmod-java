package liselilisa.huggingmod.command;


import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import liselilisa.huggingmod.config.HugConfigManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

public class HugCommands {
    public static int GetSettings(CommandContext<CommandSourceStack> context){
        context.getSource().sendSuccess(() -> Component.literal(
                "[HuggingMod] Current Settings\n\n" +
                        "Hug Distance: " + HugConfigManager.hugDistance + "\n" +
                        "Hug Duration: " + HugConfigManager.hugDurationTicks + " Ticks\n" +
                        "Hug Cooldown: " + HugConfigManager.hugCooldownSeconds + " Seconds"
        ), false);
        return 1;
    }

    public static int ChangeSettings(CommandContext<CommandSourceStack> context){
        String[] settings = {"hugDistance","hugDurationTicks","hugCooldownSeconds"};
        String setting = StringArgumentType.getString(context, "setting");
        Float value = FloatArgumentType.getFloat(context, "value");

        if (!Arrays.stream(settings).anyMatch(setting::equals)){
            context.getSource().sendFailure(Component.literal("[HuggingMod] Setting not found"));
            return 0;
        }

        context.getSource().sendSuccess(() -> Component.literal("[HuggingMod] Not yet implemented"),false);
        return 1;
    }

}
