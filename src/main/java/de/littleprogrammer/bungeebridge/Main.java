package de.littleprogrammer.bungeebridge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import redis.clients.jedis.JedisPooled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {

    private static Main instance;
    private JedisPooled jedis;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;



        //Jedis
        jedis = new JedisPooled("localhost", 6379);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (jedis.get("ping").equalsIgnoreCase("1")){
                jedis.set("ping", "0");
            }else {
                jedis.set("ping", "1");
            }
        }, 1150, 1200);


        //Jedis Connect
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(() -> jedis.subscribe(new JedisTerminal("onlyOne"), "test"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Main getInstance() {
        return instance;
    }

    public JedisPooled getJedis() {
        return jedis;
    }
}
