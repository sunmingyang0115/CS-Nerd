package com.CSNerd.csclub;

public class Config {
	private String pass;
	private char[] pwdChar;
	private String token;
	private String serverID;
	private String prefix;
	
	public Config() {}


	public void setPass(String pwd) {
		this.pass = pwd;
		this.pwdChar = pwd.toCharArray();
	}
	
	public char[] getPwd() {
		return pwdChar;
	}
	
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
