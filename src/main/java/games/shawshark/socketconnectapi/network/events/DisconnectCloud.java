package games.shawshark.socketconnectapi.network.events;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;

public class DisconnectCloud extends SocketEvent {

	public static String SPLIT = "!";
	
	public DisconnectCloud(ChannelType channelType, SocketConnectAPI instance) {
		super(channelType, instance);
	}

	@Override
	public void execute(String sender, String message) {
		
		//String[] data = message.split(SPLIT);
		
		//String name = data[0];
		//UUID uuid = UUID.fromString(data[1]);
		//ProxyPlayers server = getLilyPadManager().getCachingManager().getByServerEqualsIgnoreCase(data[2]);
		
		//PlayerDisconnectCloudEvent disconnectCloud = new PlayerDisconnectCloudEvent(name, uuid, server);
		//Bukkit.getScheduler().runTask(getLilyPadManager().getPlugin(), () -> Bukkit.getPluginManager().callEvent(disconnectCloud));
	}
}
