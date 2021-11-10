package org.geminicraft.betterclaims;


import org.geminicraft.betterclaims.command.MenuCommand;
import org.geminicraft.betterclaims.events.TestInteractEvents;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

public class MainPlugin extends SimplePlugin {

    @Override
    protected void onPluginStart() {
        Common.logFramed("BetterPlugins is starting...");

        registerCommand(new MenuCommand());
        registerEvents(new TestInteractEvents(this));
    }
}
