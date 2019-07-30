package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "YS_YSZY")

public class YsYszy {
@Id
  private String userid;
  private String account;
  private String password;
  private String username;
  private String mobilephone;
  private String email;
  private String islocked;
  private  Date regtime;
  private  Date lastlogin;
  private String orgnumber;
  private String userrole;
  private String rolename;


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


  public  Date getRegtime() {
    return regtime;
  }

  public void setRegtime( Date regtime) {
    this.regtime = regtime;
  }


  public  Date getLastlogin() {
    return lastlogin;
  }

  public void setLastlogin( Date lastlogin) {
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


  public String getRolename() {
    return rolename;
  }

  public void setRolename(String rolename) {
    this.rolename = rolename;
  }

}
