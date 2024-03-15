package org.mess.model;

import java.sql.Date;

public class AttendenceModel {
	private int rid;
	private Date adate;
	private int status;
	public AttendenceModel() {
		
	}
	public AttendenceModel(int rid, Date adate, int status) {
		super();
		this.rid = rid;
		this.adate = adate;
		this.status = status;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
