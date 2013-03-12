package src.au.exoaria.champion;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Champion extends JavaPlugin {
	public static Bukkit plugin;
	public final Logger logger = Logger.getLogger("Minecraft");

	ChampionListener ChampL;

	@Override
	public void onEnable() {
		getConfig();
		this.saveDefaultConfig();

		ChampL = new ChampionListener(this);
		getServer().getPluginManager().registerEvents((Listener) ChampL, this);
	}

	@Override
	public void onDisable() {

	}

	public void writeToHighScoreTable(float Score) {
		// Code that writes the score
	}

}