package liselilisa.huggingmod.config;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

// Sets the default values for the config json

public class HugConfig {
    private static double HugDistance = 3.0;
    private static byte HugDurationTicks = 60;
    private static byte HugCooldownSeconds = 3;


    public static double getHugDistance(){return HugDistance;}
    public static byte getHugDurationTicks(){return HugDurationTicks;}
    public static byte getHugCooldownSeconds(){return HugCooldownSeconds;}

    public static void setHugDistance(double value) {HugDistance = value;}
    public static void setHugDurationTicks(byte value) {HugDurationTicks = value;}
    public static void setHugCooldownSeconds(byte value) {HugCooldownSeconds = value;}
}
