package com.infosys.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.infosys.demo.service.PositionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.utils.R;

@RestController
@RequestMapping(value = "/")
public class PositionManagementController {

    @Autowired
    private PositionManagementService pmservice;


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

        pmservice.operationPosition(position);


        return R.ok();
    }

}
