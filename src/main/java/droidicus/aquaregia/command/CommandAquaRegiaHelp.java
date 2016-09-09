package droidicus.aquaregia.command;

import net.minecraft.command.CommandHelp;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.List;
import java.util.Map;

/**
 * Shows help for the {@code /aquaregia} command.
 *
 * @author Choonster
 */
public class CommandAquaRegiaHelp extends CommandHelp {
    /**
     * The {@link ISubCommandManager} this is registered with.
     */
    private final ISubCommandManager subCommandManager;

    public CommandAquaRegiaHelp(ISubCommandManager subCommandManager) {
        this.subCommandManager = subCommandManager;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.aquaregia:help.usage";
    }

    @Override
    protected Map<String, ICommand> getCommandMap(MinecraftServer server) {
        return subCommandManager.getCommands();
    }

    @Override
    protected List<ICommand> getSortedPossibleCommands(ICommandSender sender, MinecraftServer server) {
        return subCommandManager.getPossibleCommands(sender);
    }
}
