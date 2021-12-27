package org.geminicraft.betterclaims.command;

import org.bukkit.entity.Player;
import org.geminicraft.betterclaims.claims.claim.ClaimTool;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

public class ClaimToolCommand extends SimpleCommand {

    public ClaimToolCommand() {
        super("tool");
    }

    @Override
    protected void onCommand() {
        Player player = getPlayer();
        player.getInventory().addItem(ClaimTool.getInstance().getItem());
        Common.tell(player, "&2You were given a claim tool");
    }
}
