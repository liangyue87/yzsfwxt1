package com.ljx.hfgsjt.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HELLOTEST")
public class HelloTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long tid;

    @Column(name = "tname", length = 20)
    private String name;

    @Column(name = "tcode",length = 20)
    private String tcode;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    @Override
    public String toString() {
        return "HelloTest{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", tcode='" + tcode + '\'' +
                '}';
    }
}
