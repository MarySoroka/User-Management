package com.task.management.service.impl;

import com.task.management.entity.UserAccount;
import com.task.management.entity.UserAccountDTO;
import com.task.management.repository.UserAccountRepository;
import com.task.management.service.UserAccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

  private UserAccountRepository userAccountRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
    this.userAccountRepository = userAccountRepository;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserAccount createUser(UserAccount account) {

    account.setPassword(passwordEncoder.encode(account.getPassword()));
    return userAccountRepository.save(account);
  }

  @Override
  public void deleteUser(Long userId) {
    userAccountRepository.deleteById(userId);
  }

  @Override
  public UserAccount updateUser(UserAccountDTO userAccount) {
    return userAccountRepository.save(new UserAccount(userAccount));
  }

  @Override
  public Page<UserAccount> findAll(Pageable page) {
    return userAccountRepository.findAll(page);
  }

  @Override
  public Optional<UserAccount> findById(Long userId) {
    return userAccountRepository.findById(userId);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    UserAccount userAccount = userAccountRepository.findByUsername(username);
    if (userAccount == null) {
      throw new UsernameNotFoundException("Couldn't find user by name");
    }
    return userAccount;
  }
}
