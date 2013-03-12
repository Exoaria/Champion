package au.exoaria.champion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChampionListener implements Listener {

	public static HashMap<String, Long> playerLoginTime = new HashMap<String, Long>();
	public static HashMap<String, Long> playerRespawnTime = new HashMap<String, Long>();

	Champion MainPlugin;
	
	public ChampionListener(Champion c){
		MainPlugin = c;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void hashname(PlayerJoinEvent joinEvent) {
		joinEvent.getPlayer().sendMessage("Welcome, Champion.");
		
		String player = joinEvent.getPlayer().getName();
		playerLoginTime.put(player, System.currentTimeMillis());
	}
	
	@EventHandler
	public void hashnameDeath(PlayerRespawnEvent respawnEvent){
		String player = respawnEvent.getPlayer().getName();
		Player p = respawnEvent.getPlayer();
		playerRespawnTime.put(player, System.currentTimeMillis());
		
		float timeSurvived = playerRespawnTime.get(player) - 
				playerLoginTime.get(player);
		
		float timeSurvivedInSeconds = timeSurvived / 1000;
		
		float timeSurvivedInMinuets = timeSurvivedInSeconds / 60;
		
		if(timeSurvivedInMinuets > 1){
			p.sendMessage("You survived for: " + timeSurvivedInMinuets + " Minuets");
		}
		else if(timeSurvivedInSeconds > 1){
			p.sendMessage("You survived for: " + timeSurvivedInSeconds + " Seconds");
		}
		else{
			p.sendMessage("You survived for: " + timeSurvived + " Miliseconds, pathetic");
		}
		
		MainPlugin.writeToHighScoreTable(timeSurvivedInMinuets);
		playerLoginTime.put(player, System.currentTimeMillis());
	}
}
