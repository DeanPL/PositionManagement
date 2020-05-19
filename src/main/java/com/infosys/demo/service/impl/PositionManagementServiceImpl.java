package com.infosys.demo.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.demo.config.Constant;
import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.service.ExcelService;
import com.infosys.demo.service.PositionManagementService;

@Service("pmservice")
public class PositionManagementServiceImpl implements PositionManagementService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ExcelService excelService;

	@Override
	public void operationTransaction(Transaction t) {
		List<Transaction> transactions = queryTransactions();

		if (!isExistTransactionCancel(transactions, t)) {
			t.setTransactionID(genTransactionID());
			t.setVersion(genVersion(t.getTradeID()));
			transactions.add(t);
		}
		excelService.updateTransactionSheet(transactions);
		List<Position> positions = refreshTransaction(transactions);
		excelService.updatePositionSheet(positions);
	}

	@Override
	public void operationPosition(Position p) {
		Transaction t = null;
		String buyOrSell = p.getBuyOrSell();
		t = new Transaction();
		t.setTransactionID(genTransactionID());
		t.setTradeID(genTradeID());
		t.setVersion(1);
		t.setSecurityCode(p.getSecurityCode());
		t.setQuantity(p.getQuantity());
		t.setInsert_update_cancel(Constant.Insert);
		t.setBuy_sell(buyOrSell);
		List<Transaction> transactions = queryTransactions();
		transactions.add(t);
		excelService.updateTransactionSheet(transactions);
		List<Position> positions = refreshTransaction(transactions);
		excelService.updatePositionSheet(positions);
	}

	private List<Position> refreshTransaction(List<Transaction> transactions) {
		// 按照tradeID分组,准备Map,此处使用LinkedHashMap的原因是为了保证后续遍历的时候,按照分组时的add顺序来操作
		Map<Integer, List<Transaction>> groupedMap = new LinkedHashMap<Integer, List<Transaction>>();
		for (Transaction transaction : transactions) {
			Integer tradeID = transaction.getTradeID();
			// 如果Map中的key包含此时的groupName,则取出Value,直接add此实体
			if (groupedMap.containsKey(tradeID)) {
				groupedMap.get(tradeID).add(transaction);
				// 否则的话,新建一个key为groupName的List,并将groupName作为key,list作为value放入map
			} else {
				List<Transaction> grouplist = new ArrayList<Transaction>();
				grouplist.add(transaction);
				groupedMap.put(tradeID, grouplist);
			}
		}

		Set<Integer> keySet = groupedMap.keySet();
		List<Transaction> lastTransactions = new ArrayList<Transaction>();
		for (Integer tradeID : keySet) {
			List<Transaction> grouplist = groupedMap.get(tradeID);
			grouplist.sort(new Comparator<Transaction>() {
				@Override
				public int compare(Transaction o1, Transaction o2) {
					return o2.getVersion().compareTo(o1.getVersion());
				}
			});
			lastTransactions.add(grouplist.get(0));
		}

		Position p = null;
		Map<String, Position> map = new HashMap<String, Position>();
		for (Transaction t : lastTransactions) {
			String securityCode = t.getSecurityCode();
			if (map.containsKey(securityCode)) {
				p = map.get(securityCode);
			} else {
				p = new Position();
				p.setQuantity(0);
				map.put(securityCode, p);
			}
			p.setSecurityCode(t.getSecurityCode());
			if (!t.getInsert_update_cancel().equals(Constant.Cancel)) {
				if (t.getBuy_sell().equals(Constant.Buy)) {
					p.setQuantity(p.getQuantity() + t.getQuantity());
				} else {
					p.setQuantity(p.getQuantity() - t.getQuantity());
				}
			}
		}
		Set<String> set = map.keySet();
		List<Position> positions = new ArrayList<Position>();
		for (String securityCode : set) {
			positions.add(map.get(securityCode));
		}
		return positions;
	}

	@Override
	public List<Transaction> queryTransactions() {
		return excelService.queryTransactions();
	}

	@Override
	public List<Position> queryPositions() {
		return excelService.queryPositions();
	}

	private Integer genTransactionID() {
		return queryTransactions().size() + 1;
	}

	private boolean isExistTransactionCancel(List<Transaction> transactions, Transaction t) {
		for (Transaction transation : transactions) {
			if (transation.getTradeID().equals(t.getTradeID())) {
				if (transation.getInsert_update_cancel().equals(Constant.Cancel)) {
					return true;
				}
			}
		}
		return false;
	}

	private Integer genTradeID() {
		List<Transaction> transactions = queryTransactions();
		transactions.sort(new Comparator<Transaction>() {
			@Override
			public int compare(Transaction o1, Transaction o2) {

				return o2.getTradeID().compareTo(o1.getTradeID());
			}
		});

		return transactions.get(0).getTradeID() + 1;
	}

	private Integer genVersion(Integer tradeID) {
		List<Transaction> transactions = queryTransactions();
		List<Transaction> t2 = new ArrayList<Transaction>();
		for (Transaction t : transactions) {
			if (t.getTradeID().equals(tradeID)) {
				t2.add(t);
			}
		}
		t2.sort(new Comparator<Transaction>() {
			@Override
			public int compare(Transaction o1, Transaction o2) {

				return o2.getVersion().compareTo(o1.getVersion());
			}
		});

		return t2.get(0).getVersion() + 1;
	}

	public static void main(String[] args) throws Exception {
		PositionManagementServiceImpl pmservice = new PositionManagementServiceImpl();
		System.out.println("SSSSSSSSSSSSSSSSSSSSS");
		List<Transaction> transactions = pmservice.queryTransactions();
		for (Transaction t : transactions) {
			System.out.println(t);
		}
	}

	@Override
	public Transaction queryTransaction(Integer transactionID) {
		List<Transaction> transactions = queryTransactions();
		for (Transaction t : transactions) {
			if (t.getTransactionID().equals(transactionID)) {
				return t;
			}
		}
		return null;
	}
}
