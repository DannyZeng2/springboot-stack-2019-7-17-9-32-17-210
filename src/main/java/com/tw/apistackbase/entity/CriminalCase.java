package com.tw.apistackbase.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class CriminalCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false,length =255)
    private String name;

    @Column(nullable=false)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private CriminalInfomation criminalInfomation;

    public CriminalCase(String name, Date date, CriminalInfomation criminalInfomation) {
        this.name = name;
        this.date = date;
        this.criminalInfomation = criminalInfomation;
    }

    public CriminalCase(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CriminalInfomation getCriminalInfomation() {
        return criminalInfomation;
    }

    public void setCriminalInfomation(CriminalInfomation criminalInfomation) {
        this.criminalInfomation = criminalInfomation;
    }
}
