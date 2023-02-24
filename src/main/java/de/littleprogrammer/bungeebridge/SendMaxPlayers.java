package de.littleprogrammer.bungeebridge;

import org.bukkit.Bukkit;

public class SendMaxPlayers {

    private Main main = Main.getInstance();
    private String serverName;
    private int maxPlayers;

    private String message;

    public SendMaxPlayers(){
        serverName = Bukkit.getServer().getName();
        maxPlayers = Bukkit.getMaxPlayers();

        message = serverName + ":" + maxPlayers; //"Lobby-1:100"

        main.getJedis().publish("maxPlayers", message);
    }

}
