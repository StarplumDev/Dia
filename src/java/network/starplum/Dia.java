package network.starplum;

import net.md_5.bungee.api.plugin.Plugin;
import network.starplum.listener.ProtocolListener;

public class Dia extends Plugin {
	
	private static Dia dia;
	public static Dia getDia() { return dia; }
	
	@Override public void onEnable() {
		dia = this;
		new ProtocolListener();
	}

}
