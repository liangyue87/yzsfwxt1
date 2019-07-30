package com.ljx.hfgsjt.entity.dto;


import javax.persistence.*;

@Entity //实体类注解
@Table(name = "XTGL_ORG")
public class DepartmentEntity {
    @Id //主键
    private String orgid; //部门编号

    @Column(name = "parentid", length = 20)
      private String parentid; //上级部门编号

    @Column(name = "orgname", length = 20)
      private String orgname; //部门名称

    @Column(name = "ordinal", length = 20)
      private Long ordinal; //排列顺序

    @Column(name = "isused", length = 20)
      private String isused; //是否启用

    @Column(name = "orgLevel", length = 20)
      private Long orgLevel; //部门层级
    @Column(name = "bmfzrid")
    private String bmfzrid; //部门负责人id

    @Transient
    private String bmfzr;


    public String getOrgid() {
    return orgid;
    }

    public void setOrgid(String orgid) {
    this.orgid = orgid;
    }


    public String getParentid() {
    return parentid;
    }

    public void setParentid(String parentid) {
    this.parentid = parentid;
    }


    public String getOrgname() {
    return orgname;
    }

    public void setOrgname(String orgname) {
    this.orgname = orgname;
    }

    public Long getOrdinal() {
    return ordinal;
    }

    public void setOrdinal(Long ordinal) {
    this.ordinal = ordinal;
    }

    public Long getOrgLevel() {
    return orgLevel;
    }

    public void setOrgLevel(Long orgLevel) {
    this.orgLevel = orgLevel;
    }

    public String getIsused() {
    return isused;
    }

    public void setIsused(String isused) {
    this.isused = isused;
    }

    public String getBmfzr() {
        return bmfzr;
    }

    public void setBmfzr(String bmfzr) {
        this.bmfzr = bmfzr;
    }

    public String getBmfzrid() {
        return bmfzrid;
    }

    public void setBmfzrid(String bmfzrid) {
        this.bmfzrid = bmfzrid;
    }
}
