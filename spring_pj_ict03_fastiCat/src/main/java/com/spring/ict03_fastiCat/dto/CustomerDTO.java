package com.spring.ict03_fastiCat.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CustomerDTO {
	
	//멤버변수
	private String userid;
   	private String password;
   	private String username;
   	private Date birthday;
   	private String address;
   	private String hp;
   	private String email;
   	private Timestamp regDate;
   	private String  	show;
   
   	//시큐리티
   	private String key;
   	private String authority;
   	private String enabled;
   
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(String userid, String password, String username, Date birthday, String address, String hp,
			String email, Timestamp regDate, String show, String key, String authority, String enabled) {
		super();
		this.userid = userid;
		this.password = password;
		this.username = username;
		this.birthday = birthday;
		this.address = address;
		this.hp = hp;
		this.email = email;
		this.regDate = regDate;
		this.show = show;
		this.key = key;
		this.authority = authority;
		this.enabled = enabled;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "CustomerDTO [userid=" + userid + ", password=" + password + ", username=" + username + ", birthday="
				+ birthday + ", address=" + address + ", hp=" + hp + ", email=" + email + ", regDate=" + regDate
				+ ", show=" + show + ", key=" + key + ", authority=" + authority + ", enabled=" + enabled
				+ ", getUserid()=" + getUserid() + ", getPassword()=" + getPassword() + ", getUsername()="
				+ getUsername() + ", getBirthday()=" + getBirthday() + ", getAddress()=" + getAddress() + ", getHp()="
				+ getHp() + ", getEmail()=" + getEmail() + ", getRegDate()=" + getRegDate() + ", getShow()=" + getShow()
				+ ", getKey()=" + getKey() + ", getAuthority()=" + getAuthority() + ", getEnabled()=" + getEnabled()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	   
}
