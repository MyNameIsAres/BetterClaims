package org.geminicraft.betterclaims.events;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.geminicraft.betterclaims.MainPlugin;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.claim.data.ClaimPersistence;
import org.mineacademy.fo.Common;

import java.io.IOException;

public class TestInteractEvents implements Listener {

    Location testLocation = null;
    Location secondTestLocation = null;

    private MainPlugin mainPlugin;
    private Gson gson;

    public TestInteractEvents(MainPlugin mainPlugin, Gson gson) {
        this.mainPlugin = mainPlugin;
        this.gson = gson;
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

            Claim claim = new Claim(event.getPlayer().getUniqueId(), 0L, testLocation, secondTestLocation);
            Claim.addClaimToList(claim);

//            try {
//                claim.persistClaimToJson();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            String json = gson.toJson(claim);
//            System.out.println(json);
//
            try {
                new ClaimPersistence(gson).persistClaimToJson(claim);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            Claim jsonClaim = gson.fromJson(json, Claim.class);
//            System.out.println(jsonClaim);

//            try {
//                claim.persistClaimToJson();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Bukkit.getScheduler().runTask(mainPlugin, new VisualiserTask(testLocation, secondTestLocation));

            testLocation = null;
            secondTestLocation = null;

        }
    }
}
