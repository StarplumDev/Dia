package network.starplum.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class TextManager {

	private final static int CENTER_PX = 154;
	private final static int MAX_PX = 250;

	public static void sendCenteredMessage(ProxiedPlayer player, String message) {
		final TextComponent compo = new TextComponent(message);
		if(message == null || message.equals("")) player.sendMessage(compo);
		message = ChatColor.translateAlternateColorCodes('&', message);

		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for(char c : message.toCharArray()) {
			if(c == '§') {
				previousCode = true;
				continue;
			} else if(previousCode == true) {
				previousCode = false;
				if(c == 'l' || c == 'L') {
					isBold = true;
					continue;
				} else isBold = false;
			} else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}

		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated < toCompensate){
			sb.append(" ");
			compensated += spaceLength;
		}
		final TextComponent component = new TextComponent(sb.toString() + message);
		player.sendMessage(component);
	}
	
	public static void sendCenteredMessage2(ProxiedPlayer player, String message) {
		message = ChatColor.translateAlternateColorCodes('&', message);
		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;
		int charIndex = 0;
		int lastSpaceIndex = 0;
		String toSendAfter = null;
		String recentColorCode = "";
		for(char c : message.toCharArray()) {
			if(c == '§') {
				previousCode = true;
				continue;
			} else if(previousCode == true) {
				previousCode = false;
				recentColorCode = "§" + c;
				if(c == 'l' || c == 'L') {
					isBold = true;
					continue;
				} else isBold = false;
			} else if(c == ' ') lastSpaceIndex = charIndex;
			else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
			if(messagePxSize >= MAX_PX) {
				toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1, message.length());
				message = message.substring(0, lastSpaceIndex + 1);
				break;
			}
			charIndex++;
		}
		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}
		final TextComponent component = new TextComponent(sb.toString() + message);
		player.sendMessage(component);
		if(toSendAfter != null) sendCenteredMessage(player, toSendAfter);
	}

}
