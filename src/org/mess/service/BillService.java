package org.mess.service;

import java.util.List;

import org.mess.model.BillModel;
import org.mess.repository.BillRepository;

public class BillService {
	BillRepository billRepo=new BillRepository();
	public List<BillModel> getBill(BillModel bmodel,int rid) {
		return billRepo.getBill(bmodel,rid);
	}
	public int updateBill(int remaining,int bid,int rid) {
		return billRepo.updateBill(remaining,bid,rid);
	}
}
