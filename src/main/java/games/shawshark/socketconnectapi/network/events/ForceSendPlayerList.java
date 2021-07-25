package games.shawshark.socketconnectapi.network.events;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;
import org.bukkit.Bukkit;

public class ForceSendPlayerList extends SocketEvent {

	public ForceSendPlayerList(ChannelType channelType, SocketConnectAPI instance) {
		super(channelType, instance);
	}

	@Override
	public void execute(String sender, String message) {
		try {
			Bukkit.getScheduler().runTaskAsynchronously(getInstance().getPlugin(), () -> {
				getInstance().getRunnableManager().forceplayersend();
			});
		} catch(Exception e) {
			System.out.println("[Network] Failed to execute force send in ForceSendPlayerList");
		}
	}
}
