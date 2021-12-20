package org.geminicraft.betterclaims.listeners.player;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

import java.util.Optional;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Action action = event.getAction();

        // We don't care about this as nothing is really happening.
        if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) return;

        final Player player = event.getPlayer();
        Optional<Block> block = Optional.ofNullable(event.getClickedBlock());

        // TODO: Investigate if this warning is true
        Optional<Material> material = Optional.ofNullable(event.getMaterial());


        block.ifPresent((selectedBlock -> {
            if (!(ClaimLocationUtil.isPlayerAllowedToInteract(selectedBlock.getLocation(), player, null))) {
                Common.tell(player, "&cYou aren't permitted to interact with this block.");
                event.setCancelled(true);
            }
        }));

        // Called on every interaction, just in case I miss something.
        System.out.println("[ATTENTION] This also triggers the event");
    }


    private boolean isBoat(final Material material) {
        return material.toString().endsWith("_BOAT");
    }

    private boolean isBoneMeal(final Material material) {
        return material == Material.BONE_MEAL;
    }

}
