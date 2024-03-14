package org.mess.repository;

import org.mess.model.MealModel;

public class MealRepository extends DBConfig {
      public boolean addMealType(MealModel mmodel) {
    	  try {
    		  stmt=conn.prepareStatement("insert into mealtype values(?,?)");
    		  stmt.setInt(1, mmodel.getMtid());
    		  stmt.setString(2, mmodel.getMealtype());
    		  
    		  return stmt.executeUpdate()>0?true:false;
    	  }catch(Exception e) {
    		  System.err.println("Error is "+e);
    		  return false;
    	  }
      }
}
