package org.geminicraft.betterclaims.claims.claim;

  /*
      This class represents the 3D box shape. Claim is simply the data model.
    */

import org.bukkit.Location;

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

    public ClaimBoundBox(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public ClaimBoundBox(Claim claim) {
        this(claim.getLesserBoundaryCorner(), claim.getGreaterBoundaryCorner());
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
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
        return x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ;
    }

    public boolean overlaps(Claim claim) {
        return !(this.getMinX() > maxX || this.getMinY() > maxY || this.getMinZ() > maxZ ||
                minZ > this.getMaxX() || minY > this.getMaxY() || minZ > this.getMaxZ());
    }


}
