package org.geminicraft.betterclaims.claims.claim.data;

import com.google.gson.Gson;

import org.geminicraft.betterclaims.MainPlugin;
import org.geminicraft.betterclaims.claims.claim.Claim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ClaimPersistence {

    private Gson gson;

    private final String PATH = MainPlugin.getInstance().getDataFolder().getAbsolutePath() + "/ClaimData/";

    public ClaimPersistence(Gson gson) {
        this.gson = gson;
    }

    public void createClaimAsJson(Claim claim) throws IOException {
        // TODO: This is a constant path for now, creating claims manually is not yet implemented.
        File file = new File(PATH + "claim_id_2.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(claim, writer);

        writer.flush();
        writer.close();
    }

    // TODO Update a claim - consider changing this to be more explicit: 'What are we updating? What properties? And maybe even 'why'?'
    public void updateClaim(Claim claim) {

    }

    // TODO: Delete a claim
    public void deleteClaim(Claim claim) {

    }
}
