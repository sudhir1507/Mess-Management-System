package org.mess.model;

public class MealModel {
    private int mtid;
    private String mealtype;
    public MealModel() {
    	
    }
    
	public MealModel(int mtid, String mealtype) {
		
		this.mtid = mtid;
		this.mealtype = mealtype;
	}

	public int getMtid() {
		return mtid;
	}
	public void setMtid(int mtid) {
		this.mtid = mtid;
	}
	public String getMealtype() {
		return mealtype;
	}
	public void setMealtype(String mealtype) {
		this.mealtype = mealtype;
	}
    
}
