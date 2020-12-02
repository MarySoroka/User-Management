package com.task.management.service;

import com.task.management.entity.UserAccount;
import com.task.management.entity.UserAccountDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {

  UserAccount createUser(UserAccount userAccount);

  void deleteUser(Long userId);

  UserAccount updateUser(UserAccountDTO userAccount);

  Page<UserAccount> findAll(Pageable page);

  Optional<UserAccount> findById(Long userId);
}
