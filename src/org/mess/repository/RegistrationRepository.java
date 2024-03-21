package org.mess.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mess.model.MealModel;
import org.mess.model.RegistrationModel;

public class RegistrationRepository extends DBConfig {
	RegistrationModel model=new RegistrationModel();
	MealModel mmodel=new MealModel();
	List<RegistrationModel> list=new ArrayList<RegistrationModel>();
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
	public int countMembers(int month,int year,String category) {
		int cid=getCategoryIDByName(category);
		try {
			stmt=conn.prepareStatement("select count(distinct r.rid) from registration r inner join catmealregjoin cat on cat.rid=r.rid inner join category c on c.cid=cat.cid where MONTH(rsdate)=? and YEAR(rsdate)=? and (c.cid=?)");
			stmt.setInt(1, month);
			stmt.setInt(2, year);
			stmt.setInt(3, cid);
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
	public List<RegistrationModel> getAllRegistrations() {
		try {
			stmt=conn.prepareStatement("select * from registration");
			rs=stmt.executeQuery();
			while(rs.next()) {
				RegistrationModel rmodel=new RegistrationModel();
				rmodel.setRid(rs.getInt(1));
				rmodel.setName(rs.getString(2));
				rmodel.setContact(rs.getString(3));
				rmodel.setAddress(rs.getString(4));
				rmodel.setRsdate(rs.getDate(5));
				rmodel.setRedate(rs.getDate(6));
				rmodel.setAmount(rs.getInt(7));
				rmodel.setUsername(rs.getString(8));
				rmodel.setPassword(rs.getString(9));
				list.add(rmodel);
			}
			return list.size()>0?list:null;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return null;
		}
	}
}
