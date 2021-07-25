package games.shawshark.socketconnectapi.network.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import lombok.Getter;

public class ServerPlayersData {

    private static Gson gson = new Gson();

    @SerializedName("user")
    @Getter private String serverUsername;
    @SerializedName("motd")
    @Getter private String motd;
    @SerializedName("ids")
    @Getter private String playersID;
    @SerializedName("names")
    @Getter private String playerNames;

    public ServerPlayersData(String serverUsername, String motd, String playersID, String playerNames) {
        this.serverUsername = serverUsername;
        this.motd = motd;
        this.playersID = playersID;
        this.playerNames = playerNames;
    }

    public ServerPlayersData() {
    }

    @Override
    public String toString() {
        return "Server username: " + serverUsername + ", Motd: " + motd + ", players uuid size: " +
                playersID + ", players names size: " + playerNames;
     }

    public static ServerPlayersData decode(String string) {
        try {
            return gson.fromJson(string, ServerPlayersData.class);
        } catch(Exception e) {
            SocketConnectAPI.log("[ServerPlayerData] Failed to decode: " + string);
            return null;
        }
    }

    public static String encode(ServerPlayersData data) {
        return gson.toJson(data);
    }
}