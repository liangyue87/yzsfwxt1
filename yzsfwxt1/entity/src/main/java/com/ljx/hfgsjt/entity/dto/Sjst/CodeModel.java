package com.ljx.hfgsjt.entity.dto.Sjst;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "XTGL_CODE")
public class CodeModel {
    @Id
    private String id;
    private String codevalue;
    private String codename;
    @Transient
    private String label;
    @Transient
    private int count;
    @Transient
    private List<CodeModel> children;
    @Transient
    private String parentid;
    @Transient
    private String parentname;

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodevalue() {
        return codevalue;
    }

    public void setCodevalue(String codevalue) {
        this.codevalue = codevalue;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CodeModel> getChildren() {
        return children;
    }

    public void setChildren(List<CodeModel> children) {
        this.children = children;
    }
}
