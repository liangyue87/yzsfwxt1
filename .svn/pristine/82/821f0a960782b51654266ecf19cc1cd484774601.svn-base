package com.ljx.hfgsjt.entity.dto.message;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DX_FSJL")
public class DxFsjlEntity {

  @Id
  private String id;
  private String dxmbid;
  private String fsr;
  private Date fssj;
  private String fscs;
  private String sfcg;
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


  public String getSfcg() {
    return sfcg;
  }

  public void setSfcg(String sfcg) {
    this.sfcg = sfcg;
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

  public DxFsjlEntity() {
  }

  public DxFsjlEntity(DxDfsEntity df) {
    this.id = df.getId();
    this.dxmbid = df.getDxmbid();
    this.fsr = df.getFsr();
    this.fssj = new Date();
    this.fscs = df.getFscs();
    this.fsnr = df.getFsnr();
    this.jsr = df.getJsr();
    this.jshm = df.getJshm();
    this.taskid = df.getTaskid();
  }

  public DxFsjlEntity(String id, String dxmbid, String fsr, Date fssj, String fscs, String sfcg, String fsnr, String jsr, String jshm, String taskid) {
    this.id = id;
    this.dxmbid = dxmbid;
    this.fsr = fsr;
    this.fssj = fssj;
    this.fscs = fscs;
    this.sfcg = sfcg;
    this.fsnr = fsnr;
    this.jsr = jsr;
    this.jshm = jshm;
    this.taskid = taskid;
  }
}
