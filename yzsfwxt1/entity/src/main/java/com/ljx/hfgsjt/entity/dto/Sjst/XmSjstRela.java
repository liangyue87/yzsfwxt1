package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XM_SJST_RELA")
public class XmSjstRela {
    @Id
    private String id;
    private String xmid;
    private String sjstid;

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

    public String getSjstid() {
        return sjstid;
    }

    public void setSjstid(String sjstid) {
        this.sjstid = sjstid;
    }
}
