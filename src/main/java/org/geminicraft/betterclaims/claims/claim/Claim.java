package org.geminicraft.betterclaims.claims.claim;

import org.bukkit.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Claim {

    private Location lesserBoundaryCorner, greaterBoundaryCorner;
    private UUID ownerUUID;
    private Long claimID;
    private Set<ClaimGroup> claimGroup;

    private static List<Claim> claimList = new ArrayList<>();

    public static void addClaimToList(Claim claim) {
        claimList.add(claim);
    }

    public Claim() {
    }

    public Claim(UUID ownerUUID, Long claimID, Location lesserBoundaryCorner, Location greaterBoundaryCorner, Set<ClaimGroup> claimGroups) {
        this.ownerUUID = ownerUUID;
        this.claimID = claimID;
        this.lesserBoundaryCorner = lesserBoundaryCorner;
        this.greaterBoundaryCorner = greaterBoundaryCorner;
        this.claimGroup = claimGroups;
    }

    // TODO: This isn't the cleanest solution. Revisit it and clean it up.
    public boolean contains(Location location) {
        ClaimBoundBox boundBox = new ClaimBoundBox(this);
        return  boundBox.contains(location);
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public Long getClaimID() {
        return claimID;
    }

    public static List<Claim> getClaimList() {
        return claimList;
    }

    public Location getLesserBoundaryCorner() {
        return lesserBoundaryCorner;
    }

    public Location getGreaterBoundaryCorner() {
        return greaterBoundaryCorner;
    }

    public void setLesserBoundaryCorner(Location lesserBoundaryCorner) {
        this.lesserBoundaryCorner = lesserBoundaryCorner;
    }

    public void setGreaterBoundaryCorner(Location greaterBoundaryCorner) {
        this.greaterBoundaryCorner = greaterBoundaryCorner;
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public void setClaimID(Long claimID) {
        this.claimID = claimID;
    }

    public Set<ClaimGroup> getClaimGroup() {
        return claimGroup;
    }

    public void setClaimGroup(Set<ClaimGroup> claimGroup) {
        this.claimGroup = claimGroup;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "lesserBoundaryCorner=" + lesserBoundaryCorner +
                ", greaterBoundaryCorner=" + greaterBoundaryCorner +
                ", ownerUUID=" + ownerUUID +
                ", claimID=" + claimID +
                ", claimGroup=" + claimGroup +
                '}';
    }


}
