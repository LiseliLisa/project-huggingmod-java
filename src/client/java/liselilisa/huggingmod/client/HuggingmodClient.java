package liselilisa.huggingmod.client;
import liselilisa.huggingmod.client.input.HugKeybinds;
import liselilisa.huggingmod.client.network.HugClientNetworking;
import net.fabricmc.api.ClientModInitializer;


public class HuggingmodClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        HugClientNetworking.register();
        HugKeybinds.register();

    }
}
