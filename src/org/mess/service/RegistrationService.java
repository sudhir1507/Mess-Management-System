package org.mess.service;

import org.mess.model.RegistrationModel;
import org.mess.repository.RegistrationRepository;

public class RegistrationService {
	RegistrationRepository regRepo=new RegistrationRepository();
	public boolean addRegistration(RegistrationModel rmodel,String category) {
		return regRepo.addRegistration(rmodel,category);
	}
	public int getCurrentRegID() {
		return regRepo.getCurrentRegID();
	}
	public RegistrationModel getLoginInfo(int rid) {
		return regRepo.getLoginInfo(rid);
	}
	public void addCatMealReg(int rid,String category,String mealType) {
		regRepo.addCatMealReg(rid, category, mealType);
	}
}
