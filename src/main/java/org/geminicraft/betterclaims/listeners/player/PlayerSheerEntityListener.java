package org.geminicraft.betterclaims.listeners.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

public class PlayerSheerEntityListener implements Listener {

    @EventHandler
    public void onPlayerSheerSheep(PlayerShearEntityEvent event) {
        final Player player = event.getPlayer();
        Entity entity = event.getEntity();

        if (!(ClaimLocationUtil.isPlayerAllowedToInteract(entity.getLocation(), player, null))) {
            event.setCancelled(true);
            Common.tell(player, "&cCan't do that, mate.");
        }
    }
}
