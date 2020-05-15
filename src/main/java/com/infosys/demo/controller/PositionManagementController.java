package com.infosys.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.utils.R;

@RestController
@RequestMapping(value = "/")
public class PositionManagementController {

	@RequestMapping("transactions/list")
	public R transactionsList() {
		List<Transaction> transactions = new ArrayList<Transaction>();

		for (int i = 0; i < 10; i++) {

			Transaction t = new Transaction();
			t.setTrade("A" + i);
			t.setQuantity(i * 10);
			t.setTransactionID(i);
			t.setVersion(i);
			transactions.add(t);
		}

		return R.ok().put("transactions", transactions);
	}

	@RequestMapping("positions/list")
	public R positionsList() {
		List<Position> positions = new ArrayList<Position>();

		for (int i = 0; i < 10; i++) {
			Position p = new Position();
			p.setSecurityCode("INF" + i);
			p.setQuantity(50 + i);
			positions.add(p);
		}

		return R.ok().put("positions", positions);
	}

}
