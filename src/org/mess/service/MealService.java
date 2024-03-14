package org.mess.service;

import org.mess.model.MealModel;
import org.mess.repository.MealRepository;

public class MealService {
	MealRepository mealRepo=new MealRepository();
	public boolean addMealType(MealModel mmodel) {
		return mealRepo.addMealType(mmodel);
	}
}
