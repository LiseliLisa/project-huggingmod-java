package liselilisa.huggingmod.config;


// Sets the default values for the config json

public class HugConfig {
    private static double HugDistance = 3.0;
    private static byte HugDurationTicks = 60;
    private static byte HugCooldownSeconds = 3;


    public static double getHugDistance(){return HugDistance;}
    public static byte getHugDurationTicks(){return HugDurationTicks;}
    public static byte getHugCooldownSeconds(){return HugCooldownSeconds;}
}
