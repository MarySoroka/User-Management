package com.task.management.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_account", schema = "user_management")
public class UserAccount implements UserDetails {

  private static final long serialVersionUID = 6111939843998792512L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Size(min = 1, max = 16, message = "The user name should contains more then 3 and less then 17 letters")
  @NotNull
  @Column(columnDefinition = "VARCHAR(17) UNIQUE")
  @Pattern(regexp = "[A-Za-z]+")
  private String username;
  @NotNull
  private String password;
  @Size(min = 1, max = 16, message = "The first name should contains more then 1 and less then 17 letters")

  @NotNull
  @Pattern(regexp = "[A-Za-z]+")
  private String firstName;
  @Size(min = 1, max = 16, message = "The last name should contains more then 1 and less then 17 letters")
  @NotNull
  @Pattern(regexp = "[A-Za-z]+")
  private String lastName;
  @CreationTimestamp
  private LocalDate createdAt;
  @ManyToOne(fetch = FetchType.EAGER)
  private UserStatus status;
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

  public UserAccount(Long id,
      @Size(min = 1, max = 16, message = "The user name should contains more then 3 and less then 17 letters") @NotNull @UniqueElements @Pattern(regexp = "[A-Za-z]+") String username,
      @Size(min = 3, max = 16, message = "The password should contains more then 3 and less then 17 letters or numbers") @NotNull @Pattern(regexp = "[A-Za-z0-9]+") String password,
      @Size(min = 1, max = 16, message = "The first name should contains more then 1 and less then 17 letters") @NotNull @Pattern(regexp = "[A-Za-z]+") String firstName,
      @Size(min = 1, max = 16, message = "The last name should contains more then 1 and less then 17 letters") @NotNull @Pattern(regexp = "[A-Za-z]+") String lastName,
      LocalDate createdAt, UserStatus status, Set<Role> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.createdAt = createdAt;
    this.status = status;
    this.roles = roles;
  }

  public UserAccount() {
  }

  public UserAccount(UserAccountDTO userAccount) {
    this.id = userAccount.getId();
    this.username = userAccount.getUsername();
    this.password = userAccount.getPassword();
    this.firstName = userAccount.getFirstName();
    this.lastName = userAccount.getLastName();
    this.status = userAccount.getStatus();
    this.createdAt = userAccount.getCreatedAt();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }


}
