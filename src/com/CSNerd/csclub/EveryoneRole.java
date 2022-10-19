package com.CSNerd.csclub;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

public class EveryoneRole implements Command {

	@Override
	public String getTrigger() {
		return "erole";
	}

	@Override
	public void callCommand(String p, Message m) {
		List<Role>roles = m.getMember().getRoles();
		boolean perm = false;
		for(Role r: roles) {
			if(r.getId().equals(CSNerd.config.getExecID())) {
				perm = true;
				break;
			}
		}
		if(!perm) {
			m.reply("No permission.").queue();
			return;
		}
		
		List<Member>members = m.getGuild().getMembers();
		
		for(Member mem: members) {
			m.getGuild().addRoleToMember(mem.getId(), CSNerd.jda.getRoleById(CSNerd.config.getMemberID())).complete();
		}
		
		m.reply("Gave everyone member role.").queue();
	}

}
