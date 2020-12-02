package com.task.management.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_status", schema = "user_management")
public class UserStatus implements Serializable {

  private static final long serialVersionUID = 8543805418314277379L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(columnDefinition = "VARCHAR(50) default 'INACTIVE'")
  private UserStatusName statusName;
  @Transient
  @OneToMany(mappedBy = "status")
  private Set<UserAccount> users;

  public UserStatus() {
  }

  public UserStatus(Long id, @NotNull UserStatusName statusName) {
    this.id = id;
    this.statusName = statusName;
  }

  public UserStatus(Long id, @NotNull UserStatusName statusName,
      Set<UserAccount> users) {
    this.id = id;
    this.statusName = statusName;
    this.users = users;
  }

  public UserStatus(UserStatusName active, Set<UserAccount> users) {
    this.statusName = active;
    this.users = users;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserStatus that = (UserStatus) o;
    return Objects.equals(id, that.id) &&
        statusName == that.statusName &&
        Objects.equals(users, that.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, statusName, users);
  }

  public UserStatusName getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = UserStatusName.of(statusName);
  }

  public Set<UserAccount> getUsers() {
    return users;
  }

  public void setUsers(Set<UserAccount> users) {
    this.users = users;
  }
}
