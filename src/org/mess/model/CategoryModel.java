package org.mess.model;

public class CategoryModel {
	private int cid;
	private String category;
	private int fees;
	public CategoryModel() {
		
	}
	public CategoryModel(int cid, String category, int fees) {
		super();
		this.cid = cid;
		this.category = category;
		this.fees = fees;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	
}
