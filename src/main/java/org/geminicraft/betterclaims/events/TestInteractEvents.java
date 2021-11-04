package org.geminicraft.betterclaims.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.geminicraft.betterclaims.MainPlugin;
import org.geminicraft.betterclaims.claims.Claim;
import org.geminicraft.betterclaims.task.VisualiserTask;
import org.mineacademy.fo.Common;

public class TestInteractEvents implements Listener {

    Location testLocation = null;
    Location secondTestLocation = null;

    private MainPlugin mainPlugin;

    public TestInteractEvents(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }

    @EventHandler
    private void onPlayerInteract(final PlayerInteractEvent event) {
        final Action action = event.getAction();
        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (action == Action.RIGHT_CLICK_BLOCK && testLocation == null) {
                testLocation = event.getClickedBlock().getLocation();

                Common.tell(event.getPlayer(), "&2Started claim. First location set: "
                        + testLocation.getWorld() + " " + testLocation.getBlockX() + " " + testLocation.getBlockZ());
                return;
            }
            if (testLocation != null && secondTestLocation == null) {
                secondTestLocation = event.getClickedBlock().getLocation();

                Common.tell(event.getPlayer(), "&4Second location set: "
                        + secondTestLocation.getWorld() + " " + secondTestLocation.getBlockX() + " " + secondTestLocation.getBlockZ());
            }

            Common.log(testLocation + " xx");
            Common.log(secondTestLocation + " zz");

            int width = Math.abs(testLocation.getBlockX() - secondTestLocation.getBlockX()) + 1;
            int length = Math.abs(testLocation.getBlockZ() - secondTestLocation.getBlockZ()) + 1;

            Common.log("Width: " + width);
            Common.log("Length: " + length);

            int newArea = width * length;
            Common.log(newArea + " area.");

            Claim claim = new Claim(testLocation, secondTestLocation);
            Common.tell(event.getPlayer(), claim.toString());

            Bukkit.getScheduler().runTask(mainPlugin, new VisualiserTask(testLocation, secondTestLocation));

            testLocation = null;
            secondTestLocation = null;

        }
    }
}
