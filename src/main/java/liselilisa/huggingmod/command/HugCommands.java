package liselilisa.huggingmod.command;


import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import liselilisa.huggingmod.config.HugConfigManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;


import java.nio.file.Path;
import java.util.Arrays;

public class HugCommands {
    private static final Path CONFIG_FILE =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("hugconfig.json");

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
        Double value = DoubleArgumentType.getDouble(context, "value");

        if (!Arrays.stream(settings).anyMatch(setting::equals)){
            context.getSource().sendFailure(Component.literal("[HuggingMod] Setting not found"));
            return 0;
        }
        switch (setting){
            case "hugDistance": {
                HugConfigManager.hugDistance = value;
                HugConfigManager.save();
                context.getSource().sendSuccess(() -> Component.literal("[HuggingMod] Configuration saved"),false);
            };return 1;
            case "hugDurationTicks": {
                HugConfigManager.hugDurationTicks = Byte.parseByte(value.toString());
                HugConfigManager.save();
                context.getSource().sendSuccess(() -> Component.literal("[HuggingMod] Configuration saved"),false);
            };return 1;
            case "hugCooldownSeconds": {
                HugConfigManager.hugCooldownSeconds = Byte.parseByte(value.toString());
                HugConfigManager.save();
                context.getSource().sendSuccess(() -> Component.literal("[HuggingMod] Configuration saved"),false);
            };return 1;
            default: {
                context.getSource().sendFailure(Component.literal("[HuggingMod] Configuration could not be saved"));
            };return 0;
        }


    }

}
