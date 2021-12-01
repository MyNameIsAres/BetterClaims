package org.geminicraft.betterclaims.claims.claim.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.util.ClaimLocationUtil;

import java.io.IOException;
import java.util.UUID;

public class ClaimAdapter extends TypeAdapter<Claim> {

    private final ClaimLocationUtil locationUtil = new ClaimLocationUtil();

    @Override
    public void write(JsonWriter jsonWriter, Claim claim) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ownerUUID").value(claim.getOwnerUUID().toString());
        jsonWriter.name("claimId").value(claim.getClaimID());
        jsonWriter.name("lesserBoundaryCorner").value(locationUtil.getStringLocation(claim.getLesserBoundaryCorner()));
        jsonWriter.name("greaterBoundaryCorner").value(locationUtil.getStringLocation(claim.getGreaterBoundaryCorner()));
        jsonWriter.endObject();
    }

    // TODO: Could definitely use a bit of code clean-up.
    @Override
    public Claim read(JsonReader jsonReader) throws IOException {
        Claim claim = new Claim();
        String propertyKey = "";
        jsonReader.beginObject();

        while(jsonReader.hasNext()) {
            JsonToken token = jsonReader.peek();

            if (token.equals(JsonToken.NAME)) {
                propertyKey = jsonReader.nextName();
            }
            if ("ownerUUID".equals(propertyKey)) {
                claim.setOwnerUUID(UUID.fromString(jsonReader.nextString()));
                System.out.println("Got ownerUUID");
            }
            if ("claimId".equals(propertyKey)) {
                claim.setClaimID(jsonReader.nextLong());
                System.out.println("Got ClaimID");
            }
            if ("lesserBoundaryCorner".equals(propertyKey)) {
                claim.setLesserBoundaryCorner(locationUtil.getLocationString(jsonReader.nextString()));
            }
            if ("greaterBoundaryCorner".equals(propertyKey)) {
                claim.setGreaterBoundaryCorner(locationUtil.getLocationString(jsonReader.nextString()));
            }
        }
        jsonReader.endObject();

        return claim;
    }
}
