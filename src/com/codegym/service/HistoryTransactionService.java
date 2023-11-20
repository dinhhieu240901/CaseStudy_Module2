package com.codegym.service;


import com.codegym.model.history.HistoryTransaction;
import com.codegym.model.history.HistoryType;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.repository.EmployeeRepository;
import com.codegym.repository.TransactionHistoryRepository;

import java.util.List;

public class HistoryTransactionService {

    private static HistoryTransactionService historyTransactionService;
    private EmployeeRepository employeeRepository = EmployeeRepository.getEmployeeRepository();

    private HistoryTransactionService() {

    }

    public static HistoryTransactionService getHistoryTransactionService(){
        if (historyTransactionService == null){
            historyTransactionService = new HistoryTransactionService();
        }
        return historyTransactionService;
    }
    private TransactionHistoryRepository historyTransactionRepository =  TransactionHistoryRepository.getHistoryTransactionRepository();


    public List<HistoryTransaction> getHistoryTransactions(){
        return historyTransactionRepository.getAllHistoryTransaction();
    }

    public  HistoryTransaction  findById(String id){
        return historyTransactionRepository.findById(id);
    }
    public void addHistoryTransaction(HistoryTransaction historyTransaction) {
       Employee employee  =employeeRepository.findEmployeeById("owner1");
       if (employee != null && employee instanceof Owner){
           if (historyTransaction.getHistoryType().equals(HistoryType.BUY_TICKET)){
               ((Owner) employee).setTotalMoney(((Owner) employee).getTotalMoney() + historyTransaction.getTotalMoney());
               System.out.println("Tiền đã được chuyển vào tài khoản Owner");
           }else {
               ((Owner) employee).setTotalMoney(((Owner) employee).getTotalMoney() - historyTransaction.getTotalMoney());
               System.out.println("Tiền đã trừ vào tài khoản Owner");
           }
           employeeRepository.updateFileCSV();


       } historyTransactionRepository.addHistoryTransaction(historyTransaction);
    }
    public void updateHistoryTransaction(HistoryTransaction historyTransaction) {
        historyTransactionRepository.updateHistoryTransaction(historyTransaction);
    }


    
}
