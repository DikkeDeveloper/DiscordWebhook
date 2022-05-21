package dsrv.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public final class Main extends JavaPlugin {
    private String webhookURL = "https://discord.com/api/webhooks/977556208489730138/0wrOqI04tr6tO3FyXpuGQ1lgElGaWHRF3dvbIZqOsN_QW_msA3xXfzMIw9gKhwXjXU9W";

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Events(getLogger()), this);

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setColor(Color.green).setDescription("<a:online:977559642051870790> Server is starting"));
        try {
            webhook.execute();
        }
        catch (java.io.IOException e) {
            getLogger().severe(e.getStackTrace().toString());
        }

        loadConfig();

    }


    public void onDisable() {
        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setColor(Color.red).setDescription("<a:offline:977559642026696704> Server is stopping"));
        try {
            webhook.execute();
        }
        catch (java.io.IOException e) {
            getLogger().severe(e.getStackTrace().toString());
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}