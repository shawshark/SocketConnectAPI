package games.shawshark.socketconnectapi.network.managers;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.Server;
import games.shawshark.socketconnectapi.network.objects.ServerPlayersData;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

public class RunnableManager extends BukkitRunnable{

	@Getter private SocketConnectAPI instance;

    public RunnableManager(SocketConnectAPI instance) {
    	this.instance = instance;
        runTaskTimerAsynchronously(instance.getPlugin(), 20, 110);

        startCheckOfflineTask();
    }

    @Override
    public void run() {
        forceplayersend();
    }
    
    public void forceplayersend() {

        String motd = "Failed {reason: Failed to fire ServerListPingEvent}";

        try {
            ServerListPingEvent event = new ServerListPingEvent(InetAddress.getLocalHost(),
                    Bukkit.getMotd(), Bukkit.getOnlinePlayers().size(), Bukkit.getMaxPlayers());
            Bukkit.getPluginManager().callEvent(event);

            motd = event.getMotd();
        } catch (Exception e) {
            System.out.println("[Network] Failed to call ServerListPingEvent in Runnable Manager");
        }

        String username = getInstance().getUsername();
        List<String> playerIds = Bukkit.getOnlinePlayers()
                .stream()
                .map(player -> player.getUniqueId().toString())
                .collect(Collectors.toList());
        List<String> playerNames = Bukkit.getOnlinePlayers()
                .stream()
                .map(player -> player.getName())
                .collect(Collectors.toList());

        ServerPlayersData serverData = new ServerPlayersData(username, motd,
                String.join(",", playerIds), String.join(",", playerNames));

        getInstance().getMessagingManager()
                .messageRequest(ChannelType.PLAYER_LIST, ServerPlayersData.encode(serverData), null);
    }

    private void startCheckOfflineTask() {
        Bukkit.getScheduler().runTaskTimer(getInstance().getPlugin(), () -> {
            for(Server server : getInstance().getCachingManager().getNetworkMap().values()) {
                if(server.getSecondsSinceLastReply() > 16) {
                    server.setStatus(false);
                    server.setMotd("Offline - Last Reply > 16");
                    server.resetPlayers();
                }
            }
        }, 200, 200);
    }
}