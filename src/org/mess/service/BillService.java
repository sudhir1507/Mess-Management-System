package org.mess.service;

import org.mess.model.BillModel;
import org.mess.repository.BillRepository;

public class BillService {
	BillRepository billRepo=new BillRepository();
	public int billGenerator(BillModel bmodel,int rid) {
		return billRepo.billGenerator(bmodel,rid);
	}
}
