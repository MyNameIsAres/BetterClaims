package org.geminicraft.betterclaims.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.geminicraft.betterclaims.claims.claim.data.PlayerClaimData;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

public class BlockBreakListener implements Listener {

    // TODO: This is temporary to simulate player storage.
    PlayerClaimData lastPlayerClaim = PlayerClaimData.getInstance();

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        final Location blockLocation = block.getLocation();

        System.out.println("Last Claim: " + lastPlayerClaim.getLastClaim());

        if (!ClaimLocationUtil.isPlayerAllowedToInteract(blockLocation, player, lastPlayerClaim.getLastClaim())) {
            event.setCancelled(true);
            Common.tell(player, "&cYou can't break here.");
        }


    }

}
