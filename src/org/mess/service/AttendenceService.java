package org.mess.service;

import java.sql.Date;

import org.mess.model.AttendenceModel;
import org.mess.repository.AttendenceRepository;

public class AttendenceService {
	AttendenceRepository attRepo=new AttendenceRepository();
	public int markAttenedence(AttendenceModel amodel) {
		return attRepo.markAttenedence(amodel);
	}
	public int getAttendenceCount(int rid,Date rsdate,Date redate) {
		return attRepo.getAttendenceCount(rid,rsdate,redate);
	}
}
