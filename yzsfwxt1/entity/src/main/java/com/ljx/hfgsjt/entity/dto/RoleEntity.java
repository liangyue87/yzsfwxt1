package com.ljx.hfgsjt.entity.dto;


import javax.persistence.*;

@Entity //实体类注解
@Table(name = "XTGL_ROLE")
public class RoleEntity {
    @Id
    private String roleid;
    @Column(name = "rolename", length = 20)
    private String rolename;
    @Column(name = "note", length = 20)
    private String note;
    @Column(name = "ordinal", length = 20)
    private Long ordinal;
    @Column(name = "roletype", length = 20)
    private String roletype;


    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Long getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }


    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }


}
