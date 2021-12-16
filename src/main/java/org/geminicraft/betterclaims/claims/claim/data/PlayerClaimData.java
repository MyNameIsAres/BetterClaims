package org.geminicraft.betterclaims.claims.claim.data;


import lombok.Getter;
import lombok.Setter;
import org.geminicraft.betterclaims.claims.claim.Claim;

// Simulates persistent storage for players and their respective claims.
public class PlayerClaimData {

    @Getter
    private static final PlayerClaimData instance = new PlayerClaimData();

    // TODO: Temporary access with Lombok, clean up with next refactor.
    @Getter
    @Setter
    public Claim lastClaim = null;

    private PlayerClaimData() {

    }

}
