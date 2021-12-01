package org.geminicraft.betterclaims.command;

import com.google.gson.Gson;
import org.mineacademy.fo.command.SimpleCommand;

public class ClaimCommand extends SimpleCommand {

    private Gson gson;

    protected ClaimCommand(Gson gson) {
        super("claim");
        this.gson = gson;
    }

    @Override
    protected void onCommand() {
    }
}
