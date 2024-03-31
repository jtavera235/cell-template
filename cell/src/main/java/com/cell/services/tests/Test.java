package com.cell.services.tests;

import com.cell.common.utils.ModelEntityId;
import java.util.UUID;

public class Test {

  private ModelEntityId id;
  private String name;
  private String email;
  private String password;
  private int age;

  public Test() {}

  public static Test newInstance(String name, String email, String password, int age) {
    Test test = new Test();
    test.age = age;
    test.password = password;
    test.name = name;
    test.email = email;
    return test;
  }

  public static Test ref(ModelEntityId id) {
    Test test = new Test();
    test.id = id;
    return test;
  }

  public static Test load(ModelEntityId id) {
    return TestDao.get(id);
  }

  public void save() {
    ModelEntityId id = new ModelEntityId("Test", UUID.randomUUID());
    this.id = id;
    TestDao.save(this);
  }

  public void update() {
  }
}