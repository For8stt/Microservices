package com.example.demo.transaction;

import com.example.demo.Command;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TrasferService implements Command<TransferDTO,String> {
    private final BankAccountRepository bankAccountRepository;
    public TrasferService(BankAccountRepository bankAccountRepository){
        this.bankAccountRepository=bankAccountRepository;
    }
    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {
        Optional<BankAccount> fromAccount=bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getToUser());

        if (fromAccount.isEmpty() || toAccount.isEmpty()){
            throw new RuntimeException("User not find");
        }
        BankAccount from=fromAccount.get();
        BankAccount to=toAccount.get();

        add(to,transfer.getAmount());
        deduct(from, transfer.getAmount());


        return ResponseEntity.ok("Success");
    }
    public void deduct(BankAccount bankAccount, double amount){
        if (bankAccount.getBalance() < amount){
            throw new RuntimeException("Not enough money");
        }
        bankAccount.setBalance(bankAccount.getBalance()-amount);
    }
    public void add(BankAccount bankAccount,double amount){
        bankAccount.setBalance(bankAccount.getBalance()+amount);
    }


}
