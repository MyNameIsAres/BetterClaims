package org.geminicraft.betterclaims;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.bukkit.event.Listener;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.claim.data.ClaimAdapter;
import org.geminicraft.betterclaims.command.ClaimCommand;
import org.geminicraft.betterclaims.command.ClaimToolCommand;
import org.geminicraft.betterclaims.listeners.TestInteractEvents;
import org.geminicraft.betterclaims.listeners.blocks.*;
import org.geminicraft.betterclaims.listeners.player.PlayerEmptyBucketListener;
import org.geminicraft.betterclaims.listeners.player.PlayerInteractListener;
import org.geminicraft.betterclaims.listeners.player.PlayerSheerEntityListener;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.FileUtil;
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
            this.getClaimsFromJson(gson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        registerEvents(new TestInteractEvents(this, gson));
        registerEvents(new BlockPlaceListener());
        registerEvents(new BlockBreakListener());
        registerEvents(new BlockFormListener());
        registerEvents(new BlockFluidListener());
        registerEvents(new StructureGrowListener());

//        registerEvents(new PlayerInteractListener());
//        registerEvents(new PlayerSheerEntityListener());
//        registerEvents(new PlayerEmptyBucketListener());

        registerCommand(new ClaimCommand(gson));
        registerCommand(new ClaimToolCommand());
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

    private void getClaimsFromJson(Gson gson) throws FileNotFoundException {
        FileReader reader;

        for (File file : FileUtil.getFiles("ClaimData", ".json")) {
            reader = new FileReader(file);

            Claim claim = gson.fromJson(reader, Claim.class);
            Claim.addClaimToList(claim);

            System.out.println("[ALERT] Here is thy claim: " + claim);
        }
    }

    private GsonBuilder createGsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Claim.class, new ClaimAdapter());
        gsonBuilder.setPrettyPrinting();

        return gsonBuilder;
    }
}