package net.maltshakes.genetictesting.server;

import net.maltshakes.genetictesting.GeneticTesting;
import net.maltshakes.genetictesting.utils.CompatHelpers;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class GeneticTestingPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE =
            NetworkRegistry.newSimpleChannel(
                    CompatHelpers.getCompatibleResourceLocation(GeneticTesting.MOD_ID, "main"),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals);

    private static int packetId = 0;

    public static void register() {
        INSTANCE.messageBuilder(SaveGeneBookPagesPacket.class, packetId++)
                .encoder(SaveGeneBookPagesPacket::toBytes)
                .decoder(SaveGeneBookPagesPacket::new)
                .consumerMainThread(SaveGeneBookPagesPacket::handle)
                .add();
    }
}
