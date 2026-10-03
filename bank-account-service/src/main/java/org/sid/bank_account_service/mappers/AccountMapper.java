package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    // Conversion pour la réponse API (Entité -> ResponseDTO)
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        if (bankAccount == null) return null;
        BankAccountResponseDTO dto = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, dto);
        return dto;
    }

    // Conversion pour la création/modification (RequestDTO -> Entité)
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO requestDTO) {
        if (requestDTO == null) return null;
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(requestDTO, bankAccount);
        return bankAccount;
    }
}
