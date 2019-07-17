package com.tw.apistackbase;

import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.repository.CriminalCaseRepository;
import org.junit.Test;
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
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000));
        CriminalCase criminalCase_3 = new CriminalCase("ccc",new Date(3000));

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
    public void should_return_a_case_when_find_by_name() {
        //given
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000));
        CriminalCase criminalCase_3 = new CriminalCase("bbb",new Date(3000));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);
        criminalCaseRepository.save(criminalCase_3);

        //when
        List<CriminalCase> resultList  = criminalCaseRepository.findAllByName("bbb");

        //then
        Assertions.assertEquals(2, resultList.size());
    }

}
