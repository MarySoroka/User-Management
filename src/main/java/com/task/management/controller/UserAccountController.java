package com.task.management.controller;

import com.task.management.entity.Role;
import com.task.management.entity.UserAccount;
import com.task.management.entity.UserAccountDTO;
import com.task.management.entity.UserRoleName;
import com.task.management.entity.UserStatus;
import com.task.management.entity.UserStatusName;
import com.task.management.service.UserAccountService;
import java.util.HashSet;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAccountController {

  private final UserAccountService userAccountService;

  @Autowired
  public UserAccountController(UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }


  @GetMapping("/user")
  public String userList(@RequestParam Pageable page, Model model) {
    model.addAttribute("allUsers", userAccountService.findAll(page));
    return "list";
  }

  @GetMapping("/user/{id}")
  public String gtUser(@PathVariable("id") Long userId, Model model) {
    model.addAttribute("user", userAccountService.findById(userId));
    return "view";
  }

  @PostMapping("/user/new")
  public String create(@Valid UserAccount user, BindingResult result, Model model) {
    HashSet<UserAccount> users = new HashSet<>();
    users.add(user);
    user.setStatus(new UserStatus(1L,UserStatusName.ACTIVE, users));
    HashSet<Role> roles = new HashSet<>();

    roles.add(new Role(1L,UserRoleName.ADMIN, users));
    user.setRoles(roles);
    UserAccount userAccount = userAccountService.createUser(user);
    model.addAttribute("user", userAccount);
    return "view";
  }

  @PatchMapping("/user/{id}/edit")
  public String update(@PathVariable("id") Long userId, @RequestParam UserAccountDTO user, Model model) {
    user.setId(userId);
    model.addAttribute("user", userAccountService.updateUser(user));
    return "view";
  }
}
