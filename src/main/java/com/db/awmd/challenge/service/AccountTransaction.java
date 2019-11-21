package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.repository.AccountsRepository;
import com.db.awmd.challenge.repository.AccountsRepositoryInMemory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Service
public class AccountTransaction {

    @Autowired
    private AccountsService services;

    @Autowired
    private AccountsRepository repo;

    private static Logger log = LoggerFactory.getLogger(AccountTransaction.class);

   /* public Account fundTransfer() throws Exception{

        Account account = new Account("Id-0522");
        account.setBalance(new BigDecimal(2000));

        this.repo.createAccount(account);

        //this.services.createAccount(account);

        log.info("account in data {}",this.repo.getAccount("Id-0522").getBalance());

        return this.services.getAccount("Id-0522");
    }*/

   public synchronized void fundTransfer(String fromAccount
           ,String toAccount, BigDecimal amount)throws Exception{
       try{

           BigDecimal amountFrmAccount = this.repo.getAccount(fromAccount).getBalance().subtract(amount);

           if(amountFrmAccount.compareTo(BigDecimal.ZERO) < 0) {

               log.info("Current account balance {}",this.repo.getAccount(fromAccount).getBalance());

               throw new IllegalArgumentException("Only positive value allowed");
           }

           this.repo.getAccount(fromAccount).setBalance(amountFrmAccount);

           BigDecimal amountToAccount = this.repo.getAccount(toAccount).getBalance().add(amount);

           this.repo.getAccount(toAccount).setBalance(amountToAccount);

           log.info("from account balance {}",this.repo.getAccount(fromAccount).getBalance());
           log.info("to account balance {}",this.repo.getAccount(toAccount).getBalance());

       }catch (Exception ex){

       }
   }


}
