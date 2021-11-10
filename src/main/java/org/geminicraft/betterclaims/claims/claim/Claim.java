package org.geminicraft.betterclaims.claims.claim;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.geminicraft.betterclaims.MainPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Claim {

    Location lesserBoundaryCorner, greaterBoundaryCorner;
    private UUID ownerUUID;
    Long claimID;

    @Getter
    private List<Claim> claimList = new ArrayList<>();

    public void addClaimToList(Claim claim) {
        claimList.add(claim);
    }

    public Claim(UUID ownerUUID, Long claimID, Location lesserBoundaryCorner, Location greaterBoundaryCorner) {
        this.ownerUUID = ownerUUID;
        this.claimID = claimID;
        this.lesserBoundaryCorner = lesserBoundaryCorner;
        this.greaterBoundaryCorner = greaterBoundaryCorner;
    }

    public void persistClaimToJson() throws IOException {
        File file = new File(MainPlugin.getInstance().getDataFolder().getAbsolutePath() + "/claims.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        JsonArray jsonArray = new JsonArray();

        claimList.forEach((claim) -> {
            jsonArray.add(claim.write(new JsonObject()));
        });

        Writer writer = new FileWriter(file, false);
        new Gson().toJson(jsonArray, writer);

        writer.flush();
        writer.close();
    }

    // TODO: Fix encoding problem with location values.
    public JsonObject write(JsonObject object) {
        object.addProperty("ownerUUID", String.valueOf(ownerUUID));
        object.addProperty("claimId", claimID);
        object.addProperty("lesserBoundaryCorner", String.valueOf(lesserBoundaryCorner));
        object.addProperty("greaterBoundaryCorner", String.valueOf(greaterBoundaryCorner));

        return object;
    }

    public Location getLesserBoundaryCorner() {
        return lesserBoundaryCorner;
    }

    public Location getGreaterBoundaryCorner() {
        return greaterBoundaryCorner;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "ownerUUID=" + ownerUUID +
                ", claimID=" + claimID +
                ", lesserLocation" + lesserBoundaryCorner +
                ", greaterLocation" + greaterBoundaryCorner +
                '}';
    }

}
