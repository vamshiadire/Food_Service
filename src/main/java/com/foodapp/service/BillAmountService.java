package com.foodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.entity.BillAmount;
import com.foodapp.repository.BillAmountRepository;

@Service
public class BillAmountService {

    @Autowired
    private BillAmountRepository billAmountRepository;

    public List<BillAmount> getAllBills() {
        return billAmountRepository.findAll();
    }

    public void saveBill(BillAmount bill) {
        billAmountRepository.save(bill);
    }

    public List<BillAmount> getBillsByWaiter(String waiterName) {
        return billAmountRepository.findByWaiterName(waiterName);
    }
    
    public void createBill(String waiterName, String item, double amount) {
        BillAmount bill = new BillAmount();
        bill.setWaiterName(waiterName);
        bill.setItem(item);
        bill.setAmount(amount);
        System.out.println("Creating Bill: " + bill); // Log the bill details
        billAmountRepository.save(bill); // This should trigger @PrePersist
    }
    
    
    public BillAmount getBillById(int billId) {
        BillAmount bill = billAmountRepository.findById(billId).orElse(null);
        System.out.println("Fetched Bill ID: " + billId + ", DateTime: " + (bill != null ? bill.getDateTime() : "Bill not found"));
        return bill;
    }
    
    
}
