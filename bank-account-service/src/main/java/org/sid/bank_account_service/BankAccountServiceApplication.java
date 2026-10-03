package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repo.BankAccountRepo;
import org.sid.bank_account_service.repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
	}

    @Bean
    CommandLineRunner start(BankAccountRepo bankAccountRepository, CustomerRepo customerRepository) {
        return args -> {
            // 1. Enregistrement des clients
            Stream.of("Mohamed", "Mehdi", "Yasser", "Najat").forEach(c -> {
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });

            // 2. Récupération de tous les clients et création de 10 comptes pour chacun
            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i < 10; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .balance(10000 + Math.random() * 90000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            });
        };
    }



}
