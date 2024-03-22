package org.mess.repository;

import java.sql.Date;

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
	private int getCategoryIDByRid(int rid) {
		try {
			stmt = conn.prepareStatement("select cid from registration where rid=?");
			stmt.setInt(1, rid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception e) {
			System.err.println("Error is " + e);
			return 1;
		}

	}
	public int getAttendenceCount(int rid,Date rsdate,Date redate) {
		int count=0;
		try {
			stmt=conn.prepareStatement("select count(status) from attendence where (adate between ? and ?) and rid=?");
			
			stmt.setDate(1, rsdate);
			stmt.setDate(2, redate);
			stmt.setInt(3, rid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}else {
				count=0;
			}
			int cid=getCategoryIDByRid(rid);
			if(cid==1) {
				count=count/2;
			}
			return count;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
}
