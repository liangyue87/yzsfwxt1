package com.ljx.hfgsjt.sys.entity;

import com.ljx.hfgsjt.user.entity.UserInfoEO;
import com.ljx.hfgsjt.util.persistence.entity.TreeEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;



@Entity
@Table(name = "sys_office")
@Data
public class SysOfficeEO extends TreeEntity<SysOfficeEO> {
    private String areaId;
    private String code;
    private Integer type;
    private Integer office_level;
    private String address;
    private String zipCode;
    private String masterId;
    private String phone;
    private String fax;
    private String email;
    private String remarks;

    private SysAreaEO area;
    private UserInfoEO masterUserInfo;

    private String otherInfo;
    private Map<String, String> otherInfoMap = new HashMap<>();

    @Basic
    @Column(name = "area_id")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "office_level")
    public Integer getOffice_level() {
        return office_level;
    }

    public void setOffice_level(Integer office_level) {
        this.office_level = office_level;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "zip_code")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "master_id")
    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @ManyToOne
    @JoinColumn(name = "area_id", insertable = false, updatable = false)
    public SysAreaEO getArea() {
        return area;
    }

    public void setArea(SysAreaEO area) {
        this.area = area;
    }

    @ManyToOne
    @JoinColumn(name = "master_id", insertable = false, updatable = false)
    public UserInfoEO getMasterUserInfo() {
        return masterUserInfo;
    }

    public void setMasterUserInfo(UserInfoEO masterUserInfo) {
        this.masterUserInfo = masterUserInfo;
    }

    @Basic
    @Column(name = "other_info")
    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Transient
    public Map<String, String> getOtherInfoMap() {
        return otherInfoMap;
    }

    public void setOtherInfoMap(Map<String, String> otherInfoMap) {
        this.otherInfoMap = otherInfoMap;
    }
}
