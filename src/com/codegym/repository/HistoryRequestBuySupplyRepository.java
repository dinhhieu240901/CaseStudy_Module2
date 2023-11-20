package com.codegym.repository;


import com.codegym.model.history.HistoryRequestBuySupply;
import com.codegym.serializer.ReadHistoryRequestBuySupplySerializer;

import java.util.List;
import java.util.Optional;

public class HistoryRequestBuySupplyRepository {
    private List<HistoryRequestBuySupply> historyRequestBuySupplys;

    ReadHistoryRequestBuySupplySerializer readHistoryRequestBuySupplySerializer = ReadHistoryRequestBuySupplySerializer.getInstanceReadHistoryRequestBySupplySerializer();

    private static HistoryRequestBuySupplyRepository historyRequestBuySupplyRepository;


    private HistoryRequestBuySupplyRepository() {
        this.historyRequestBuySupplys = readHistoryRequestBuySupplySerializer.readFromCSV();


    }

    public static HistoryRequestBuySupplyRepository getHistoryRequestBuySupplyRepository() {
        if (historyRequestBuySupplyRepository == null) {
            historyRequestBuySupplyRepository = new HistoryRequestBuySupplyRepository();
        }
        return historyRequestBuySupplyRepository;
    }

    public void addHistoryRequestBuySupply(HistoryRequestBuySupply historyRequestBuySupply) {
        historyRequestBuySupplys.add(historyRequestBuySupply);
        updateFileCSV();
    }

    public void removeHistoryRequestBuySupply(String historyRequestBuySupplyId) {
        historyRequestBuySupplys.removeIf(historyRequestBuySupply -> historyRequestBuySupply.getId().equals(historyRequestBuySupplyId));
        updateFileCSV();

    }

    public HistoryRequestBuySupply findById(String historyRequestBuySupplyId) {
        for (HistoryRequestBuySupply historyRequestBuySupply : historyRequestBuySupplys) {
            if (historyRequestBuySupplyId.equals(historyRequestBuySupply.getId())) {
                return historyRequestBuySupply;
            }
        }
        return null;
    }


    public List<HistoryRequestBuySupply> getAllHistoryRequestBuySupplies() {
        return historyRequestBuySupplys;
    }

    public void updateHistoryRequestBuySupply(HistoryRequestBuySupply updatedHistoryRequestBuySupply) {
        for (int i = 0; i < historyRequestBuySupplys.size(); i++) {
            historyRequestBuySupplys.set(i, updatedHistoryRequestBuySupply);
            updateFileCSV();
            return;
        }
    }

    public void updateFileCSV() {
        readHistoryRequestBuySupplySerializer.writeToCSV(historyRequestBuySupplys);
    }

}
