package org.geminicraft.betterclaims.events;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import java.util.UUID;

public class StructureGrowListener implements Listener {

    UUID ownerOfClaim = null;

    public UUID getOwnerOfClaim() {
        return ownerOfClaim;
    }

    public void setOwnerOfClaim(UUID ownerOfClaim) {
        this.ownerOfClaim = ownerOfClaim;
    }

    @EventHandler
    public void structureGrowListener(StructureGrowEvent event) {
        final Location location = event.getLocation();
        Claim claim = ClaimLocationUtil.getClaim(location, null);

        if (claim != null) {
            this.setOwnerOfClaim(claim.getOwnerUUID());
        }
        for (int i = 0; i < event.getBlocks().size(); i++) {
            BlockState block = event.getBlocks().get(i);

            Claim blockInClaim = ClaimLocationUtil.getClaim(block.getLocation(), claim);

            if (blockInClaim != null) {
                if (this.getOwnerOfClaim() == null || !this.getOwnerOfClaim().equals(blockInClaim.getOwnerUUID())) {
                    event.getBlocks().remove(block);
                }
            }
        }
    }
}
