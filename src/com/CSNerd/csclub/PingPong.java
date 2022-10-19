package com.CSNerd.csclub;

import net.dv8tion.jda.api.entities.Message;

public class PingPong implements Command {

	@Override
	public String getTrigger() {
		// TODO Auto-generated method stub
		return "ping";
	}

	@Override
	public void callCommand(String p, Message m) {
		m.reply("Ping: ...", m -> {
			long ping = m.getTimeCreated().until(m.getTimeCreated(), ChronoUnit.MILLIS);
			m.editMessage("Pong! \uD83C\uDFD3 " + ping  + "ms").queue();
		});
	}

}