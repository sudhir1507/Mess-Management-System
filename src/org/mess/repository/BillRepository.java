package org.mess.repository;

import org.mess.model.BillModel;

public class BillRepository extends DBConfig {
	
	private int totalAmountByRid(int rid) {
		try {
			stmt=conn.prepareStatement("select fees from category where cid=(select cid from catmealregjoin where rid=?)");
			stmt.setInt(1, rid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int totalAmount=rs.getInt(1);
				return totalAmount;
			}else {
				return 0;
			}
		}catch(Exception e) {
			return 0;
		}
	}
	private int getPaidAmountByRid(int rid) {
		try {
			stmt=conn.prepareStatement("select amount from registration where rid=?");
			stmt.setInt(1, rid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				int paidAmount=rs.getInt(1);
				return paidAmount;
			}else {
				return 0;
			}
		}catch(Exception e) {
			return 0;
		}
	}
	public int billGenerator(BillModel bmodel,int rid) {
		int totalAmount=totalAmountByRid(rid);
		int paidAmount=getPaidAmountByRid(rid);
		int ramaining=totalAmount-paidAmount;
		try {
			stmt=conn.prepareStatement("insert into billgenerate values('0',?,?,?,?,?,?)");
			stmt.setInt(1, rid);
			stmt.setDate(2, bmodel.getBdate());
			stmt.setInt(3, totalAmount);
			stmt.setInt(4, paidAmount);
			stmt.setInt(5, ramaining);
			stmt.setInt(6, 0);
			int value=stmt.executeUpdate();
			return value>0?1:0;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
}
