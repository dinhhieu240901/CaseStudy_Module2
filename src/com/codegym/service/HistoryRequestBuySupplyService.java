package com.codegym.service;



import com.codegym.model.history.HistoryRequestBuySupply;
import com.codegym.model.history.HistoryTransaction;
import com.codegym.model.history.HistoryType;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.supply.Supply;
import com.codegym.model.supply.Warehouse;
import com.codegym.repository.EmployeeRepository;
import com.codegym.repository.HistoryRequestBuySupplyRepository;
import com.codegym.repository.TransactionHistoryRepository;

import java.sql.Date;
import java.util.List;

public class HistoryRequestBuySupplyService  extends BaseService{

    private static HistoryRequestBuySupplyService historyRequestBuySupplyService;
    private static EmployeeRepository employeeRepository = EmployeeRepository.getEmployeeRepository();

    private static TransactionHistoryRepository  historyTransactionRepository = TransactionHistoryRepository.getHistoryTransactionRepository();
    private Warehouse warehouse = new Warehouse();

    private HistoryRequestBuySupplyService() {

    }


    public static HistoryRequestBuySupplyService getHistoryRequestBuySupplyService(){
        if (historyRequestBuySupplyService == null){
            historyRequestBuySupplyService = new HistoryRequestBuySupplyService();
        }
        return historyRequestBuySupplyService;
    }
    private HistoryRequestBuySupplyRepository historyRequestBuySupplyRepository =  HistoryRequestBuySupplyRepository.getHistoryRequestBuySupplyRepository();
    private SupplyService supplyService =  SupplyService.getSupplyService();

    private HistoryTransactionService historyTransactionService =  HistoryTransactionService.getHistoryTransactionService();


    public List<HistoryRequestBuySupply> getSupplies(){
        return historyRequestBuySupplyRepository.getAllHistoryRequestBuySupplies();
    }

    public  HistoryRequestBuySupply  findById(String id){
        return historyRequestBuySupplyRepository.findById(id);
    }
    public void addHistoryRequestBuySupply(int idSupply, int quantity ) {

        Supply supply = supplyService.findById(idSupply);
        if (supply != null && supply.getQuantityAvailable() >= quantity){
            HistoryRequestBuySupply requestBuySupply = new HistoryRequestBuySupply(genId(), getEmployeeId(), idSupply, quantity, supply.getPricePerUnit()*quantity,"WAITING");
            historyRequestBuySupplyRepository.addHistoryRequestBuySupply(requestBuySupply);
            System.out.println("Thành Công");
        }else {
            System.out.println("Không tìm thấy nguyên liệu hoặc không đủ");

        }
    }
    public void purchaseSupply(int supplyId, int quantity) {
        Supply supplyInWarehouse = warehouse.getSupplyQuantities().containsKey(supplyId)
                ? supplyService.findById(supplyId)
                : null;
        if (supplyInWarehouse != null) {
            warehouse.addSupply(supplyId, quantity);
            supplyService.decreaseSupplyQuantity(supplyId, quantity);
            historyTransactionService.addHistoryTransaction(new HistoryTransaction(
                    genId(),
                    "owner1",
                    HistoryType.BUY_SUPPLY,
                    supplyInWarehouse.getPricePerUnit() * quantity,
                    new Date(System.currentTimeMillis())
            ));

            System.out.println("Giao dịch mua nguyên liệu thành công!");
            warehouse.writeToFile();
        } else {
            Supply supply = supplyService.findById(supplyId);
            if (supply != null && supply.getQuantityAvailable() >= quantity) {
                double totalPrice = supply.getPricePerUnit() * quantity;
                Employee owner = employeeRepository.findEmployeeById("owner1");
                if (owner != null && owner instanceof Owner) {
                    if (((Owner) owner).getTotalMoney() >= totalPrice) {
                        ((Owner) owner).setTotalMoney(((Owner) owner).getTotalMoney() - totalPrice);
                        employeeRepository.updateFileCSV();
                        warehouse.addSupply(supplyId, quantity);
                        supplyService.decreaseSupplyQuantity(supplyId, quantity);
                        historyTransactionService.addHistoryTransaction(new HistoryTransaction(
                                genId(),
                                owner.getEmployeeId(),
                                HistoryType.BUY_SUPPLY,
                                totalPrice,
                                new Date(System.currentTimeMillis())
                        ));
                        System.out.println("Giao dịch mua nguyên liệu thành công!");
                        warehouse.writeToFile();
                    }
                } else {
                    System.out.println("Không tìm thấy thông tin Owner hoặc Owner không hợp lệ.");
                }
            } else {
                System.out.println("Không tìm thấy nguyên liệu hoặc số lượng không đủ.");
            }
        }
    }


    public void updateFailHistoryRequestBuySupply(String id ) {
        HistoryRequestBuySupply requestBuySupply = findById(id);
        if (requestBuySupply != null){
            requestBuySupply.setStatus("FAIL");
            historyRequestBuySupplyRepository.updateHistoryRequestBuySupply(requestBuySupply);
            System.out.println("Thành công.");
        } else {
            System.out.println("Không tìm thấy yêu cầu mua nguyên liêu.");
        }
    }
    public void updateSuccessHistoryRequestBuySupply(String id ) {
        HistoryRequestBuySupply requestBuySupply = findById(id);
        if (requestBuySupply != null){
            requestBuySupply.setStatus("SUCCESS");
            historyRequestBuySupplyRepository.updateHistoryRequestBuySupply(requestBuySupply);
            historyTransactionRepository.addHistoryTransaction(new HistoryTransaction(genId(), requestBuySupply.getUserIdRequest(), HistoryType.BUY_SUPPLY,requestBuySupply.getTotalMoney(),new Date(System.currentTimeMillis())));
            System.out.println("Thành công.");
        } else {
            System.out.println("Không tìm thấy yêu cầu mua nguyên liêu.");
        }
    }

}
