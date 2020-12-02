package com.task.management.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_role", schema = "user_management")
public class Role implements GrantedAuthority {

  private static final long serialVersionUID = 237555507203997783L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "VARCHAR(50)")
  private UserRoleName name;
  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<UserAccount> users;
  public Role(Long id, UserRoleName name, Set<UserAccount> users) {
    this.id = id;
    this.name = name;
    this.users = users;
  }

  public Role() {
  }

  public Role(UserRoleName name, HashSet<UserAccount> users) {
    this.users = users;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(id, role.id) &&
        name == role.name &&
        Objects.equals(users, role.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, users);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserRoleName getName() {
    return name;
  }

  public void setName(UserRoleName name) {
    this.name = name;
  }

  public Set<UserAccount> getUsers() {
    return users;
  }

  public void setUsers(Set<UserAccount> users) {
    this.users = users;
  }

  @Override
  public String getAuthority() {
    return getName().name();
  }
}