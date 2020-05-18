package com.infosys.demo.service;

import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface ExcelService {

    public List<Transaction> queryTransactions();

    public List<Position> queryPositions();

    public  void updateTransactionSheet(List<Transaction> transactions);

    public  void updatePositionSheet( List<Position> positions);

}
