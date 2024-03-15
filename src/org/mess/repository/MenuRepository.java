package org.mess.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository extends DBConfig {
	List list=new ArrayList(); 
		public boolean addMenu(String []weekm) {
			try {
				stmt=conn.prepareStatement("insert into weekmenu values(?,?,?,?)");
				stmt.setInt(1, Integer.parseInt(weekm[0]));
				Date mdate=Date.valueOf(weekm[1]);
				stmt.setDate(2, mdate);
				stmt.setString(3, weekm[2]);
				stmt.setString(4, weekm[3]);
				int value=stmt.executeUpdate();
				return value>0?true:false;
			}catch(Exception e) {
				System.err.println("Error is "+e);
				return false;
			}
		}
		public List getTodaysMenu(String d) {
			try {
				stmt=conn.prepareStatement("select * from weekmenu where mdate=?");
				Date mdate=Date.valueOf(d);
				stmt.setDate(1, mdate);
				rs=stmt.executeQuery();
				if(rs.next()) {
					list.add(rs.getDate(2));
					list.add(rs.getString(3));
					list.add(rs.getString(4));
					return list.size()>1?list:null;
				}
				return null;
			}catch(Exception e) {
				return null;
			}
		}
}
