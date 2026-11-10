package liselilisa.huggingmod.config;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class HugConfig {
    private static final Path CONFIG_FILE =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("hugconfig.json");


    public static void load() {
        if (!Files.exists(CONFIG_FILE)){

        }
    }
}
