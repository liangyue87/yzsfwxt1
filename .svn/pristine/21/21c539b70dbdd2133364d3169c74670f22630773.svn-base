package com.ljx.hfgsjt.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GOODTYPE")
@Data
public class GoodType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long tid;

    @Column(name = "tname")
    private String name;

    @Column(name = "tshow")
    private int show;

    @Column(name = "torder")
    private int order;
}
