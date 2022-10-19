package com.CSNerd.csclub;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Events extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		Member member = e.getMember();
		Message message = e.getMessage();
		String raw = e.getMessage().getContentRaw();
		String rawL = raw.toLowerCase();
		
		if(!e.getGuild().getId().equals(CSNerd.config.getServerID()))
			return;
		
		if(member.getUser().isBot())
			return;

		System.out.println(e.getMessage().getContentDisplay());
		
		if(!rawL.startsWith(CSNerd.config.getPrefix()))
			return;
		
		String cmd = rawL.replace(CSNerd.config.getPrefix(), "").split(" ")[0];
		
		CSNerd.cmdMap.get(cmd).callCommand(raw.replace(CSNerd.config.getPrefix(), ""), message);
	}
	

	public void onGuildJoin(GuildJoinEvent e) {
		
	}
	
}
