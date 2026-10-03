package org.sid.bank_account_service.service;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repo.BankAccountRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final BankAccountRepo bankAccountRepo;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepo bankAccountRepo, AccountMapper accountMapper) {
        this.bankAccountRepo = bankAccountRepo;
        this.accountMapper = accountMapper;
    }

    // 1. CRÉATION
    @Override
    public BankAccountResponseDTO saveAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());

        BankAccount savedBankAccount = bankAccountRepo.save(bankAccount);
        return accountMapper.fromBankAccount(savedBankAccount);
    }

    // 2. LECTURE (Par ID)
    @Override
    public BankAccountResponseDTO getAccount(String id) {
        BankAccount bankAccount = bankAccountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }

    // 3. LECTURE (Tous les comptes)
    @Override
    public List<BankAccountResponseDTO> listAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepo.findAll();
        return bankAccounts.stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }

    // 4. MISE À JOUR
    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

        if (bankAccountDTO.getBalance() != null) bankAccount.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getCurrency() != null) bankAccount.setCurrency(bankAccountDTO.getCurrency());
        if (bankAccountDTO.getType() != null) bankAccount.setType(bankAccountDTO.getType());

        BankAccount updatedAccount = bankAccountRepo.save(bankAccount);
        return accountMapper.fromBankAccount(updatedAccount);
    }

    // 5. SUPPRESSION
    @Override
    public void deleteAccount(String id) {
        if (!bankAccountRepo.existsById(id)) {
            throw new RuntimeException(String.format("Account %s not found", id));
        }
        bankAccountRepo.deleteById(id);
    }
}