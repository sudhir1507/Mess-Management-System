package org.mess.model;

import java.sql.Date;

public class BillModel {
     private Date bdate;
     private int bstatus;
     public BillModel() {
    	 
     }
	public BillModel(Date bdate, int bstatus) {
		super();
		this.bdate = bdate;
		this.bstatus = bstatus;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getBstatus() {
		return bstatus;
	}
	public void setBstatus(int bstatus) {
		this.bstatus = bstatus;
	}
     
}
