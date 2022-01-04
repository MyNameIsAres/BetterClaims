package org.geminicraft.betterclaims.claims.claim;

  /*
      This class represents the 3D box shape. Claim is simply the data model.
    */

import org.bukkit.Location;
import org.bukkit.block.Block;

public class ClaimBoundBox {

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int minZ;
    private int maxZ;

    public ClaimBoundBox(Location location1, Location location2) {
        this(location1.getBlockX(), location1.getBlockY(), location1.getBlockZ(),
                location2.getBlockX(), location2.getBlockY(), location2.getBlockZ());
    }

    public ClaimBoundBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public ClaimBoundBox(Block block) {
        this(block.getX(), block.getY(), block.getZ(), block.getX(), block.getY(), block.getZ());
    }

    public ClaimBoundBox(Claim claim) {
        this(claim.getLesserBoundaryCorner(), claim.getGreaterBoundaryCorner());
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public boolean contains(Claim claim) {
        return this.getMinX() >= minX && this.getMaxX() < maxX &&
                this.getMinY() >= minX && this.getMaxY() < maxX &&
                this.getMinZ() >= minX && this.getMaxZ() < maxX;
    }

    public boolean contains(Location location) {
        return contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public boolean contains(int x, int y, int z) {
        return x <= minX && x >= maxX &&
                y <= minY && y >= maxY &&
                z <= minZ && z >= maxZ;
    }

    public boolean intersects(Claim claim, ClaimBoundBox otherClaim) {
        return this.minX <= otherClaim.maxX && this.maxX >= otherClaim.minX
                && this.minY <= otherClaim.maxY && this.maxY >= otherClaim.minY
                && this.minZ <= otherClaim.maxZ && this.maxZ >= otherClaim.minZ;
    }

    public boolean overlaps(Claim claim) {
        return !(this.getMinX() > maxX || this.getMinY() > maxY || this.getMinZ() > maxZ ||
                minZ > this.getMaxX() || minY > this.getMaxY() || minZ > this.getMaxZ());
    }

    @Override
    public String toString() {
        return "ClaimBoundBox{" +
                "minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                ", minZ=" + minZ +
                ", maxZ=" + maxZ +
                '}';
    }
}
