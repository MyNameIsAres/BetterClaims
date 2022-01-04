package org.geminicraft.betterclaims.listeners.player;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.claim.data.ClaimPersistence;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class PlayerInteractListener implements Listener {

    private Gson gson;

    public PlayerInteractListener(Gson gson) {
        this.gson = gson;
    }

    Location lastClickedLocation = null;

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Action action = event.getAction();

        // We don't care about this as nothing is really happening.
        if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) return;

        if (Objects.equals(event.getHand(), EquipmentSlot.HAND)) {
            this.onBlockClick(event);
        }

    }

    private boolean isBoat(final Material material) {
        return material.toString().endsWith("_BOAT");
    }

    private boolean isBoneMeal(final Material material) {
        return material == Material.BONE_MEAL;
    }

    private void onBlockClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        if (action != Action.RIGHT_CLICK_BLOCK)
            return;

        if (event.getItem().getType() == Material.GOLDEN_SHOVEL)
            event.setCancelled(true);


        if (lastClickedLocation == null) {
            lastClickedLocation = event.getClickedBlock().getLocation();
            Common.tell(player, "&4You set the first location! " + lastClickedLocation);
            return;
        }

        Location clickedLocation = event.getClickedBlock().getLocation();
        if (lastClickedLocation == clickedLocation) {
            lastClickedLocation = null;
            Common.tell(player, "&cNot allowed to create a 1x1x1 claim.");
            return;
        }

        Common.tell(player, "&2Set the second location: " + clickedLocation);
        int width = Math.abs(lastClickedLocation.getBlockX() - clickedLocation.getBlockX()) + 1;
        int length = Math.abs(lastClickedLocation.getBlockZ() - clickedLocation.getBlockZ()) + 1;
        int totalArea = width * length;

        if (totalArea < 100) {
            lastClickedLocation = null;
            Common.tell(player, "&cYou created an area of " + totalArea + " size, needs to be a minimum of 100 blocks");
            return;
        }
        if (!(inSameWorld(lastClickedLocation, clickedLocation))) {
            return;
        }

        Claim newClaim = new Claim(
                player.getUniqueId(),
                4L,
                this.lastClickedLocation,
                clickedLocation,
                null);

        for(Claim claim : Claim.getClaimList()) {
            if (!Objects.equals(claim.getClaimID(), newClaim.getClaimID()) && claim.overlaps(claim, newClaim)) {
                Common.tell(player, "&cThis overlaps with another claim");

                lastClickedLocation = null;
                return;
            }
        }
        try {
            new ClaimPersistence(gson).createClaimAsJson(newClaim);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lastClickedLocation = null;
        Common.tell(event.getPlayer(), "You successfully created a claim!");
    }

    private boolean inSameWorld(Location firstCorner, Location secondCorner) {
        return firstCorner.getWorld() == secondCorner.getWorld();
    }
}
