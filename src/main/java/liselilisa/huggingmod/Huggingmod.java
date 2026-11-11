package liselilisa.huggingmod;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import liselilisa.huggingmod.command.HugCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;

public class Huggingmod implements ModInitializer {

    @Override
    public void onInitialize() {


        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
            dispatcher.register(Commands.literal("huggingmod")
                    .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
                    .then(Commands.literal("CurrentSettings").executes(HugCommands::GetSettings))
                    .then(Commands.literal("ChangeSettings")
                            .then(Commands.argument("setting", StringArgumentType.string())
                                    .suggests((context, builder) -> {
                                        String[] settings = {"hugDistance","hugDurationTicks","hugCooldownSeconds"};
                                        for (String item : settings){
                                            builder.suggest(item);
                                        }
                                        return builder.buildFuture();
                                    })
                                    .then(Commands.argument("value", FloatArgumentType.floatArg())
                                            .executes(HugCommands::ChangeSettings)
                                    )
                            )
                    )
            );
        });
    }
}
