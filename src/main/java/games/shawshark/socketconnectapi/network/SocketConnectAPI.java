package games.shawshark.socketconnectapi.network;

import games.shawshark.socketconnectapi.network.managers.CachingManager;
import games.shawshark.socketconnectapi.network.managers.MessagingManager;
import games.shawshark.socketconnectapi.network.managers.RunnableManager;
import games.shawshark.socketconnectapi.network.managers.SocketConnectEventManager;
import lombok.Getter;
import net.shawshark.socketconnect.BridgeConnect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class SocketConnectAPI implements PluginMessageListener {

	@Getter private Plugin plugin;
	@Getter private static SocketConnectAPI managerStatic = null;
	@Getter private Logger logger;
	@Getter private String prefix = "[SocketConnectAPI] ";
	
	@Getter private String username;
	@Getter private BridgeConnect connect;

	@Getter private CachingManager cachingManager;
	@Getter private MessagingManager messagingManager;
	@Getter private RunnableManager runnableManager;
	
	@Getter private SocketConnectEventManager socketConnectEventManager;
	@Getter private SocketConnectListener socketConnectListener;
	
	public SocketConnectAPI(Plugin plugin) {
		this.plugin = plugin;
		managerStatic = this;

		this.logger = plugin.getLogger();
		getLogger().info(getPrefix() + "Starting SocketConnect services...");

		// Setup Connect
		this.connect = BridgeConnect.getConnect();
		this.username = getConnect().getNetwork().getUsername();

		// Caching Manager
		this.cachingManager = new CachingManager(this);
		
		// Messaging Manager
		this.messagingManager = new MessagingManager(this);
		
		// Runnable Manager
		this.runnableManager = new RunnableManager(this);

		// Events & Listeners
		this.socketConnectEventManager = new SocketConnectEventManager(this);
		this.socketConnectListener = new SocketConnectListener(this);

		//TODO fix placeholders
		//registerServerPlaceholders();

		getPlugin().getServer().getMessenger().registerOutgoingPluginChannel(getPlugin(), "BungeeCord");
		getPlugin().getServer().getMessenger().registerIncomingPluginChannel(getPlugin(), "BungeeCord", this);
	}

	@Override
	public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
	}

	public static void log(String message) {
    	getManagerStatic().getLogger().info(message);
	}
}
