package games.shawshark.socketconnectapi.network.objects;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Server implements Iterable<UUID> {

    @Getter private HashSet<String> playerNames;
    @Getter private HashSet<UUID> playerUUIDS;

    @Getter private String serverUsername;
    @Getter@Setter private String motd;
    @Getter@Setter private boolean status;
    @Getter private long lastUpdated;

    public Server(String username, boolean status) {
        playerNames = new HashSet<>();
        playerUUIDS = new HashSet<>();

        this.serverUsername = username;
        this.motd = "Unknown";
        this.status = status;
        this.lastUpdated = System.currentTimeMillis();
    }

    public void connect(Player playerName) {
        SocketConnectAPI.getManagerStatic().getMessagingManager().teleportRequest(playerName, getServerUsername());
    }

    public void update(ServerPlayersData data) {
        motd = data.getMotd();
        status = true;

        resetPlayers();

        if(data.getPlayerNames().length() > 3) {
            getPlayerNames().addAll(Arrays.asList(data.getPlayerNames().split(",")));
        }

        if(data.getPlayersID().length() > 3) {
            try {
                List<UUID> ids = Arrays.asList(data.getPlayersID().split(",")).stream()
                        .map(line -> UUID.fromString(line)).collect(Collectors.toList());
                playerUUIDS.addAll(ids);
            } catch(Exception e) {e.printStackTrace();}
        }
    }

    public int getPlayerCount() {
        return getPlayerNames().size();
    }

    public boolean containsPlayer(String name) {
        for(String username : getPlayerNames()) {
            if(username.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUUID(UUID uuid) {
        return getPlayerUUIDS().contains(uuid);
    }

    public void resetPlayers() {
        getPlayerNames().clear();
        getPlayerUUIDS().clear();
    }

    @Override
    public Iterator<UUID> iterator() {
        return getPlayerUUIDS().iterator();
    }

    public int convertTimer(long mstime) {
        long currenttime = System.currentTimeMillis();
        int current = (int) (currenttime / 1000);
        int saved = (int) (mstime / 1000);
        return current - saved;
    }

    public int getSecondsSinceLastReply() {
        return convertTimer(getLastUpdated());
    }
}
