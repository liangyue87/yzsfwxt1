package com.ljx.hfgsjt.entity.dto.message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DX_DFS")
public class DxDfsEntity {

  @Id
  private String id;
  private String dxmbid;
  private String fsr;
  private Date fssj;
  private String fscs;
  private String fsnr;
  private String jsr;
  private String jshm;
  private String taskid;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getDxmbid() {
    return dxmbid;
  }

  public void setDxmbid(String dxmbid) {
    this.dxmbid = dxmbid;
  }


  public String getFsr() {
    return fsr;
  }

  public void setFsr(String fsr) {
    this.fsr = fsr;
  }


  public Date getFssj() {
    return fssj;
  }

  public void setFssj(Date fssj) {
    this.fssj = fssj;
  }


  public String getFscs() {
    return fscs;
  }

  public void setFscs(String fscs) {
    this.fscs = fscs;
  }


  public String getFsnr() {
    return fsnr;
  }

  public void setFsnr(String fsnr) {
    this.fsnr = fsnr;
  }


  public String getJsr() {
    return jsr;
  }

  public void setJsr(String jsr) {
    this.jsr = jsr;
  }


  public String getJshm() {
    return jshm;
  }

  public void setJshm(String jshm) {
    this.jshm = jshm;
  }


  public String getTaskid() {
    return taskid;
  }

  public void setTaskid(String taskid) {
    this.taskid = taskid;
  }

}
