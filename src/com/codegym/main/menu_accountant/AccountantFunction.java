package com.codegym.main.menu_accountant;

import com.codegym.main.Command.Command;
import com.codegym.service.HistoryTransactionService;
import com.codegym.service.VisitorService;

import java.util.Scanner;

public class AccountantFunction implements Command {


    HistoryTransactionService historyTransactionService = HistoryTransactionService.getHistoryTransactionService();
    VisitorService visitorService = VisitorService.getVisitorService();

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        int inputMenuManagerCustomerSelected;
        double totalMoney = 0;

        do {
            System.out.println("===  MENU THU NGÂN ===");
            System.out.println("1. Quản lý thống kê");
            System.out.println("2. Tính tổng số lượng vé");
            System.out.println("3. Chuyển tiền cho owner");
            System.out.println("4. Thoát về Menu chính");

            inputMenuManagerCustomerSelected = scanner.nextInt();

            switch (inputMenuManagerCustomerSelected) {
                case 1:
                    historyTransactionService.getHistoryTransactions().forEach(System.out::println);
                    break;
                case 2:
                    totalMoney = visitorService.calculateTotalAmountFromTicketSales();
                    System.out.println("Tổng số tiền từ vé bán được là: " + totalMoney);
                    break;
                case 3:
                    visitorService.transferMoneyToOwner(totalMoney);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (inputMenuManagerCustomerSelected != 4);
    }
}
