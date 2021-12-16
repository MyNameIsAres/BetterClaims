package org.geminicraft.betterclaims.claims.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.mineacademy.fo.Common;

public class ClaimLocationStringUtil {

    public String getStringLocation(final Location location) {
        return location.getWorld().getName() + " " + location.getX() + " " + location.getY() + " " + location.getZ();
    }

    public Location getLocationString(final String input) {
        final String[] parts = input.split(" ");
        if (parts.length == 4) {

            try {
                final World w = Bukkit.getServer().getWorld(parts[0]);
                final double x = Double.parseDouble(parts[1]);
                final double y = Double.parseDouble(parts[2]);
                final double z = Double.parseDouble(parts[3]);

                return new Location(w, x, y, z);
            } catch (NumberFormatException numberFormatException) {
                Common.log("Could not parse String to Double! Please check the file for any typo's!");
            }
        }

        return null;
    }

}
