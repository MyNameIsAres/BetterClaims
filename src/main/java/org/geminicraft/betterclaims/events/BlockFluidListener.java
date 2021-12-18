package org.geminicraft.betterclaims.events;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;

public class BlockFluidListener implements Listener {

    private Claim lastSpreadClaim = null;

    @EventHandler
    public void onBlockForm(final BlockFromToEvent event) {
        if (event.getFace() == BlockFace.DOWN) return;

        final Block toBlock = event.getToBlock();
        Claim claim = ClaimLocationUtil.getClaim(toBlock.getLocation(), lastSpreadClaim);

        if (claim != null) {
            this.lastSpreadClaim = claim;
            final Block block = event.getBlock();

            if (!claim.contains(block.getLocation())) {
                event.setCancelled(true);
            }
        }

    }

}
