package games.shawshark.socketconnectapi.network.managers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import lombok.Getter;
import net.shawshark.socketconnect.objects.Message;
import net.shawshark.socketconnect.objects.MessageEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MessagingManager {

	@Getter private SocketConnectAPI instance;
	
	public MessagingManager(SocketConnectAPI instance) {
	    this.instance = instance;
	}

    public void messageRequest(final ChannelType channelType, final String message, List<String> serverList) {
        try {
            if(serverList == null) {
                serverList = new ArrayList<>();
            }

            Message data = new Message();

            data.setId(String.valueOf(MessageEvent.getId()));
            data.setChannel(channelType.toString());
            data.setMessage(message);
            data.setToServers((serverList != null ? String.join(",", serverList) : ""));

            getInstance().getConnect().sendMessage(data);
        } catch (Exception ex) {
            throw new RuntimeException("Error while sending a message request." + ex);
        }
    }

    public void teleportRequest(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(getInstance().getPlugin(), "BungeeCord", out.toByteArray());
    }
}
