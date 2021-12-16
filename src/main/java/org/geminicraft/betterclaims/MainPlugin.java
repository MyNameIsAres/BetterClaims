package org.geminicraft.betterclaims;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.bukkit.event.Listener;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.claim.data.ClaimAdapter;
import org.geminicraft.betterclaims.command.ClaimCommand;
import org.geminicraft.betterclaims.events.BlockPlaceListener;
import org.geminicraft.betterclaims.events.PlayerInteractListener;
import org.geminicraft.betterclaims.events.TestInteractEvents;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainPlugin extends SimplePlugin implements Listener {

    @Override
    protected void onPluginStart() {
        Common.logFramed("BetterPlugins is starting...");
        Gson gson = createGsonBuilder().create();

        try {
            this.getClaimFromJson(gson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        registerEvents(new PlayerInteractListener());
//        registerEvents(new TestInteractEvents(this, gson));
        registerEvents(new BlockPlaceListener());
        registerCommand(new ClaimCommand(gson));
    }

    private void getClaimFromJson(Gson gson) throws FileNotFoundException {
        File file = new File(this.getDataFolder().getAbsolutePath() + "/claims.json");
        FileReader reader = new FileReader(file);
        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);


        jsonArray.forEach(System.out::println);

        jsonArray.forEach((arrayItem) -> {

            Claim claim = gson.fromJson(arrayItem.getAsJsonObject(), Claim.class);

            Claim.addClaimToList(claim);

            System.out.println(claim.toString());
        });
    }

    private GsonBuilder createGsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Claim.class, new ClaimAdapter());
        gsonBuilder.setPrettyPrinting();

        return gsonBuilder;
    }
}