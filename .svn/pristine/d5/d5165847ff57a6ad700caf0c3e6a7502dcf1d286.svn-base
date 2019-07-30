package com.ljx.hfgsjt.entity.dto;

import javax.persistence.*;

@Entity
@Table(name = "XTGL_MENU")
public class MenuEntity implements Comparable<MenuEntity>{

  @Id
  private String menuid;

  @Column(name = "parentid", length = 20)
  private String parentid;

  @Column(name = "menuname", length = 20)
  private String menuname;

  @Column(name = "ordinal", length = 20)
  private String ordinal;

  @Column(name = "menuurl", length = 20)
  private String menuurl;

  @Column(name = "target", length = 20)
  private String target;

  @Column(name = "bigicon", length = 20)
  private String bigicon;

  @Column(name = "menuicon", length = 20)
  private String menuicon;

  @Column(name = "valid", length = 20)
  private String valid;


  public String getMenuid() {
    return menuid;
  }

  public void setMenuid(String menuid) {
    this.menuid = menuid;
  }


  public String getParentid() {
    return parentid;
  }

  public void setParentid(String parentid) {
    this.parentid = parentid;
  }


  public String getMenuname() {
    return menuname;
  }

  public void setMenuname(String menuname) {
    this.menuname = menuname;
  }


  public String getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(String ordinal) {
    this.ordinal = ordinal;
  }


  public String getMenuurl() {
    return menuurl;
  }

  public void setMenuurl(String menuurl) {
    this.menuurl = menuurl;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }


  public String getBigicon() {
    return bigicon;
  }

  public void setBigicon(String bigicon) {
    this.bigicon = bigicon;
  }


  public String getMenuicon() {
    return menuicon;
  }

  public void setMenuicon(String menuicon) {
    this.menuicon = menuicon;
  }


  public String getValid() {
    return valid;
  }

  public void setValid(String valid) {
    this.valid = valid;
  }

  @Override
  public int compareTo(MenuEntity o) {
    if(ordinal == null || o.getOrdinal() == null){
      return -1;
    }else if( Integer.parseInt(ordinal) > Integer.parseInt(o.getOrdinal()) ) {
      return 1;
    }else if(ordinal == o.getOrdinal()) {
      return 0;
    }
    return -1;
  }
}
