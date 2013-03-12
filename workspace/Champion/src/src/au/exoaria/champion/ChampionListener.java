package src.au.exoaria.champion;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChampionListener implements Listener {

	public static HashMap<String, Integer> deathHash = new HashMap<String, Integer>();

	Champion MainPlugin;

	public ChampionListener(Champion c) {
		MainPlugin = c;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void hashname(PlayerJoinEvent joinEvent) {
		joinEvent.getPlayer().sendMessage("Welcome, Champion.");
		String player = joinEvent.getPlayer().getName();
		deathHash.put(player, (int) System.currentTimeMillis());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent deathEvent) {
		String player = deathEvent.getEntity().getName();
		Player p = deathEvent.getEntity();
		int survivalTime = (int) (System.currentTimeMillis() - deathHash
				.get(player));
		int secondSwap = survivalTime / 1000;
		int survivalTimeSeconds = secondSwap % 60;
		int survivalTimeMinutes = secondSwap % 3600 / 60;
		int survivalTimeHours = secondSwap / 3600;

		p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.GOLD + "Champion"
				+ ChatColor.DARK_RED + "]" + ChatColor.WHITE
				+ " You have survived " + ChatColor.GOLD + survivalTimeHours
				+ ChatColor.WHITE + " hours, " + ChatColor.GOLD
				+ survivalTimeMinutes + ChatColor.WHITE + " minutes, and "
				+ ChatColor.GOLD + survivalTimeSeconds + ChatColor.WHITE
				+ " seconds.");

		String currentScore = (String) MainPlugin.getConfig().get(
				"deaths." + p.getName());
		if (Integer.parseInt(currentScore) < System.currentTimeMillis()) {

			MainPlugin.getConfig().set("death." + p.getName(),
					System.currentTimeMillis());

			p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.GOLD
					+ "Champion" + ChatColor.DARK_RED + "]" + ChatColor.WHITE
					+ " Congratulations! You beat your last best time of "
					+ ChatColor.GOLD + survivalTimeHours + ChatColor.WHITE
					+ " hours, " + ChatColor.GOLD + survivalTimeMinutes
					+ ChatColor.WHITE + " minutes, and " + ChatColor.GOLD
					+ survivalTimeSeconds + ChatColor.WHITE
					+ " seconds! Better luck next time!");

		}

	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent respawnEvent) {
		String player = respawnEvent.getPlayer().getName();
		deathHash.put(player, (int) System.currentTimeMillis());
	}
}
