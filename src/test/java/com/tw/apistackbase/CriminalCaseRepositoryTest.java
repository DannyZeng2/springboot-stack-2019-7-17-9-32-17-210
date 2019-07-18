package com.tw.apistackbase;

import com.alibaba.fastjson.JSON;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.CriminalInfomation;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.repository.CriminalCaseRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CriminalCaseRepositoryTest {

    @Autowired
    private CriminalCaseRepository criminalCaseRepository;

    @AfterEach
    public void initRepository() {


    }

    @Test
    public void should_return_case_when_find_by_id() {
        //given
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);

        //when
        CriminalCase resultCase  = criminalCaseRepository.findById(criminalCase_1.getId()).get();

        //then
        Assertions.assertEquals(criminalCase_1, resultCase);
    }
    @Test
    public void should_return_all_case_when_find_all_order_by_date() {
        //given
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000),new Procuratorate("p1"));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000),new Procuratorate("p2"));
        CriminalCase criminalCase_3 = new CriminalCase("ccc",new Date(3000),new Procuratorate("p3"));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);
        criminalCaseRepository.save(criminalCase_3);

        //when
        List<CriminalCase> resultList  = criminalCaseRepository.findByOrderByDateDesc();

        //then
        Assertions.assertEquals(criminalCase_3, resultList.get(0));
        Assertions.assertEquals(criminalCase_2, resultList.get(1));
        Assertions.assertEquals(criminalCase_1, resultList.get(2));
    }

    @Test
    public void should_return_cases_when_find_by_name() {
        //given
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000),new Procuratorate("p1"));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000),new Procuratorate("p2"));
        CriminalCase criminalCase_3 = new CriminalCase("bbb",new Date(3000),new Procuratorate("p3"));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);
        criminalCaseRepository.save(criminalCase_3);

        //when
        List<CriminalCase> resultList  = criminalCaseRepository.findAllByName("bbb");

        //then
        Assertions.assertEquals(2, resultList.size());
    }
    @Test
    public void should_detele_a_case_when_delete_by_id() {
        //given
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000),new Procuratorate("p1"));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000),new Procuratorate("p2"));
        CriminalCase criminalCase_3 = new CriminalCase("bbb",new Date(3000),new Procuratorate("p3"));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);
        criminalCaseRepository.save(criminalCase_3);

        //when
        criminalCaseRepository.deleteById(criminalCase_2.getId());

        //then
        Assertions.assertEquals(2, criminalCaseRepository.findAll().size());
    }

    @Test
    public void should_return_basic_case_and_info_when_find_by_id() {
        //given
        CriminalInfomation criminalInfo = new CriminalInfomation("aaa","bbb");
        CriminalCase criminalCase = new CriminalCase("case1",new Date(1000),criminalInfo);
        criminalCase.setProcuratorate(new Procuratorate("p1"));

        criminalCaseRepository.save(criminalCase);

        //when
        CriminalCase resultCase  = criminalCaseRepository.findById(criminalCase.getId()).get();

        //then
        Assertions.assertEquals(JSON.toJSONString(criminalCase), JSON.toJSONString(resultCase));
    }


}
