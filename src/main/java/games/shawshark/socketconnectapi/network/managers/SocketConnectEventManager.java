package games.shawshark.socketconnectapi.network.managers;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.events.*;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;
import lombok.Getter;

import java.util.HashMap;

public class SocketConnectEventManager {
	
	@Getter private SocketConnectAPI lilyPadManager;
	@Getter private HashMap<String, SocketEvent> lilyEvents;
	
	public SocketConnectEventManager(SocketConnectAPI lilyPadManager) {
		this.lilyPadManager = lilyPadManager;
		
		this.lilyEvents = new HashMap<>();
		setupEvents();
	}
	
	public void setupEvents() {
		register(new AlertEvent(ChannelType.ALERT_ALL_SERVERS, getLilyPadManager()));
		register(new SendPlayersEvent(ChannelType.PLAYER_LIST, getLilyPadManager()));
		register(new VoteEvent(ChannelType.PLAYER_VOTE, getLilyPadManager()));
		register(new DisconnectCloud(ChannelType.DISCONNECT_CLOUD, getLilyPadManager()));
		register(new ForceSendPlayerList(ChannelType.FORCE_SEND_PLAYER_LIST, getLilyPadManager()));
		register(new ConnectCloud(ChannelType.CONNECT_CLOUD, getLilyPadManager()));
	}
	
	public void register(SocketEvent socketEvent) {
		String channel = socketEvent.getChannelType().toString();
		
		if(getLilyEvents().containsKey(channel)) {
			getLilyPadManager().getLogger().info(getLilyPadManager().getPrefix() + "Failed to register event (" + socketEvent.getChannelType().toString() + ") as it's already loaded to memory!");
		} else {
			getLilyEvents().put(channel, socketEvent);
			getLilyPadManager().getLogger().info(getLilyPadManager().getPrefix() + "Registered channel name: " + socketEvent.getChannelType().toString());
		}
	}
}