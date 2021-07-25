package games.shawshark.socketconnectapi.network.events.CustomBukkitEvents;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerServerTeleportEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();

	@Getter@Setter public String player;
	@Getter@Setter public Server from, to;
	@Getter@Setter public boolean canncelled = false;
	@Getter@Setter public int delay = 0;
	
	public PlayerServerTeleportEvent(String player , Server from, Server to) {
		setPlayer(player);
		setFrom(from);
		setTo(to);
	}
	
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList() {
	    return HANDLERS;
	}
}
