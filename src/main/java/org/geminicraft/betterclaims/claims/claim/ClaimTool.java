package org.geminicraft.betterclaims.claims.claim;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.tool.Tool;
import org.mineacademy.fo.remain.CompMaterial;

public class ClaimTool extends Tool {

    private static final Tool instance = new ClaimTool();

    public static Tool getInstance() {
        return instance;
    }

    private ClaimTool() {
    }

    @Override
    public ItemStack getItem() {
        return ItemCreator.of(
                        CompMaterial.GOLDEN_SHOVEL,
                        "&bClaim Tool",
                        "")
                .build().make();
    }

    Location lastClickedLocation = null;

    @Override
    protected void onBlockClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action != Action.RIGHT_CLICK_BLOCK)
            return;

        // TODO: Test non item
        if (event.getItem().getType() == Material.GOLDEN_SHOVEL)
            event.setCancelled(true);


        if (lastClickedLocation == null) {
            lastClickedLocation = event.getClickedBlock().getLocation();
            Common.tell(player, "&4You set the first location!");

            return;
        }

        Location clickedLocation = event.getClickedBlock().getLocation();
        if (lastClickedLocation == clickedLocation) {
            lastClickedLocation = null;
            Common.tell(player, "&cCan't do that, mate.");
            return;
        }

        int width = Math.abs(lastClickedLocation.getBlockX() - clickedLocation.getBlockX()) + 1;
        int length = Math.abs(lastClickedLocation.getBlockZ() - clickedLocation.getBlockZ()) + 1;
        int totalArea = width * length;

        if (totalArea < 100) {
            lastClickedLocation = null;
            Common.tell(player, "&cYou created an area of " + totalArea + " size, needs to be a minimum of 100 blocks");

            return;
        }

        Claim claim = new Claim(
                event.getPlayer().getUniqueId(),
                4L,
                lastClickedLocation,
                clickedLocation,
                null);

        Claim.addClaimToList(claim);

        lastClickedLocation = null;

        Common.tell(event.getPlayer(), "You successfully created a claim!");
    }
}
