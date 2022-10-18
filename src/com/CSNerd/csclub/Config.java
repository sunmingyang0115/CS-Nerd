package com.CSNerd.csclub;

public class Config {
	private String token;
	private String serverID;
	private String prefix;
	
	public Config() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
