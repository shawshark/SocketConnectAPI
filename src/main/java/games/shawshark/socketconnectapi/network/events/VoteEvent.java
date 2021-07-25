package games.shawshark.socketconnectapi.network.events;


import games.shawshark.socketconnectapi.network.SocketConnectAPI;
import games.shawshark.socketconnectapi.network.objects.ChannelType;
import games.shawshark.socketconnectapi.network.objects.SocketEvent;

public class VoteEvent extends SocketEvent {

	public VoteEvent(ChannelType channelType, SocketConnectAPI instance) {
		super(channelType, instance);
	}

	@Override
	public void execute(String sender, String message) {

		/**
		if(getLilyPadManager().getServerType().isLobbyServer()) {
			return;
		}
		
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				getLilyPadManager().getLogger().info(getLilyPadManager().getPrefix() + "Retrieving vote from Lobby server...");
				
				//String format = vote.getServiceName() + ":o:" + vote.getUsername() + ":o:" + vote.getAddress() + ":o:" + vote.getTimeStamp();
				String[] data = message.split(":o:");
				
				String serviceName = data[0];
				String username = data[1];
				String address = data[2];
				String timeStamp = data[3];
				
				getLilyPadManager().getLogger().info(getLilyPadManager().getPrefix() + "Vote {username= " + username + ", servicename= " + serviceName + ", address= " + address + "}");
				
				//Vote vote = new Vote();
				//vote.setServiceName(serviceName);
				//vote.setUsername(username);
				//vote.setAddress(address);
				//vote.setTimeStamp(timeStamp);
				
				//VotifierEvent voteEvent = new VotifierEvent(vote);
				//getLilyPadManager().getPlugin().getServer().getPluginManager().callEvent(voteEvent);
			}
		}.runTask(getLilyPadManager().getPlugin());*/
	}
}