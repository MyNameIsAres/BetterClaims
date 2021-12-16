package org.geminicraft.betterclaims.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.mineacademy.fo.Common;

import java.util.List;


public class BlockPlaceListener implements Listener {

    // TODO: Temporary access with Lombok, clean up with next refactor.
    @Getter
    @Setter
    Claim lastClaim = null;

    // TODO: Move elsewhere and refactor to be more efficient as it's currently O(n^2)
    // TODO Generalised method can be moved to its own class.
    private Claim getClaim(Location blockLocation, Claim lastClaim) {
        if (lastClaim != null && lastClaim.contains(blockLocation)) {
            return lastClaim;
        }

        List<Claim> claimList = Claim.getClaimList();

        for (Claim claim : claimList) {
            if (claim.contains(blockLocation)) {
                this.setLastClaim(claim);
                return claim;
            }
        }
        return null;
    }

    // TODO Generalised method can be moved to its own class.
    private boolean isPlayerAllowedToBuild(Location location, Player player) {
        Claim claim = getClaim(location, this.getLastClaim());

        if (claim == null) {
            Common.tell(player, "&6This is probably the wilderness!");
            return true;
        }
        return claim.getOwnerUUID().equals(player.getUniqueId());
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        final Location blockLocation = block.getLocation();

        if (!isPlayerAllowedToBuild(blockLocation, player)) {
            event.setBuild(false);
            Common.tell(player, "&cYou can't build here.");
        }
    }
}
