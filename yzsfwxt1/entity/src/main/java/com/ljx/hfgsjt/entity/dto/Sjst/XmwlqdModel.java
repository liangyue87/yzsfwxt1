package com.ljx.hfgsjt.entity.dto.Sjst;

import com.ljx.hfgsjt.entity.dto.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "XM_WLQD")
public class XmwlqdModel extends BaseEntity{
    @Id
    private String id;
    private String xmid;
    private String wlkid;
    private String cjr;
    private String cjsj;
    private String bgr;
    private String bgsj;
    private int sxsl;
    @Transient
    private boolean isEdit=false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getWlkid() {
        return wlkid;
    }

    public void setWlkid(String wlkid) {
        this.wlkid = wlkid;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getBgsj() {
        return bgsj;
    }

    public void setBgsj(String bgsj) {
        this.bgsj = bgsj;
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
}
