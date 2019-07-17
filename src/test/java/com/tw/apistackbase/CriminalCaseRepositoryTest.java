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
        CriminalCase criminalCase_1 = new CriminalCase("aaa",new Date(1000));
        CriminalCase criminalCase_2 = new CriminalCase("bbb",new Date(2000));

        criminalCaseRepository.save(criminalCase_1);
        criminalCaseRepository.save(criminalCase_2);

        CriminalCase resultCase  = criminalCaseRepository.findById(criminalCase_1.getId()).get();

        Assertions.assertEquals(criminalCase_1, resultCase);
    }


}
