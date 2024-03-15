package org.mess.repository;

import java.sql.Date;

import org.mess.model.MealModel;
import org.mess.model.RegistrationModel;

public class RegistrationRepository extends DBConfig {
	RegistrationModel model=new RegistrationModel();
	MealModel mmodel=new MealModel();
	public int getMealIDByName(String mealType) {
		try {
			stmt=conn.prepareStatement("select mtid from mealtype where mealtime=?");
			stmt.setString(1, mealType);
			rs=stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
 	public int getCategoryIDByName(String category) {
		try {
			stmt = conn.prepareStatement("select cid from category where category=?");
			stmt.setString(1, category);
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

	public int getCurrentRegID() {
		int regid = 0;
		try {
			stmt = conn.prepareStatement("select rid from registration order by rid desc limit 1");
			rs = stmt.executeQuery();
			if (rs.next()) {
				regid = rs.getInt(1);
				return regid;
			} else {
				return -1;
			}

		} catch (Exception e) {
			System.err.println("Error is " + e);
			return 0;
		}

	}

	public boolean addRegistration(RegistrationModel rmodel, String category) {
		try {
			int cid = getCategoryIDByName(category);
			stmt = conn.prepareStatement("insert into registration values('0',?,?,?,?,?,?,?,?)");
			stmt.setString(1, rmodel.getName());
			stmt.setString(2, rmodel.getContact());
			stmt.setString(3, rmodel.getAddress());
			stmt.setDate(4, (Date) rmodel.getRsdate());
			stmt.setDate(5, (Date) rmodel.getRedate());
			stmt.setInt(6, rmodel.getAmount());
			stmt.setString(7, rmodel.getUsername());
			stmt.setString(8, rmodel.getPassword());
			int value = stmt.executeUpdate();
			if (value > 0) {
//				stmt=conn.prepareStatement("insert into categoryRegistrationjoin values(?,?)");
//				stmt.setInt(1, rmodel.getRid());
//				stmt.setInt(2, cid);
				// return stmt.executeUpdate() > 0 ? true : false;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println("Error is " + e);
			return false;
		}
	}
	public RegistrationModel getLoginInfo(int rid) {
		try {
			stmt=conn.prepareStatement("select username,password from registration where rid=?");
			stmt.setInt(1, rid);
		    rs=stmt.executeQuery();
			if(rs.next()) {
				model.setUsername(rs.getString(1));
				model.setPassword(rs.getString(2));
				return model;
			}else {
				return null;
			}
		}catch(Exception e) {
			return null;
		}
	}
	public void addCatMealReg(int rid,String category,String mealType) {
		int cid = getCategoryIDByName(category);
		int mtid=getMealIDByName(mealType);
		try {
			stmt=conn.prepareStatement("insert into catmealregjoin values(?,?,?)");
			stmt.setInt(1, cid);
			stmt.setInt(2, rid);
			stmt.setInt(3, mtid);
			int value=stmt.executeUpdate();
		}catch(Exception e) {
			System.err.println("Error is "+e);
		}
	}
	public int countMonthlyMembers(String sdate,String edate) {
		try {
			Date sd=Date.valueOf(sdate);
			Date ed=Date.valueOf(edate);
			stmt=conn.prepareStatement("select count(rsdate) from registration where rsdate between ? and ? and category='Monthlytt' || category='Monthlyot'");
			stmt.setDate(1, sd);
			stmt.setDate(2, ed);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				return count;
			}
			return 0;
		}catch(Exception e) {
			return 0;
		}
	}
}
