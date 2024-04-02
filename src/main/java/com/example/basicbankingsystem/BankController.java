package com.example.basicbankingsystem;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public void createAccount(@RequestBody AccountDto accountDto) {
        bankService.createAccount(accountDto.getAccountNumber(), accountDto.getOwnerName(), accountDto.getInitialBalance());
    }

    @GetMapping("/{accountNumber}")
    public AccountDto getAccount(@PathVariable String accountNumber) throws BankException {
        BankAccount account = bankService.getAccount(accountNumber);
        return new AccountDto(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable String accountNumber, @RequestParam double amount) throws BankException {
        bankService.deposit(accountNumber, amount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public void withdraw(@PathVariable String accountNumber, @RequestParam double amount) throws BankException {
        bankService.withdraw(accountNumber, amount);
    }

    @PostMapping("/{senderAccountNumber}/transfer")
    public void transfer(@PathVariable String senderAccountNumber, @RequestParam String recipientAccountNumber, @RequestParam double amount) throws BankException {
        bankService.transfer(senderAccountNumber, recipientAccountNumber, amount);
    }
}