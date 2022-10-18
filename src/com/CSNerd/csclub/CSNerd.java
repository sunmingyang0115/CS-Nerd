package com.CSNerd.csclub;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class CSNerd {

	static Config config;
	static JDA jda;
	static String parent;

	private static final EnumSet<GatewayIntent> intent = EnumSet.of(GatewayIntent.GUILD_MESSAGES);
	
	public CSNerd() throws LoginException, InterruptedException, URISyntaxException {
		Console con = System.console(); 
		
//		if(con == null) {
//			System.exit(0);
//		}
		
		out("CS NERD DISCORD BOT");
		parent = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI()).getPath();
		
		out("Reading config.yml");
		if(!readConfigYML()) {
			System.out.println("- Bad Config");
			System.exit(0);
		}
		
		out("Auth:");
		char[]pwd = con.readPassword();
		if(pwd != config.getPwd()) {
			out("- Bad Auth");
			System.exit(0);
		}
		
		out("Building");
		jda = JDABuilder.createDefault(config.getToken(), intent).build();
		jda.awaitReady();
		out("Done building");
		
		
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
