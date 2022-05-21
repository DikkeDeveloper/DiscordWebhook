package dsrv.main;

import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.util.logging.Logger;

public class Events implements Listener {
    private String webhookURL = "https://discord.com/api/webhooks/977556208489730138/0wrOqI04tr6tO3FyXpuGQ1lgElGaWHRF3dvbIZqOsN_QW_msA3xXfzMIw9gKhwXjXU9W";

    private Logger logger;

    public Events(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        int pc = Bukkit.getOnlinePlayers().size();

        Player player = e.getPlayer();

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.LIGHT_GRAY)
                .setDescription("**" + player.getName() + "** has joined the server. (" + pc + "/" + Bukkit.getMaxPlayers() + ")"));
        try {
            webhook.execute();
        } catch (java.io.IOException event) {
            logger.severe(event.getStackTrace().toString());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        int pc = Bukkit.getOnlinePlayers().size();

        Player player = e.getPlayer();

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.darkGray)
                .setDescription("**" + player.getName() + "** has left the server. (" + pc + "/" + Bukkit.getMaxPlayers() + ")"));
        try {
            webhook.execute();
        } catch (java.io.IOException event) {
            logger.severe(event.getStackTrace().toString());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String Msg = e.getMessage();
        Player player = e.getPlayer();


        Msg = Msg.replaceAll("@everyone", "`@everyone`").replaceAll("@here", "`@here`");

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setColor(Color.white)
                .setAuthor("<" + player.getName() + "> " + Msg, "", "https://minotar.net/avatar/" + player.getName() + ".png"));
        try {
            webhook.execute();
        } catch (java.io.IOException event) {
            logger.severe(event.getStackTrace().toString());
        }
    }
}