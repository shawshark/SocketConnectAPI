package games.shawshark.socketconnectapi.network.events;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class AlertEvent extends SocketEvent {

	public AlertEvent(ChannelType channelType, SocketConnectAPI instance) {
		super(channelType, instance);
	}

	@Override
	public void execute(String sender, String message) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}