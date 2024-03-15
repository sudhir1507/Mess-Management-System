package org.mess.service;

import org.mess.model.AttendenceModel;
import org.mess.repository.AttendenceRepository;

public class AttendenceService {
	AttendenceRepository attRepo=new AttendenceRepository();
	public int markAttenedence(AttendenceModel amodel) {
		return attRepo.markAttenedence(amodel);
	}
}
