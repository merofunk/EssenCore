package vk.com.merofunk.esscore.utilities;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class PrefixAboveHead 
{
	private static Team team;
	private static Scoreboard s;
	
	@SuppressWarnings("deprecation")
	public static void set(Player p, String prefix, TeamAction action)
	{
		if(p.getScoreboard() == null || prefix == null || action == null) return;
		
		s = p.getScoreboard();
		
		if(s.getTeam(p.getName()) == null) s.registerNewTeam(p.getName());
		
		team = s.getTeam(p.getName());
		team.setPrefix(prefix);
		team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		
		switch(action)
		{
		case CREATE:
			team.addPlayer(p);
			break;
		case UPDATE:
			team.unregister();
			s.registerNewTeam(p.getName());
			team = s.getTeam(p.getName());
			team.setPrefix(prefix);
			team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
			team.addPlayer(p);
			break;
		case DESTROY:
			team.unregister();
			break;
		}
	}
}
