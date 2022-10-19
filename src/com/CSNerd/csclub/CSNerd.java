package com.CSNerd.csclub;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class CSNerd {

	static Config config;
	static JDA jda;
	static String parent;

	static HashMap<String, Command>cmdMap = new HashMap<String, Command>();

	private static final EnumSet<GatewayIntent> intent = EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS);

	public CSNerd() throws LoginException, InterruptedException, URISyntaxException {
		Console con = System.console(); 

		if(con == null) {
			//System.exit(0);
		}

		out("CS NERD DISCORD BOT");
		parent = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI()).getPath();

		out("Reading config.yml");
		if(!readConfigYML()) {
			out("- Bad Config");
			System.exit(0);
		}

		out("Auth:");
		char[]pwd = con.readPassword();
		char[]rpwd = config.getPwd().toCharArray();
		for(int i = 0; i < pwd.length; i++) {
			if(pwd[i] != rpwd[i]) {
				out("- Bad Auth");
				System.exit(0);
			}
		}

		out("Authorized");

		out("Building...");
		jda = JDABuilder.createDefault(config.getToken(), intent)
				.setChunkingFilter(ChunkingFilter.ALL)
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.build();
		jda.awaitReady();
		out("Done building");

		out("Setting status message...");
		jda.getPresence().setActivity(Activity.playing("at CS Club!"));
		out("Setting status...");
		jda.getPresence().setStatus(OnlineStatus.IDLE);

		out("Checking server ID...");
		boolean found = false;
		for (Guild guild : jda.getGuilds()) {
			if(guild.getId().equals(config.getServerID())) {
				found = true;
			}
		}
		if(!found) {
			out("- Invalid server ID");
			System.exit(0);
		}

		out("Mapping commands");
		cmdMap.put("ping", new PingPong());
		cmdMap.put("erole", new EveryoneRole());

		out("Adding listeners...");
		jda.addEventListener(new Events());
		out("Done!");

	}

	private void out(String string) {
		System.out.println(string);
	}

	private static boolean readConfigYML() {
		InputStream is;
		try {
			is = new FileInputStream(new File(parent + "/config.yml"));
			Yaml yml = new Yaml(new Constructor(Config.class));
			config = yml.load(is);
			if(!config.isValid()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
