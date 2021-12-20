package org.geminicraft.betterclaims.listeners.blocks;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;

public class BlockFormListener implements Listener {

    private static final ImmutableSet<Material> BLOCK_FORM_MATERIAL = Sets.immutableEnumSet(
            Material.COBBLESTONE,
            Material.OBSIDIAN,
            Material.LAVA,
            Material.WATER
    );

    private boolean isBlockedMaterial(final Material material) {
        return BLOCK_FORM_MATERIAL.contains(material);
    }

    @EventHandler
    public void onBlockForm(final BlockFormEvent event) {
        final Block block = event.getBlock();

        if (isBlockedMaterial(block.getType())) {
            Claim claim = ClaimLocationUtil.getClaim(block.getLocation(), null);
            if (claim == null) {
                event.setCancelled(true);
            }
        }
    }
}
