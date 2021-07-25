package games.shawshark.socketconnectapi.network.managers;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.Server;
import games.shawshark.socketconnectapi.network.objects.ServerPlayersData;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

public class CachingManager {

	@Getter@Setter private SocketConnectAPI instance;
	@Getter@Setter private HashMap<String, Server> networkMap;
	@Getter@Setter private Server currentServer;

	public CachingManager(SocketConnectAPI instance) {
		this.instance = instance;
		setNetworkMap(new HashMap<>());
	}

    public void updateServer(String message, String sender) {

		ServerPlayersData data = ServerPlayersData.decode(message);
		if(data == null) {
			SocketConnectAPI.log("Failed to decode message for ServerPlayersData. Username: " + sender);
			return;
		}

		if(!getNetworkMap().containsKey(sender)) {
			Server server = new Server(sender, true);
			server.update(data);
			getNetworkMap().put(sender, server);
			SocketConnectAPI.log("Registered server " + sender + " into network map");

			// Set the current server to this server
			if(getInstance().getUsername().equalsIgnoreCase(sender)) {
				setCurrentServer(server);
			}
		} else {
			getNetworkMap().get(sender).update(data);
		}
    }


    public HashMap<String, String> getOnlinePlayerNames() {
		HashMap<String, String> onlinePlayers = new HashMap<>();
		for(Server server : getNetworkMap().values()) {
			for(String player : server.getPlayerNames()) {
				onlinePlayers.put(player, server.getServerUsername());
			}
		}
		return onlinePlayers;
	}

	public Server getServerByPlayerLookup(String player) {
		Server server = null;
		for(Server ser : getNetworkMap().values()) {
			if(ser.containsPlayer(player)) {
				server = ser;
				break;
			}
		}
		return server;
	}

	public Server getServerByPlayerUUIDLookup(UUID uuid) {
		Server server = null;
		for(Server temp : getNetworkMap().values()) {
			if(temp.getPlayerUUIDS().contains(uuid)) {
				server = temp;
				break;
			}
		}
		return server;
	}

	public Server getServer(String username) {
		Server server = null;
		for(Server ser : getNetworkMap().values()) {
			if(ser.getServerUsername().equalsIgnoreCase(username)) {
				server = ser;
				break;
			}
		}
		return server;
	}
}