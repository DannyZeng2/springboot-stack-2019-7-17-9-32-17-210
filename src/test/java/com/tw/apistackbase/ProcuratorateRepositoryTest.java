package com.tw.apistackbase;

import com.alibaba.fastjson.JSON;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.CriminalInfomation;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.entity.Prosecutor;
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
        Assertions.assertEquals(procuratorate_2.getId(), result.getId());
        Assertions.assertEquals(procuratorate_2.getName(), result.getName());
    }

    @Test
    public void should_return_basic_case_and_info_when_find_by_id() {
        //given
        CriminalInfomation criminalInfo_1 = new CriminalInfomation("aaa", "bbb");
        CriminalCase criminalCase_1 = new CriminalCase("case1", new Date(1000), criminalInfo_1);

        CriminalInfomation criminalInfo_2 = new CriminalInfomation("aaa", "bbb");
        CriminalCase criminalCase_2 = new CriminalCase("case1", new Date(1000), criminalInfo_2);

        List<CriminalCase> caseList = asList(criminalCase_1,criminalCase_2);

        Procuratorate procuratorate = new Procuratorate("p1",caseList);
        procuratorateRepository.save(procuratorate);

        //when
        Procuratorate result = procuratorateRepository.findById(procuratorate.getId()).get();

        Assertions.assertEquals(JSON.toJSONString(procuratorate)
                , JSON.toJSONString(result));
    }

    @Test
    public void should_return_include_prosecutor_when_find_procuratorate_info() {
        //given
        Prosecutor prosecutor_1 = new Prosecutor("Mike");
        Prosecutor prosecutor_2 = new Prosecutor("Lily");

        List<Prosecutor> prosecutors = asList(prosecutor_1,prosecutor_2);

        Procuratorate procuratorate = new Procuratorate("p1");
        procuratorate.setProsecutors(asList(prosecutor_1,prosecutor_2));
        procuratorateRepository.save(procuratorate);

        //when
        Procuratorate result  = procuratorateRepository.findById(procuratorate.getId()).get();

        //then
        Assertions.assertEquals(JSON.toJSONString(procuratorate), JSON.toJSONString(result));
    }
}
