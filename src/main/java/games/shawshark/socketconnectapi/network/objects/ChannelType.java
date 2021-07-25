package games.shawshark.socketconnectapi.network.objects;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChannelType {
    PLAYER_LIST("player_list"),
    PLAYER_VOTE("player_vote"),
    ALERT_ALL_SERVERS("alert_all_servers"),
    DISCONNECT_CLOUD("disconnect_cloud"),
    CONNECT_CLOUD("connect_cloud"),
    FORCE_SEND_PLAYER_LIST("force_send_player_list");

    private final String channelName;

    @Override
    public String toString() {
    	return "SocketConnectAPI-" + channelName;
    }
}