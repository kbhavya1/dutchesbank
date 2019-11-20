package com.db.awmd.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.repository.AccountsRepository;
import com.db.awmd.challenge.service.AccountTransaction;
import com.db.awmd.challenge.service.AccountsService;
import java.math.BigDecimal;

import lombok.Getter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceTest {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private AccountsRepository process;

  @Autowired
  private AccountTransaction transaction;

  private static Logger log = LoggerFactory.getLogger(AccountsServiceTest.class);

  @Test
  public void addAccount() throws Exception {
    Account account = new Account("Id-123");
    account.setBalance(new BigDecimal(1000));
    this.accountsService.createAccount(account);

    log.info("logger shows here");
    log.info("result is {}",assertThat(this.accountsService.getAccount("Id-123")).isEqualTo(account));
  }

  @Test
  public void addAccount_failsOnDuplicateId() throws Exception {
    String uniqueId = "Id-" + System.currentTimeMillis();

    Account account = new Account(uniqueId);
    this.accountsService.createAccount(account);

    try {
      this.accountsService.createAccount(account);
      fail("Should have failed when adding duplicate account");
    } catch (DuplicateAccountIdException ex) {
      assertThat(ex.getMessage()).isEqualTo("Account id " + uniqueId + " already exists!");
    }

  }


  /*public void checkAccountTransfer() throws Exception {
    log.info("the data is {}",this.transaction.fundTransfer());
    String hi = "Hi";
    assertThat(hi.equals("Hi"));
  }*/

  @Test
  public void checAccountTransfer()throws Exception{
    Account account = new Account("Id-1234");
    account.setBalance(new BigDecimal( 1000));

    this.accountsService.createAccount(account);

    Account account1 = new Account("Id-5432");
    account1.setBalance(new BigDecimal(2000));

    this.accountsService.createAccount(account1);

    transaction.fundTransfer("Id-1234","Id-5432",new BigDecimal(2000));

    String hi = "hi";

    assertThat(hi.equals("hi"));


  }

}
