package com.task.management.entity;

import java.util.stream.Stream;

public enum UserStatusName {
  INACTIVE,
  ACTIVE;

  static UserStatusName of(String name) {
    return Stream.of(UserStatusName.values()).filter(t -> t.name().equalsIgnoreCase(name)).findFirst()
        .orElse(UserStatusName.ACTIVE);
  }
}
