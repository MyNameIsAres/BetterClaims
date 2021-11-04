package org.geminicraft.betterclaims.task;

import org.bukkit.Location;
import org.mineacademy.fo.BlockUtil;
import org.mineacademy.fo.remain.CompParticle;

public class VisualiserTask implements Runnable {

    private final Location firstLocation;
    private final Location secondLocation;

    public VisualiserTask(Location firstLocation, Location secondLocation) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }

    @Override
    public void run() {
        for (final Location sphereLocation : BlockUtil.getBoundingBox(firstLocation, secondLocation)) {
            CompParticle.VILLAGER_HAPPY.spawn(sphereLocation);
        }
    }
}
