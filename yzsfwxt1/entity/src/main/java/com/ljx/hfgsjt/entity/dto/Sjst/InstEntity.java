package com.ljx.hfgsjt.entity.dto.Sjst;

import java.util.Date;

public class InstEntity {

    private String proc_inst_id;

    private String pdef_id;

    private String parammap;

    private Date startdt;

    private String startuser;

    private String procstatus;

    private String stopuser;

    private Date stopdt;

    public String getProcInstId() {
        return proc_inst_id;
    }

    public void setProcInstId(String procInstId) {
        this.proc_inst_id = procInstId;
    }

    public String getPdefId() {
        return pdef_id;
    }

    public void setPdefId(String pdefId) {
        this.pdef_id = pdefId;
    }

    public String getParammap() {
        return parammap;
    }

    public void setParammap(String parammap) {
        this.parammap = parammap;
    }

    public Date getStartdt() {
        return startdt;
    }

    public void setStartdt(Date startdt) {
        this.startdt = startdt;
    }

    public String getStartuser() {
        return startuser;
    }

    public void setStartuser(String startuser) {
        this.startuser = startuser;
    }

    public String getProcstatus() {
        return procstatus;
    }

    public void setProcstatus(String procstatus) {
        this.procstatus = procstatus;
    }

    public String getStopuser() {
        return stopuser;
    }

    public void setStopuser(String stopuser) {
        this.stopuser = stopuser;
    }

    public Date getStopdt() {
        return stopdt;
    }

    public void setStopdt(Date stopdt) {
        this.stopdt = stopdt;
    }
}
