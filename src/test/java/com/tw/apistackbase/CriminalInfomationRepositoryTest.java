package com.tw.apistackbase;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.CriminalInfomation;
import com.tw.apistackbase.repository.CriminalCaseRepository;
import com.tw.apistackbase.repository.CriminalInfomationRepository;
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
public class CriminalInfomationRepositoryTest {

    @Autowired
    private CriminalInfomationRepository criminalInfoRepository;
    @Test
    public void should_return_case_info_when_find_by_id() {
        //given
        CriminalInfomation criminalInfo_1 = new CriminalInfomation("aaa","bbb");
        CriminalInfomation criminalInfo_2 = new CriminalInfomation("ccc","ddd");

        criminalInfoRepository.save(criminalInfo_1);
        criminalInfoRepository.save(criminalInfo_2);

        //when
        CriminalInfomation resultInfo  = criminalInfoRepository.findById(criminalInfo_2.getId()).get();

        //then
        Assertions.assertEquals(criminalInfo_2, resultInfo);
    }



}
