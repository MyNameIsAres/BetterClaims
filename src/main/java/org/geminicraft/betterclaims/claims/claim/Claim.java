package org.geminicraft.betterclaims.claims.claim;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.geminicraft.betterclaims.MainPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Claim {

    Location lesserBoundaryCorner, greaterBoundaryCorner;
    private UUID ownerUUID;
    Long claimID;


    private static List<Claim> claimList = new ArrayList<>();

    public static void addClaimToList(Claim claim) {
        claimList.add(claim);

        // For testing only
        claimList.forEach((claims) -> {
            System.out.println(claims.toString());
        });
    }

    public Claim() {
    }

    public Claim(UUID ownerUUID, Long claimID) {
        this.ownerUUID = ownerUUID;
        this.claimID = claimID;
    }

    public Claim(UUID ownerUUID, Long claimID, Location lesserBoundaryCorner, Location greaterBoundaryCorner) {
        this.ownerUUID = ownerUUID;
        this.claimID = claimID;
        this.lesserBoundaryCorner = lesserBoundaryCorner;
        this.greaterBoundaryCorner = greaterBoundaryCorner;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public Long getClaimID() {
        return claimID;
    }

    public static List<Claim> getClaimList() {
        return claimList;
    }

    public Location getLesserBoundaryCorner() {
        return lesserBoundaryCorner;
    }

    public Location getGreaterBoundaryCorner() {
        return greaterBoundaryCorner;
    }

    public void setLesserBoundaryCorner(Location lesserBoundaryCorner) {
        this.lesserBoundaryCorner = lesserBoundaryCorner;
    }

    public void setGreaterBoundaryCorner(Location greaterBoundaryCorner) {
        this.greaterBoundaryCorner = greaterBoundaryCorner;
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public void setClaimID(Long claimID) {
        this.claimID = claimID;
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
