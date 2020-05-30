package com.homework.week12.atm_new_with_DB_in_csv_files.service;

import com.homework.exception.EntityNotFoundException;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.BankAccount;
import com.homework.week12.atm_new_with_DB_in_csv_files.domain.User;
import com.homework.week12.atm_new_with_DB_in_csv_files.repository.UserRepository;

import java.util.List;
import java.util.Map;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService(BankAccountService bankAccountService) {
        Map<String, List<BankAccount>> userIdToAccounts = bankAccountService.getUserIdToAccounts();
        userRepository = new UserRepository(userIdToAccounts);
    }

    public User getUserById(String userId) throws EntityNotFoundException {
      return userRepository.getUserById(userId)
              .orElseThrow(() -> new EntityNotFoundException("No user for id '" + userId + "'"));
    }
}
