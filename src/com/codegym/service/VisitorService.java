package com.codegym.service;



import com.codegym.model.history.HistoryTransaction;
import com.codegym.model.history.HistoryType;
import com.codegym.model.person.employee.Employee;
import com.codegym.model.person.employee.Owner;
import com.codegym.model.person.enumerations.AgeCategory;
import com.codegym.model.person.visitors.Adult;
import com.codegym.model.person.visitors.Child;
import com.codegym.model.person.visitors.Senior;
import com.codegym.model.person.visitors.Visitor;
import com.codegym.model.ticket.AdultTicket;
import com.codegym.model.ticket.ChildTicket;
import com.codegym.model.ticket.SeniorTicket;
import com.codegym.repository.EmployeeRepository;
import com.codegym.repository.TransactionHistoryRepository;
import com.codegym.repository.VisitorRepository;

import java.sql.Date;
import java.util.List;

public class VisitorService extends BaseService{

    private static VisitorService visitorService;



    private VisitorService() {

    }

    public static VisitorService getVisitorService(){
        if (visitorService == null){
            visitorService = new VisitorService();
        }
        return visitorService;
    }
    private final VisitorRepository visitorRepository =  VisitorRepository.getVisitorRepository();

    private final HistoryTransactionService historyTransactionService = HistoryTransactionService.getHistoryTransactionService();

    private final TransactionHistoryRepository transactionHistoryRepository = TransactionHistoryRepository.getHistoryTransactionRepository();
    private final EmployeeRepository employeeRepository = EmployeeRepository.getEmployeeRepository();


    public List<Visitor> getVisitors(){
        return visitorRepository.getAllVisitors();
    }

    public  List<Visitor>  findById(AgeCategory ageCategory){
        return visitorRepository.findVisitorsByAgeCategory(ageCategory);
    }
    public void addVisitor(Visitor visitor) {
        visitorRepository.addVisitor(visitor);
        double totalMoney = 0;
        if(visitor instanceof Child){
            totalMoney = ((Child) visitor).getChildTicket().getPrice();
        } else if(visitor instanceof Adult){
            totalMoney = ((Adult) visitor).getAdultTicket().getPrice();
        } else if(visitor instanceof Senior) {
            totalMoney = ((Senior) visitor).getSeniorTicket().getPrice();
        }
        transactionHistoryRepository.addHistoryTransaction(new HistoryTransaction( genId(),visitor.getVisitorID(), HistoryType.BUY_TICKET,totalMoney,new Date(System.currentTimeMillis())));
    }

    public void transferMoneyToOwner(double tongSoTien) {
        Employee owner = employeeRepository.findEmployeeById("owner1");
        if (owner != null && owner instanceof Owner) {
            ((Owner) owner).setTotalMoney(((Owner) owner).getTotalMoney() + tongSoTien);
            System.out.println("Tiền đã được chuyển vào tài khoản của Owner.");
            employeeRepository.updateFileCSV();
        } else {
            System.out.println("Không tìm thấy thông tin Owner hoặc Owner không hợp lệ.");
        }
    }
    public double calculateTotalAmountFromTicketSales() {
        double total = 0;
        for (HistoryTransaction transaction : historyTransactionService.getHistoryTransactions()) {
            if (transaction.getHistoryType() == HistoryType.BUY_TICKET) {
                total += transaction.getTotalMoney();
            }
        }
        return total;
    }

    public void updateVisitor(Visitor visitor) {
        visitorRepository.updateVisitor(visitor);
    }
    public void removeVisitor(String visitorID){visitorRepository.removeVisitor(visitorID);}
    public void sortVisitors() {
        visitorRepository.sortVisitorsByName();
    }
}
