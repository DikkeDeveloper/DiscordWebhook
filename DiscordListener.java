package dsrv.main;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public final class DiscordListener extends ListenerAdapter {

    private String webhookURL = "https://discord.com/api/webhooks/977556208489730138/0wrOqI04tr6tO3FyXpuGQ1lgElGaWHRF3dvbIZqOsN_QW_msA3xXfzMIw9gKhwXjXU9W";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getTextChannel().retrieveWebhooks().equals(webhookURL)) return;

        Member member = event.getMember();
        if (member == null || member.getUser().isBot()) return;

        String message = event.getMessage().getContentDisplay();
        Bukkit.broadcastMessage(ChatColor.BLUE + "<" + member.getEffectiveName() + ">" + ChatColor.RESET + " " + message);
    }
}