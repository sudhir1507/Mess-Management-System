package org.mess.repository;

import org.mess.model.AttendenceModel;


public class AttendenceRepository extends DBConfig {
	
	public int markAttenedence(AttendenceModel amodel) {
		try {
			stmt=conn.prepareStatement("insert into attendence values('0',?,?,?,?)");
			stmt.setInt(1, amodel.getRid());
			stmt.setDate(2, amodel.getAdate());
			stmt.setInt(3, amodel.getStatus());
			stmt.setInt(4, amodel.getMtid());
			int value=stmt.executeUpdate();
			return value>0?1:0;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
}
