package com.tw.apistackbase;

import com.alibaba.fastjson.JSON;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.CriminalInfomation;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.repository.CriminalCaseRepository;
import com.tw.apistackbase.repository.ProcuratorateRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcuratorateRepositoryTest {

    @Autowired
    private ProcuratorateRepository procuratorateRepository;
    @Test
    public void should_return_a_procuratorate_when_find_by_id() {
        //given
        Procuratorate procuratorate_1 = new Procuratorate("aaa");
        Procuratorate procuratorate_2 = new Procuratorate("bbb");
        Procuratorate procuratorate_3 = new Procuratorate("ccc");

        procuratorateRepository.save(procuratorate_1);
        procuratorateRepository.save(procuratorate_2);
        procuratorateRepository.save(procuratorate_3);

        //when
        Procuratorate result  = procuratorateRepository.findById(procuratorate_2.getId()).get();

        //then
        Assertions.assertEquals(procuratorate_2, result);
    }




}
