package org.geminicraft.betterclaims.listeners.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;
import org.mineacademy.fo.Common;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        final Entity entity = event.getRightClicked();

        // We disable any interaction *FOR NOW* until permissions are implemented.
        if (!(ClaimLocationUtil.isPlayerAllowedToInteract(entity.getLocation(), player, null))) {
            Common.tell(player, "&c You can't interact with this entity.");
        }
    }
}
