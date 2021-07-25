package games.shawshark.socketconnectapi.network.events;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;

public class SendPlayersEvent extends SocketEvent {

    public SendPlayersEvent(ChannelType channelType, SocketConnectAPI instance) {
        super(channelType, instance);
    }

    @Override
    public void execute(final String sender, final String message) {
        getInstance().getCachingManager().updateServer(message, sender);
    }
}