package dtu.services.Entities;


public class AccountDTO {
  public String accountNumber;

  
  public AccountDTO() {
  }
  
  public AccountDTO(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override
  public String toString() {
    return "AccountDTO [accountNumber=" + accountNumber + "]";
  }
}