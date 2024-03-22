package org.mess.repository;

import java.util.ArrayList;
import java.util.List;

import org.mess.model.BillModel;

public class BillRepository extends DBConfig {
	

	private int totalAmountByRid(int rid) {
		try {
			stmt = conn.prepareStatement(
					"select fees from category where cid=(select cid from registration where rid=?)");
			stmt.setInt(1, rid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int totalAmount = rs.getInt(1);
				return totalAmount;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	private int getPaidAmountByRid(int rid) {
		try {
			stmt = conn.prepareStatement("select amount from registration where rid=?");
			stmt.setInt(1, rid);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int paidAmount = rs.getInt(1);
				return paidAmount;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}
	private int getRidByBillId(int bid) {
		try {
			stmt=conn.prepareStatement("select rid from billgenerate where bid=?");
			stmt.setInt(1, bid);
			rs=stmt.executeQuery();
			return rs.next()?rs.getInt(1):0;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
	public int billGenerator(BillModel bmodel, int rid) {
		int totalAmount = totalAmountByRid(rid);
		int paidAmount = getPaidAmountByRid(rid);
		int ramaining = 0, extrapaid = 0;
		ramaining = totalAmount - paidAmount;
//		if(paidAmount>totalAmount) {            //count
//			extrapaid=paidAmount-totalAmount;
//		}else {
//			ramaining=totalAmount-paidAmount;
//		}

		try {
			stmt = conn.prepareStatement("insert into billgenerate values('0',?,?,?,?,?,?)");
			stmt.setInt(1, rid);
			stmt.setDate(2, bmodel.getBdate());
			stmt.setInt(3, totalAmount);
			stmt.setInt(4, paidAmount);
			stmt.setInt(5, ramaining);
			if (ramaining <= 0) {
				stmt.setInt(6, 1);
			} else {
				stmt.setInt(6, 0);
			}
			int value = stmt.executeUpdate();
			return value > 0 ? 1 : 0;
		} catch (Exception e) {
			System.err.println("Error is " + e);
			return 0;
		}
	}

	public List<BillModel> getBill(BillModel bmodel, int rid) {
		List<BillModel> list = new ArrayList<>();
		try {
			if (billGenerator(bmodel, rid) > 0) {
				stmt = conn.prepareStatement("select * from billgenerate where rid=?");
				stmt.setInt(1, rid);
				rs = stmt.executeQuery();
				if (rs.next()) {
					BillModel model = new BillModel();
					model.setBid(rs.getInt(1));
					model.setRid(rs.getInt(2));
					model.setBdate(rs.getDate(3));
					model.setTotalAmount(rs.getInt(4));
					model.setPaid(rs.getInt(5));
					model.setRemaining(rs.getInt(6));
					model.setBstatus(rs.getInt(7));
					list.add(model);
					return list != null ? list : null;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Error is " + e);
			return null;
		}

	}
	public int getRemainingBill(int bid) {
		try {
			stmt=conn.prepareStatement("select remaining from billgenerate where bid=?");
			stmt.setInt(1, bid);
			rs=stmt.executeQuery();
			
				return rs.next()?rs.getInt(1):0;
			
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
	private void updatePaidBill(int rid,int updatePaid) {
		try {
			stmt=conn.prepareStatement("update registration set amount=? where rid=?");
			stmt.setInt(1, updatePaid);
			stmt.setInt(2, rid);
			int value=stmt.executeUpdate();
		}catch(Exception e) {
			System.out.println();
		}
	}
	public int updateBill(int remaining, int bid) {
		try {
			int rid=getRidByBillId(bid);
			int total = totalAmountByRid(rid); //1500
			int paid = getPaidAmountByRid(rid); //1000
			int remain = total - paid; //500
			int updatePaid = paid + remaining;  //1000+200=1200
			updatePaidBill(rid,updatePaid);
			remain = remain - remaining;  //500-200=300
			stmt = conn.prepareStatement("update billgenerate set paid=?,remaining=?,bstatus=? where bid=?");
			stmt.setInt(1, updatePaid);
			stmt.setInt(2, remain);
			if (remain > 0) {
				stmt.setInt(3, 0);
			} else {
				stmt.setInt(3, 1);
			}
			stmt.setInt(4, bid);
			return stmt.executeUpdate() > 0 ? getRemainingBill(bid) : 0;
		} catch (Exception e) {
			System.err.println("Error is " + e);
			return 0;
		}
	}
	public int totalBillDailyMembers(int month,int year,int cid) {
		try {
			stmt=conn.prepareStatement("select sum(paid) from billgenerate where rid in(select rid from registration where cid=? and (month(rsdate)=? and year(rsdate)=?))");
			stmt.setInt(1, cid);
			stmt.setInt(2, month);
			stmt.setInt(3, year);
			rs=stmt.executeQuery();
			return rs.next()?rs.getInt(1):0;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return 0;
		}
	}
}
