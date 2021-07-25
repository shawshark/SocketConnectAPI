package games.shawshark.socketconnectapi.network.events.CustomBukkitEvents;

import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerDisconnectCloudEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	@Getter private String username;
	@Getter private UUID uuid;
	@Getter private Server disconnectFrom;
	
	public PlayerDisconnectCloudEvent(String username, UUID uuid, Server disconnectFrom) {
		this.username = username;
		this.uuid = uuid;
		this.disconnectFrom = disconnectFrom;
	}
	
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}