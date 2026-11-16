
package liselilisa.huggingmod.network.payload;

import liselilisa.huggingmod.Huggingmod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record HugStatePayload(
        int giverId,
        int receiverId,
        int state
) implements CustomPacketPayload {

    public static final Type<HugStatePayload> TYPE =
            new Type<>(Huggingmod.id("hug_state"));

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            HugStatePayload
            > CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            HugStatePayload::giverId,
            ByteBufCodecs.INT,
            HugStatePayload::receiverId,
            ByteBufCodecs.INT,
            HugStatePayload::state,
            HugStatePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
