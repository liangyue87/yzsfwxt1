package com.ljx.hfgsjt.entity.dto.yswt;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XTGL_FYLX")
public class XtglFylx {
  @Id
  private String bh;
  private String mc;
  private String type;
  private String value;


  public String getBh() {
    return bh;
  }

  public void setBh(String bh) {
    this.bh = bh;
  }


  public String getMc() {
    return mc;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
