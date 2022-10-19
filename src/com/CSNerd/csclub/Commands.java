package com.CSNerd.csclub;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Commands extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		Member member = e.getMember();
		Message message = e.getMessage();
		String raw = e.getMessage().getContentRaw();
		String rawL = raw.toLowerCase();
		
		if(member.getUser().isBot())
			return;

		System.out.println(e.getMessage().getContentDisplay());
		
		if(!rawL.startsWith(CSNerd.config.getPrefix()))
			return;
		CSNerd.cmdMap.get("ping").callCommand(raw.replace(CSNerd.config.getPrefix(), ""), message);
	}
}
