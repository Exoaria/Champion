package src.au.exoaria.champion;

import java.util.HashMap;

public class HighScoreManager 
{
	Champion MainPlugin;
	
    private final HashMap<String, Integer> playerHighScores;

    public HighScoreManager()
    {
        this.playerHighScores = new HashMap<String,Integer>();
    }
    
}