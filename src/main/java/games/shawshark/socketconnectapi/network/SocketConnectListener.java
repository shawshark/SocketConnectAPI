package games.shawshark.socketconnectapi.network;

import games.shawshark.socketconnectapi.network.objects.SocketEvent;
import lombok.Getter;
import net.shawshark.socketconnect.objects.Message;
import net.shawshark.socketconnect.request.Listener;

public class SocketConnectListener implements Listener {

	@Getter private SocketConnectAPI instance;
	
	public SocketConnectListener(SocketConnectAPI instance) {
		this.instance = instance;
        instance.getConnect().registerListener(this);
	}

    @Override
    public void execute(Message m) {

	    try {
            String sender = m.getFromServer();
            String message = m.getMessage();
            String channel = m.getChannel();

            if (getInstance().getSocketConnectEventManager().getLilyEvents().containsKey(channel)) {
                SocketEvent lilyEvent = getInstance().getSocketConnectEventManager().getLilyEvents().get(channel);
                lilyEvent.execute(sender, message);
            }

        } catch (Exception ex) {
            System.out.println("Error listening for socketconnect msg:");
            System.out.println("Sender: " + m.getFromServer());
            System.out.println("Channel" + m.getChannel());
            System.out.println("Message: " + m.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public String getListenerName() {
        return "Main core listener";
    }
}
