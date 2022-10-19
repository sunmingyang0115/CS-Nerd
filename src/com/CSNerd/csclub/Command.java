package com.CSNerd.csclub;

import net.dv8tion.jda.api.entities.Message;

public interface Command {
	String getTrigger();
	
	void callCommand(String p, Message m);
}
