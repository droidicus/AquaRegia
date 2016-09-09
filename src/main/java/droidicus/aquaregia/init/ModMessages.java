package droidicus.aquaregia.init;

import droidicus.aquaregia.AquaRegia;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

//import droidicus.aquaregia.network.*;

public class ModMessages {
    private static int messageID = 0;

    public static void registerMessages() {
//		registerMessage(MessageSurvivalCommandBlockSaveChanges.Handler.class, MessageSurvivalCommandBlockSaveChanges.class, Side.SERVER);
//		registerMessage(MessageUpdateHeldPigSpawnerFinite.Handler.class, MessageUpdateHeldPigSpawnerFinite.class, Side.CLIENT);
//		registerMessage(MessageUpdateHeldLastUseTime.Handler.class, MessageUpdateHeldLastUseTime.class, Side.CLIENT);
//		registerMessage(MessagePlayerReceivedLoot.Handler.class, MessagePlayerReceivedLoot.class, Side.CLIENT);
//		registerMessage(MessageFluidTankContents.Handler.class, MessageFluidTankContents.class, Side.CLIENT);
//		registerMessage(MessageUpdateHeldHiddenBlockRevealer.Handler.class, MessageUpdateHeldHiddenBlockRevealer.class, Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side receivingSide) {
        AquaRegia.network.registerMessage(messageHandler, requestMessageType, messageID++, receivingSide);
    }
}
