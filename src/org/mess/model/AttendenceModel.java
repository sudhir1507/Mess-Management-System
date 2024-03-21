package org.mess.model;

import java.sql.Date;

public class AttendenceModel {
	private int rid;
	private Date adate;
	private int status;
	private int mtid;
	public AttendenceModel() {
		
	}
	public AttendenceModel(int rid, Date adate, int status,int mtid) {
		super();
		this.rid = rid;
		this.adate = adate;
		this.status = status;
		this.mtid=mtid;
	}
	public int getMtid() {
		return mtid;
	}
	public void setMtid(int mtid) {
		this.mtid = mtid;
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
