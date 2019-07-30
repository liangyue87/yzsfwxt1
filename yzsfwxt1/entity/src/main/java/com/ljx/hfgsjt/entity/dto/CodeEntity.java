package com.ljx.hfgsjt.entity.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.*;
@Entity
@Table(name = "XTGL_CODE")
public class CodeEntity {
  @Id
  private String id;
  @Column(name = "codevalue", length = 20)
  private String codevalue;

  @Column(name = "codename", length = 20)
  private String codename;

  @Column(name = "codedescription", length = 20)
  private String codedescription;

  @Column(name = "parentid", length = 20)
  private String parentid;

  @Column(name = "sort", length = 20)
  private String sort;

  @Column(name = "type", length = 20)
  private String type;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCodevalue() {
    return codevalue;
  }

  public void setCodevalue(String codevalue) {
    this.codevalue = codevalue;
  }


  public String getCodename() {
    return codename;
  }

  public void setCodename(String codename) {
    this.codename = codename;
  }


  public String getCodedescription() {
    return codedescription;
  }

  public void setCodedescription(String codedescription) {
    this.codedescription = codedescription;
  }


  public String getParentid() {
    return parentid;
  }

  public void setParentid(String parentid) {
    this.parentid = parentid;
  }


  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
