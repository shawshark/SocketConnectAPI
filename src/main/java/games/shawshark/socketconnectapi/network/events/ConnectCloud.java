package games.shawshark.socketconnectapi.network.events;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;

public class ConnectCloud extends SocketEvent {

	public ConnectCloud(ChannelType channelType, SocketConnectAPI instance) {
		super(channelType, instance);
	}

	@Override
	public void execute(String sender, String message) {

		//String[] data = message.split(DisconnectCloud.SPLIT);
		
		//String name = data[0];
		//UUID uuid = UUID.fromString(data[1]);
		//Server server = getInstance().getCachingManager().getByServerEqualsIgnoreCase(data[2]);
		
		//PlayerConnectCloudEvent connectCloud = new PlayerConnectCloudEvent(name, uuid, server);
		//Bukkit.getScheduler().runTask(getLilyPadManager().getPlugin(), () -> Bukkit.getPluginManager().callEvent(connectCloud));
	}

}
