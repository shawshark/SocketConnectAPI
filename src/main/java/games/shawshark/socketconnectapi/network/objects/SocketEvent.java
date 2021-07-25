package games.shawshark.socketconnectapi.network.objects;

import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import lombok.Getter;

public abstract class SocketEvent {

    @Getter private final ChannelType channelType;
    @Getter private final SocketConnectAPI instance;

    public SocketEvent(ChannelType channelType, SocketConnectAPI instance) {
        this.channelType = channelType;
        this.instance = instance;
    }

    public abstract void execute(String sender, String message);

    @Override
    public String toString() {
        return String.format("Event(channel=%s)", channelType.toString());
    }
}