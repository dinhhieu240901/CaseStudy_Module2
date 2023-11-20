package com.codegym.service;



import com.codegym.model.history.HistoryTransaction;
import com.codegym.model.history.HistoryType;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.supply.Supply;
import com.codegym.model.supply.Warehouse;
import com.codegym.repository.EmployeeRepository;
import com.codegym.repository.SupplyRepository;
import com.codegym.repository.TransactionHistoryRepository;

import java.util.Date;
import java.util.List;

public class SupplyService extends BaseService{

    private static SupplyService supplyService;
    private Warehouse warehouse = new Warehouse();

    private SupplyService() {

    }

    public static SupplyService getSupplyService(){
        if (supplyService == null){
            supplyService = new SupplyService();
        }
        return supplyService;
    }
    private SupplyRepository supplyRepository =  SupplyRepository.getSupplyRepository();

    public void decreaseSupplyQuantity(int supplyId, int quantity) {
        Supply supply = supplyRepository.findById(supplyId);
        if (supply != null) {
            int currentQuantity = supply.getQuantityAvailable();
            if (currentQuantity >= quantity) {
                supply.setQuantityAvailable(currentQuantity - quantity);
                supplyRepository.updateSupply(supply);
            } else {
                System.out.println("Không đủ số lượng nguyên liệu để giảm.");
            }
        } else {
            System.out.println("Không tìm thấy nguyên liệu.");
        }
    }



    public List<Supply> getSupplies(){
        return supplyRepository.getAllSupplies();
    }

    public  Supply  findById(int id){
        return supplyRepository.findById(id);
    }
    public void addSupply(Supply supply) {
        supplyRepository.addSupply(supply);
    }
    public void updateSupply(Supply supply) {
        supplyRepository.updateSupply(supply);
    }

    public void removeSupply(int supplyId) {
        supplyRepository.removeSupply(supplyId);
    }
}
