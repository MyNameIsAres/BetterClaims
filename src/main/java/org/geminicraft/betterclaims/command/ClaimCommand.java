package org.geminicraft.betterclaims.command;

import com.google.gson.Gson;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.geminicraft.betterclaims.MainPlugin;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.task.VisualiserTask;
import org.mineacademy.fo.command.SimpleCommand;

public class ClaimCommand extends SimpleCommand {

    @Getter
    private Gson gson;

    public ClaimCommand(Gson gson) {
        super("claim");
        this.gson = gson;
    }
    @Override
    protected void onCommand() {
       Claim.getClaimList().forEach((claim -> {
           Bukkit.getScheduler().runTask(MainPlugin.getInstance(), new VisualiserTask(claim.getLesserBoundaryCorner(), claim.getGreaterBoundaryCorner()));
       }));
    }
}