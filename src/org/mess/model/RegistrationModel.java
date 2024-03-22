package org.mess.model;

import java.util.*;

public class RegistrationModel {
	private int rid;
	private String name;
	private String contact;
	private String address;
	private Date rsdate;
	private Date redate;
	private int amount;
	private String username;
	private String password;
	private int cid;
	public RegistrationModel() {
		
	}
	public RegistrationModel(String name, String contact, String address, Date rsdate,Date redate, int amount,
			String username, String password,int cid) {
		super();
		//this.rid = rid;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.rsdate = rsdate;
		this.redate=redate;
		this.amount = amount;
		this.username = username;
		this.password = password;
		this.cid=cid;
	}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRsdate() {
		return rsdate;
	}
	public Date getRedate() {
		return redate;
	}
	public void setRedate(Date redate) {
		this.redate = redate;
	}
	public void setRsdate(Date rsdate) {
		this.rsdate = rsdate;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
