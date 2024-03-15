package org.mess.model;

import java.sql.Date;

public class MenuModel {
    private int mid;
    private Date date;
    private String Lunch;
    private String Dinner;
    public MenuModel() {
    	
    }
    
	public MenuModel(int mid, Date date, String lunch, String dinner) {
		super();
		this.mid = mid;
		this.date = date;
		Lunch = lunch;
		Dinner = dinner;
	}

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLunch() {
		return Lunch;
	}
	public void setLunch(String lunch) {
		Lunch = lunch;
	}
	public String getDinner() {
		return Dinner;
	}
	public void setDinner(String dinner) {
		Dinner = dinner;
	}
    
}
