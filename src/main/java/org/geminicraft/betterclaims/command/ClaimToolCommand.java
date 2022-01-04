package org.geminicraft.betterclaims.command;

import com.google.gson.Gson;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class ClaimToolCommand extends SimpleCommand {

    @Getter
    private Gson gson;

    public ClaimToolCommand(Gson gson) {
        super("tool");
        this.gson = gson;
    }

    @Override
    protected void onCommand() {
        Player player = getPlayer();
        ItemStack claimTool = ItemCreator.of(
                CompMaterial.GOLDEN_SHOVEL,
                "&3Claim Tool").build().make();

        player.getInventory().addItem(claimTool);
        Common.tell(player, "You were given a claim tool!");
    }
}
