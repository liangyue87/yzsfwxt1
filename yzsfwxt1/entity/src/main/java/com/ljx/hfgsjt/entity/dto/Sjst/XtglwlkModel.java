package com.ljx.hfgsjt.entity.dto.Sjst;

import com.ljx.hfgsjt.entity.dto.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "XTGL_WLK")
public class XtglwlkModel extends BaseEntity{
    @Id
    private String id;
    private String wlbm;
    private String wlmc;
    private String ggxh;
    private String wljbfl;
    private String jbjldw;
    @Transient
    private int sxsl=0;
    @Transient
    private String xmid;
    @Transient
    private boolean isEdit=false;
    @Transient
    private  boolean pickIsEdit = false;

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWlbm() {
        return wlbm;
    }

    public void setWlbm(String wlbm) {
        this.wlbm = wlbm;
    }

    public String getWlmc() {
        return wlmc;
    }

    public void setWlmc(String wlmc) {
        this.wlmc = wlmc;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getWljbfl() {
        return wljbfl;
    }

    public void setWljbfl(String wljbfl) {
        this.wljbfl = wljbfl;
    }

    public String getJbjldw() {
        return jbjldw;
    }

    public void setJbjldw(String jbjldw) {
        this.jbjldw = jbjldw;
    }

    public int getSxsl() {
        return sxsl;
    }

    public void setSxsl(int sxsl) {
        this.sxsl = sxsl;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isPickIsEdit() {
        return pickIsEdit;
    }

    public void setPickIsEdit(boolean pickIsEdit) {
        this.pickIsEdit = pickIsEdit;
    }
}
