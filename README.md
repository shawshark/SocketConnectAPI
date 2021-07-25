# SocketConnectAPI


This is an api for https://github.com/shawshark/SocketConnect



You must install https://github.com/shawshark/SocketConnect on your bukkit servers & start up the server by putting (https://github.com/shawshark/SocketConnect) into a folder and starting it up java -jar SocketConnect.jar

For devs
		try {
			if(getServer().getPluginManager().getPlugin("SocketConnect") != null) {
				new SocketConnectAPI(this);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
