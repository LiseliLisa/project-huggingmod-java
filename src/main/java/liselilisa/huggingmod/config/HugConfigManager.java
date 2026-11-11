package liselilisa.huggingmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// Config json will be outside the compiled files
// Handles config Changes

public class HugConfigManager {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_FILE =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("hugconfig.json");

    public static double hugDistance = HugConfig.getHugDistance();
    public static byte hugDurationTicks = HugConfig.getHugDurationTicks();
    public static byte hugCooldownSeconds = HugConfig.getHugCooldownSeconds();


    public static void load() {
        if (!Files.exists(CONFIG_FILE)){
            save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(
                CONFIG_FILE, StandardCharsets.UTF_8)){
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            if (json.has("hugDistance")){
                hugDistance = json.get("hugDistance").getAsDouble();
            }
            if (json.has("hugDurationTicks")){
                hugDurationTicks = json.get("hugDurationTicks").getAsByte();
            }
            if (json.has("hugCooldownSeconds")){
                hugCooldownSeconds = json.get("hugCooldownSeconds").getAsByte();
            }

        }
        catch (IOException | RuntimeException e) {
            System.err.println(
                    "[JessiMod] Failed to load config: " + e
            );
        }

    }

    public static void save(){
        JsonObject json = new JsonObject();
        json.addProperty("hugDistance",HugConfig.getHugDistance());
        json.addProperty("hugDurationTicks",HugConfig.getHugDurationTicks());
        json.addProperty("hugCooldownSeconds",HugConfig.getHugCooldownSeconds());

        try {
            Files.createDirectories(
                    CONFIG_FILE.getParent()
            );

            try (Writer writer = Files.newBufferedWriter(
                    CONFIG_FILE, StandardCharsets.UTF_8)) {

                GSON.toJson(json, writer);
            }

        } catch (IOException e) {
            System.err.println(
                    "[HuggingMod] Failed to save config: " + e
            );
        }
    }
}
