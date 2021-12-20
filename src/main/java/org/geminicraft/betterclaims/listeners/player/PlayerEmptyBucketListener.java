package org.geminicraft.betterclaims.listeners.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

public class PlayerEmptyBucketListener implements Listener {

    @EventHandler
    public void onPlayerEmptyBucket(final PlayerBucketEmptyEvent event) {
        final Player player = event.getPlayer();
        Block block = event.getBlockClicked().getRelative(event.getBlockFace());

        // TODO Passing in null for now, this is to be removed in favor of the player's last claim location.
        if (!(ClaimLocationUtil.isPlayerAllowedToInteract(block.getLocation(), player, null))) {
            event.setCancelled(true);
            Common.tell(player, "&cYou can't empty a bucket in this claim");
        }
    }

}
