package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name="SJST_SJTHSZB")
public class HsjglrZb {
    @Id
    private String id;//id
    private String sjstrwid;//设计审图任务标识
    private String xmjlyj;//项目经理意见
    private String cyry;//参与人员
    private Date cjsj;//创建时间
    private String cjr;//创建人
    private String xmid;//关联主表id


    @Transient
    private String cjsj_str;

    public String getCjsj_str() {
        return cjsj_str;
    }

    public void setCjsj_str(String cjsj_str) {
        this.cjsj_str = cjsj_str;
    }

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

    public String getSjstrwid() {
        return sjstrwid;
    }

    public void setSjstrwid(String sjstrwid) {
        this.sjstrwid = sjstrwid;
    }

    public String getXmjlyj() {
        return xmjlyj;
    }

    public void setXmjlyj(String xmjlyj) {
        this.xmjlyj = xmjlyj;
    }

    public String getCyry() {
        return cyry;
    }

    public void setCyry(String cyry) {
        this.cyry = cyry;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }
}
