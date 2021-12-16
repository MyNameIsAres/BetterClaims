package org.geminicraft.betterclaims.claims.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.mineacademy.fo.Common;

import java.util.List;

public class ClaimLocationUtil {

    public static Claim getClaim(Location blockLocation, Claim lastClaim) {
        if (lastClaim != null && lastClaim.contains(blockLocation)) {
            return lastClaim;
        }

        List<Claim> claimList = Claim.getClaimList();

        for (Claim claim : claimList) {
            if (claim.contains(blockLocation)) {
                return claim;
            }
        }
        return null;
    }

    // This covers breaking, placing, interactions at the basic level. Complexity will change over time.
    public static boolean isPlayerAllowedToInteract(Location location, Player player, Claim lastPlayerClaim) {
        Claim claim = getClaim(location, lastPlayerClaim);

        if (claim == null) {
            Common.tell(player, "&6This is probably the wilderness!");
            return true;
        }
        return claim.getOwnerUUID().equals(player.getUniqueId());
    }


}
