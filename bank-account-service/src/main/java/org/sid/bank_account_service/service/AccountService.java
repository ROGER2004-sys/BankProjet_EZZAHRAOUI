package org.sid.bank_account_service.service;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO saveAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountResponseDTO getAccount(String id);
    List<BankAccountResponseDTO> listAccounts();
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
    void deleteAccount(String id);
}