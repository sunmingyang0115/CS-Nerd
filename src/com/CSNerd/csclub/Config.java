package com.CSNerd.csclub;

public class Config {
	
	private String pwd;
	private String token;
	private String serverID;
	private String prefix;
	private String memberID;
	private String execID;
	
	public Config() {}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public boolean isValid() {
		if(pwd.isBlank())
			return false;
		
		if(token.isBlank())
			return false;

		if(serverID.isBlank())
			return false;

		if(prefix.isBlank())
			return false;
		
		return true;
	}

	public String getExecID() {
		return execID;
	}

	public void setExecID(String execID) {
		this.execID = execID;
	}
	
}
