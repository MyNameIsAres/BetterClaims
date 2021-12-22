package org.geminicraft.betterclaims.claims.claim;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ClaimGroup {

    private String name;

    private Location spawnLocation;

    private ChatColor chatColor;

    private Set<String> permissions = new HashSet<>();

    private Set<UUID> playerUUIDs = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<UUID> getPlayerUUIDs() {
        return playerUUIDs;
    }

    public void setPlayerUUIDs(Set<UUID> playerUUIDs) {
        this.playerUUIDs = playerUUIDs;
    }


}
