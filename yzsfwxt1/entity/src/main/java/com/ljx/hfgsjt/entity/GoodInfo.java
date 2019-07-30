package com.ljx.hfgsjt.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GOODINFO")
@Data
public class GoodInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long tid;

    @Column(name = "ttitle", length = 20)
    private String title;

    @Column(name = "tprice")
    private double price;

    @Column(name = "torder")
    private int order;

    @Column(name = "ttypeId")
    private Long typeId;
}
