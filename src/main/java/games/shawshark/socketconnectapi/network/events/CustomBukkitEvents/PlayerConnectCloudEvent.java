package games.shawshark.socketconnectapi.network.events.CustomBukkitEvents;

import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PlayerConnectCloudEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	@Getter private String username;
	@Getter private UUID uuid;
	@Getter private Server connectTo;
	
	public PlayerConnectCloudEvent(String username, UUID uuid, Server connectTo) {
		this.username = username;
		this.uuid = uuid;
		this.connectTo = connectTo;
	}
	
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}