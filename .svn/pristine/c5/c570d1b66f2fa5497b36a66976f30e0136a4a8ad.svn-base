package com.ljx.hfgsjt.entity.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "XTGL_USER")
public class UserEntity {
  @Id
  private String userid;
  private String account;
  private String password;
  @Column(name = "username", length = 20)
  private String username;
  private String mobilephone;
  private String email;
  private String islocked;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date regtime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date lastlogin;
  private String orgnumber;
  private String userrole;
  private String gddh;


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public void setMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIslocked() {
    return islocked;
  }

  public void setIslocked(String islocked) {
    this.islocked = islocked;
  }

  public Date getRegtime() {
    return regtime;
  }

  public void setRegtime(Date regtime) {
    this.regtime = regtime;
  }

  public Date getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin(Date lastlogin) {
    this.lastlogin = lastlogin;
  }

  public String getOrgnumber() {
    return orgnumber;
  }

  public void setOrgnumber(String orgnumber) {
    this.orgnumber = orgnumber;
  }

  public String getUserrole() {
    return userrole;
  }

  public void setUserrole(String userrole) {
    this.userrole = userrole;
  }

  public String getGddh() {
    return gddh;
  }

  public void setGddh(String gddh) {
    this.gddh = gddh;
  }
}
