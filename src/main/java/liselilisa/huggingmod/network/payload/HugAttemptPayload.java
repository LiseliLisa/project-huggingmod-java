package liselilisa.huggingmod.network.payload;

import liselilisa.huggingmod.Huggingmod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record HugAttemptPayload(
        int targetEntityId
) implements CustomPacketPayload {

    public static final Type<HugAttemptPayload> TYPE =
            new Type<>(Huggingmod.id("hug_attempt"));

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            HugAttemptPayload
            > CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            HugAttemptPayload::targetEntityId,
            HugAttemptPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
