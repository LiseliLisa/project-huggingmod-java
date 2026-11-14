package liselilisa.huggingmod;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import liselilisa.huggingmod.command.HugCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.resources.Identifier;

public class Huggingmod implements ModInitializer {
    public static final String MOD_ID = "huggingmod";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

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
                                    .then(Commands.argument("value", DoubleArgumentType.doubleArg())
                                            .executes(HugCommands::ChangeSettings)
                                    )
                            )
                    )
            );
        });
    }
}
