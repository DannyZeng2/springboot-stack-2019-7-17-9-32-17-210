package com.tw.apistackbase.entity;


import javax.persistence.*;

@Entity
@Table
public class CriminalInfomation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false,length =255)
    private String subCase;

    @Column(nullable=false,length =255)
    private String objCase;

    public CriminalInfomation( String subCase, String objCase) {
        this.subCase = subCase;
        this.objCase = objCase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubCase() {
        return subCase;
    }

    public void setSubCase(String subCase) {
        this.subCase = subCase;
    }

    public String getObjCase() {
        return objCase;
    }

    public void setObjCase(String objCase) {
        this.objCase = objCase;
    }
}
