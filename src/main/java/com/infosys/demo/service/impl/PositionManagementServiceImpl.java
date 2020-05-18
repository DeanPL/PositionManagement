package com.infosys.demo.service.impl;

import com.infosys.demo.config.Constant;
import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.service.ExcelService;
import com.infosys.demo.service.PositionManagementService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("pmservice")
public class PositionManagementServiceImpl implements PositionManagementService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExcelService excelService;

    @Override
    public void operationTransaction(Transaction t) {
        List<Transaction> transactions = queryTransactions();
        t.setTransactionID(genTransactionID());
        transactions.add(t);
        excelService.updateTransactionSheet(transactions);
        this.refreshPositionSheet();
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
        this.refreshPositionSheet();
    }


    private void refreshPositionSheet() {

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
        return queryTransactions().size()+1;
    }

    private Integer genTradeID() {
        List<Transaction> transactions = queryTransactions();
        transactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {

                return o1.getTradeID().compareTo(o2.getTradeID());
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

                return o1.getVersion().compareTo(o2.getVersion());
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
}
