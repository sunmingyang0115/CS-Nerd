package com.CSNerd.csclub;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import QOTDBot.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class CSNerd {

	static Config config;
	static JDA jda;
	static String parent;

	private static final EnumSet<GatewayIntent> intent = EnumSet.of(GatewayIntent.GUILD_MESSAGES);
	
	public CSNerd() throws LoginException, InterruptedException, URISyntaxException {
		parent = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI()).getPath();
		
		if(!readConfigYML()) {
			System.out.println("Bad Config");
			System.exit(0);
		}
		
		jda = JDABuilder.createDefault(config.getToken(), intent).build();
		jda.awaitReady();
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
