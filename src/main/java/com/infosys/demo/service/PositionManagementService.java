package com.infosys.demo.service;

import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface PositionManagementService {
    public void operationTransaction(Transaction t);
    public void operationPosition(Position p);

    public Transaction queryTransaction(Integer transactionID);
    
    public List<Transaction> queryTransactions();
    public List<Position> queryPositions();


}
