package network.starplum.listener;

import static network.starplum.util.TextManager.sendCenteredMessage;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import network.starplum.Dia;
import network.starplum.type.ClientVersion;

public class ProtocolListener implements Listener {
	
	public ProtocolListener() { ProxyServer.getInstance().getPluginManager().registerListener(Dia.getDia(), this); }

	@EventHandler public void listen(PostLoginEvent event) {
		
		final int version = event.getPlayer().getPendingConnection().getVersion();
		final ProxiedPlayer player = event.getPlayer();
		
		if(version != ClientVersion.V1_12_2.getValue()) {
			sendCenteredMessage(player, "§8§m-------------------------------------------------");
			sendCenteredMessage(player, "§fPlease notice that the server is still in beta!");
			sendCenteredMessage(player, "§fIf you see this message, this mean that your version");
			sendCenteredMessage(player, "§fcan be unstable. Please report bugs on our Discord!");
			sendCenteredMessage(player, "§8● §5Discord§d discord.starplum.fun");
			sendCenteredMessage(player, "§8§m-------------------------------------------------");
		}
	}

}
