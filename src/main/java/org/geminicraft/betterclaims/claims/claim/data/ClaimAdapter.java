package org.geminicraft.betterclaims.claims.claim.data;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.geminicraft.betterclaims.claims.claim.Claim;
import org.geminicraft.betterclaims.claims.claim.ClaimGroup;
import org.geminicraft.betterclaims.claims.util.ClaimLocationStringUtil;

import java.io.IOException;
import java.util.*;

public class ClaimAdapter extends TypeAdapter<Claim> {

    private final ClaimLocationStringUtil locationUtil = new ClaimLocationStringUtil();

    Set<ClaimGroup> groupList = new HashSet<>();

    private void createGroup(String name, Location location, ChatColor chatColor, Set<String> permissions) {
        ClaimGroup claimGroup = new ClaimGroup();
        claimGroup.setName(name);
        claimGroup.setSpawnLocation(location);
        claimGroup.setChatColor(chatColor);
        claimGroup.setPermissions(permissions);
        claimGroup.setPlayerUUIDs(new HashSet<>(Arrays.asList(UUID.fromString("3457f4bf-3616-4da2-aa1d-017b36f86662"))));

        groupList.add(claimGroup);
    }

    @Override
    public void write(JsonWriter jsonWriter, Claim claim) throws IOException {


        this.createGroup("Trusted",
                new Location(Bukkit.getWorld("world"), 0, 0, 0),
                ChatColor.GOLD,
                new HashSet<>(Arrays.asList(
                        "claim.can.chat",
                        "claim.can.teleport",
                        "claim.can.break.blocks",
                        "claim.can.place.blocks"
                )));




        jsonWriter.beginObject();

        jsonWriter.name("ownerUUID").value(claim.getOwnerUUID().toString());
        jsonWriter.name("claimId").value(claim.getClaimID());
        jsonWriter.name("lesserBoundaryCorner").value(locationUtil.getStringLocation(claim.getLesserBoundaryCorner()));
        jsonWriter.name("greaterBoundaryCorner").value(locationUtil.getStringLocation(claim.getGreaterBoundaryCorner()));

        jsonWriter.name("groups").beginArray();
        for (ClaimGroup group : groupList) {
            jsonWriter.beginObject();
            jsonWriter.name("name").value(group.getName());
            jsonWriter.name("spawnLocation").value(locationUtil.getStringLocation(group.getSpawnLocation()));
            jsonWriter.name("color").value(group.getChatColor().name());

            jsonWriter.name("players").beginArray();
            for (UUID playerUUID : group.getPlayerUUIDs()) {
                jsonWriter.value(playerUUID.toString());
            }
            jsonWriter.endArray();

            jsonWriter.name("permissions").beginArray();
            for (String permission : group.getPermissions()) {
                jsonWriter.value(permission);
            }
            jsonWriter.endArray();

            jsonWriter.endObject();
        }

        jsonWriter.endArray();

        jsonWriter.endObject();
    }

    // TODO: This code isn't clean and it's a priority to clean it up.
    @Override
    public Claim read(JsonReader jsonReader) throws IOException {
        Claim claim = new Claim();

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "ownerUUID":
                    claim.setOwnerUUID(UUID.fromString(jsonReader.nextString()));
                    break;
                case "claimId":
                    claim.setClaimID(jsonReader.nextLong());
                    break;
                case "lesserBoundaryCorner":
                    claim.setLesserBoundaryCorner(locationUtil.getLocationString(jsonReader.nextString()));
                    break;
                case "greaterBoundaryCorner":
                    claim.setGreaterBoundaryCorner(locationUtil.getLocationString(jsonReader.nextString()));
                    break;
                case "groups":
                    jsonReader.beginArray();
                    Set<ClaimGroup> claimGroups = new HashSet<>();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        ClaimGroup claimGroup = new ClaimGroup();

                        while (jsonReader.hasNext()) {
                            switch (jsonReader.nextName()) {
                                case "name":
                                    claimGroup.setName(jsonReader.nextString());
                                    break;
                                case "spawnLocation":
                                    claimGroup.setSpawnLocation(locationUtil.getLocationString(jsonReader.nextString()));
                                    break;
                                case "color":
                                    claimGroup.setChatColor(ChatColor.valueOf(jsonReader.nextString()));
                                    break;

                                case "players":
                                    jsonReader.beginArray();
                                    Set<UUID> playerUUIDs = new HashSet<>();
                                    while (jsonReader.hasNext()) {
                                        playerUUIDs.add(UUID.fromString(jsonReader.nextString()));
                                    }
                                    claimGroup.setPlayerUUIDs(playerUUIDs);
                                    jsonReader.endArray();
                                    break;
                                case "permissions":
                                    jsonReader.beginArray();
                                    Set<String> permissions = new HashSet<>();
                                    while (jsonReader.hasNext()) {
                                        permissions.add(jsonReader.nextString());
                                    }
                                    claimGroup.setPermissions(permissions);
                                    jsonReader.endArray();
                                    break;
                            }
                        }
                        jsonReader.endObject();
                        claimGroups.add(claimGroup);

                    }
                    claim.setClaimGroup(claimGroups);
                    jsonReader.endArray();
            }
        }
        jsonReader.endObject();

        return claim;
    }
}
