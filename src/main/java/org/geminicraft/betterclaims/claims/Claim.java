package org.geminicraft.betterclaims.claims;

import org.bukkit.Location;
import org.bukkit.World;

public class Claim {

    private final World world;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int minZ;
    private int maxZ;


    public Claim(Location location1, Location location2) {
        this(location1.getWorld(), location1.getBlockX(), location1.getBlockY(), location1.getBlockZ(),
                location2.getBlockX(), location2.getBlockY(), location2.getBlockZ());
    }

    public Claim(World world, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        this.world = world;

        this.minX = Math.min(minX, maxX);
        this.maxX = Math.max(minX, maxX);
        this.minY = Math.min(minY, maxY);
        this.maxY = Math.max(minY, maxY);;
        this.minZ = Math.min(minZ, maxZ);;
        this.maxZ = Math.max(minZ, maxZ);
    }

    public World getWorld() {
        return world;
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
        return claim.getWorld().equals(world) &&
                claim.getMinX() >= minX && claim.getMaxX() < maxX &&
                claim.getMinY() >= minX && claim.getMaxY() < maxX &&
                claim.getMinZ() >= minX && claim.getMaxZ() < maxX;
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
        return claim.getWorld().equals(world) &&
                !(claim.getMinX() > maxX || claim.getMinY() > maxY || claim.getMinZ() > maxZ ||
                        minZ > claim.getMaxX() || minY > claim.getMaxY() || minZ > claim.getMaxZ());
    }

    @Override
    public String toString() {
        return "Claim{" +
                "world=" + world +
                ", minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                ", minZ=" + minZ +
                ", maxZ=" + maxZ +
                '}';
    }
}
