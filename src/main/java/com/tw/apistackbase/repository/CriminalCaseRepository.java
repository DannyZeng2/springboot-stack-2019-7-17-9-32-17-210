package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase,Long> {
    List<CriminalCase> findByOrderByDateDesc();

    CriminalCase findByName(String name);

    List<CriminalCase>  findAllByName(String name);
}
