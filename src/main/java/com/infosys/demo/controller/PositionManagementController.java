package com.infosys.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.demo.config.Constant;
import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.service.PositionManagementService;
import com.infosys.demo.utils.R;

@RestController
@RequestMapping(value = "/")
public class PositionManagementController {

    @Autowired
    private PositionManagementService pmservice;


    @RequestMapping("transactions/info/{transactionID}")
    public R transactionsInfo(@PathVariable("transactionID") Integer transactionID) {
        Transaction transaction = pmservice.queryTransaction(transactionID);
        transaction.setInsert_update_cancel(Constant.Update);
        return R.ok().put("transaction", transaction);
    }
    
    @RequestMapping("transactions/list")
    public R transactionsList() {
        List<Transaction> transactions = pmservice.queryTransactions();
        return R.ok().put("transactions", transactions);
    }

    @RequestMapping("positions/list")
    public R positionsList() {
        List<Position> positions = pmservice.queryPositions();
        return R.ok().put("positions", positions);
    }

    @RequestMapping("positions/operation")
    public R operation(@RequestBody Position position) {
    	position.setSecurityCode(position.getSecurityCode().toUpperCase());
        pmservice.operationPosition(position);
        return R.ok();
    }
    @RequestMapping("transaction/operation")
    public R operation(@RequestBody Transaction t) {
    	t.setSecurityCode(t.getSecurityCode().toUpperCase());
    	pmservice.operationTransaction(t);
        return R.ok();
    }

}
