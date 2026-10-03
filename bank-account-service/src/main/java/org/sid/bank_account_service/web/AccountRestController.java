package org.sid.bank_account_service.web;

import org.sid.bank_account_service.DTO.BankAccountRequestDTO;
import org.sid.bank_account_service.DTO.BankAccountResponseDTO;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {


    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<BankAccountResponseDTO> accounts() {
        return accountService.listAccounts();
    }


    @GetMapping("/accounts/{id}")
    public BankAccountResponseDTO getAccount(@PathVariable String id) {
        return accountService.getAccount(id);
    }

    // 3. Création (Via Service + DTO)
    @PostMapping("/accounts")
    public BankAccountResponseDTO saveAccount(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.saveAccount(requestDTO);
    }

    // 4. Modification (Via Service + DTO)
    @PutMapping("/accounts/{id}")
    public BankAccountResponseDTO updateAccount(@PathVariable String id, @RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.updateAccount(id, requestDTO);
    }

    // 5. Suppression (Via Service)
    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}