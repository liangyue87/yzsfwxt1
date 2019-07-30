package com.ljx.hfgsjt.entity.dto.Activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WF_RU_INST_CONTENT")
public class WfRuInstContentEntity {
  @Id
  private String contentid;
  @Column(name = "proc_inst_id")
  private String procInstId;
  @Column(name = "table_name")
  private String tableName;
  private String contentremark;
  private String contentname;
  private String contenttype;
  private String readonly;
  private String contentvalue;


  public String getContentid() {
    return contentid;
  }

  public void setContentid(String contentid) {
    this.contentid = contentid;
  }


  public String getProcInstId() {
    return procInstId;
  }

  public void setProcInstId(String procInstId) {
    this.procInstId = procInstId;
  }


  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }


  public String getContentremark() {
    return contentremark;
  }

  public void setContentremark(String contentremark) {
    this.contentremark = contentremark;
  }


  public String getContentname() {
    return contentname;
  }

  public void setContentname(String contentname) {
    this.contentname = contentname;
  }


  public String getContenttype() {
    return contenttype;
  }

  public void setContenttype(String contenttype) {
    this.contenttype = contenttype;
  }


  public String getReadonly() {
    return readonly;
  }

  public void setReadonly(String readonly) {
    this.readonly = readonly;
  }

  public String getContentvalue() {
    return contentvalue;
  }

  public void setContentvalue(String contentvalue) {
    this.contentvalue = contentvalue;
  }
}
