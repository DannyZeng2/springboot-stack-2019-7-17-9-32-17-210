package com.tw.apistackbase.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Procuratorate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false,unique=true,length =50)
    private String name;

    public List<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(List<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    @OneToMany(mappedBy="procuratorate",cascade = CascadeType.ALL)
    private List<CriminalCase> criminalCases;

    public Procuratorate(String name, List<CriminalCase> criminalCases) {
        this.name = name;
        this.criminalCases = criminalCases;
    }

    public Procuratorate(String name) {
        this.name = name;
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
}
