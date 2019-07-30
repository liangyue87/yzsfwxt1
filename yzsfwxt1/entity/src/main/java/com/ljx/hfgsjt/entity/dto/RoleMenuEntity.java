package com.ljx.hfgsjt.entity.dto;
import javax.persistence.*;

@Entity
@Table(name = "XTGL_ROLEMENU")
public class RoleMenuEntity {

  @Id
  private String id;
  @Column(name = "roleid", length = 20)
  private String roleid;
  @Column(name = "menuid", length = 20)
  private String menuid;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getRoleid() {
    return roleid;
  }

  public void setRoleid(String roleid) {
    this.roleid = roleid;
  }


  public String getMenuid() {
    return menuid;
  }

  public void setMenuid(String menuid) {
    this.menuid = menuid;
  }

  public RoleMenuEntity(String id, String roleid, String menuid) {
    this.id = id;
    this.roleid = roleid;
    this.menuid = menuid;
  }

  public RoleMenuEntity() {
  }
}
