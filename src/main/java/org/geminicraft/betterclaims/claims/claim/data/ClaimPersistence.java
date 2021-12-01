package org.geminicraft.betterclaims.claims.claim.data;

import com.google.gson.Gson;
import org.geminicraft.betterclaims.MainPlugin;
import org.geminicraft.betterclaims.claims.claim.Claim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ClaimPersistence {

    private Gson gson;

    public ClaimPersistence(Gson gson) {
        this.gson = gson;
    }
    public void persistClaimToJson(Claim claim) throws IOException {
        File file = new File(MainPlugin.getInstance().getDataFolder().getAbsolutePath() + "/claims.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(claim, writer);

        writer.flush();
        writer.close();
    }

    public void persistClaimsToJson(List<Claim> claimList) throws IOException {
        File file = new File(MainPlugin.getInstance().getDataFolder().getAbsolutePath() + "/claims.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, true);
        gson.toJson(claimList, writer);

        writer.flush();
        writer.close();
    }


}
