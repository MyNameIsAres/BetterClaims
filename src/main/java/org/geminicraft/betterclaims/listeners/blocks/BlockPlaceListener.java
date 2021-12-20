package org.geminicraft.betterclaims.listeners.blocks;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.geminicraft.betterclaims.claims.claim.data.PlayerClaimData;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

public class BlockPlaceListener implements Listener {

    // TODO: This is temporary to simulate player storage.
    PlayerClaimData lastPlayerClaim = PlayerClaimData.getInstance();

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        final Location blockLocation = block.getLocation();

        if (!ClaimLocationUtil.isPlayerAllowedToInteract(blockLocation, player, lastPlayerClaim.getLastClaim())) {
            event.setBuild(false);
            Common.tell(player, "&cYou can't build here.");
        }
    }
}
