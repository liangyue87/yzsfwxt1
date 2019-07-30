package com.ljx.hfgsjt.entity.dto.Sjst;

import com.ljx.hfgsjt.entity.dto.BaseEntity;

import javax.persistence.Id;
import java.util.Date;


public class HsjglrFj extends BaseEntity {
    @Id
    private String id;//id
    private String sjthsid;//设计图会审主表标识
    private String fjlx_yj;//一级附件类型
    private String fjlx_ej;//二级附件类型
    private String fjlj;//附件路径
    private String fjhz;//附件后缀
    private String fjmc;//附件名称
    private String scr;//上传人
    private Date scsj;//上传时间
    private String sc_flag;//是否删除 1删除  逻辑删除  物理是否删除 如何删除待定SC_FLAG
    private String shcr;//删除人
    private Date shcj;//删除时间
    private String shcsj;
    private String xmid;

    private String scsj_str;
    private String username;


    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getShcsj() {
        return shcsj;
    }

    public void setShcsj(String shcsj) {
        this.shcsj = shcsj;
    }

    public String getScsj_str() {
        return scsj_str;
    }

    public void setScsj_str(String scsj_str) {
        this.scsj_str = scsj_str;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSjthsid() {
        return sjthsid;
    }

    public void setSjthsid(String sjthsid) {
        this.sjthsid = sjthsid;
    }

    public String getFjlx_yj() {
        return fjlx_yj;
    }

    public void setFjlx_yj(String fjlx_yj) {
        this.fjlx_yj = fjlx_yj;
    }

    public String getFjlx_ej() {
        return fjlx_ej;
    }

    public void setFjlx_ej(String fjlx_ej) {
        this.fjlx_ej = fjlx_ej;
    }

    public String getFjlj() {
        return fjlj;
    }

    public void setFjlj(String fjlj) {
        this.fjlj = fjlj;
    }

    public String getFjhz() {
        return fjhz;
    }

    public void setFjhz(String fjhz) {
        this.fjhz = fjhz;
    }

    public String getFjmc() {
        return fjmc;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    public String getScr() {
        return scr;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    public Date getScsj() {
        return scsj;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }

    public String getSc_flag() {
        return sc_flag;
    }

    public void setSc_flag(String sc_flag) {
        this.sc_flag = sc_flag;
    }

    public String getShcr() {
        return shcr;
    }

    public void setShcr(String shcr) {
        this.shcr = shcr;
    }

    public Date getShcj() {
        return shcj;
    }

    public void setShcj(Date shcj) {
        this.shcj = shcj;
    }
}
