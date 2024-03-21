package org.mess.model;

import java.sql.Date;

public class BillModel {
     private Date bdate;
     private int bstatus;
     private int totalAmount;
     private int paid;
     private int remaining;
     private int rid;
     private int bid;
     public BillModel(Date bdate, int bstatus, int totalAmount, int paid, int remaining, int rid,int bid) {
		super();
		this.bdate = bdate;
		this.bstatus = bstatus;
		this.totalAmount = totalAmount;
		this.paid = paid;
		this.remaining = remaining;
		this.rid = rid;
		this.bid=bid;
	}
	public BillModel() {
    	 
     }
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
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
