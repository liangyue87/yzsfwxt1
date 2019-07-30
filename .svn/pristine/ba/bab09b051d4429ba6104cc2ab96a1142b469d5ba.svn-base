package com.ljx.hfgsjt.entity.dto.htqd;

import java.util.List;

public class FqbEntity implements Comparable<FqbEntity>{

    private String id;
    private String htzbid;
    private String qs;
    private String zje;
    private String perce;
    private String bz;
    private String cjsj;
    private String xgsj;

    private List<FqmxbEntity> fqmx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtzbid() {
        return htzbid;
    }

    public void setHtzbid(String htzbid) {
        this.htzbid = htzbid;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public String getZje() {
        return zje;
    }

    public void setZje(String zje) {
        this.zje = zje;
    }

    public String getPerce() {
        return perce;
    }

    public void setPerce(String perce) {
        this.perce = perce;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    public List<FqmxbEntity> getFqmx() {
        return fqmx;
    }

    public void setFqmx(List<FqmxbEntity> fqmx) {
        this.fqmx = fqmx;
    }

    public int compareTo(FqbEntity o) {
        if(qs == null || o.getQs() == null){
            return -1;
        }else if( Integer.parseInt(qs) > Integer.parseInt(o.getQs()) ) {
            return 1;
        }else if(qs == o.getQs()) {
            return 0;
        }
        return -1;
    }
}
