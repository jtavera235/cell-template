package com.cell.services.accounts.persist;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountModel {

  private String id;
  private String email;
  private String username;
  private Boolean enabled;
  private Date registeredDate;

  @Column(name = "email", unique = true, nullable = false)
  public String getEmail() {
    return email;
  }

  @Column(name = "registered_date", columnDefinition="DATE")
  public Date getRegisteredDate() {
    return registeredDate;
  }

  @Id
  @Column(name = "id", unique = true, nullable = false)
  public String getId() {
    return id;
  }

  @Column(name = "enabled", nullable = false)
  public Boolean getEnabled() {
    return enabled;
  }

  @Column(name = "username", unique = true, nullable = false)
  public String getUsername() {
    return username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRegisteredDate(Date date) {
    this.registeredDate = date;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
