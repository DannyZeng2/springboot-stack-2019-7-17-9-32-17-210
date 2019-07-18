package com.tw.apistackbase;

import com.alibaba.fastjson.JSON;
import com.tw.apistackbase.entity.CriminalCase;
import com.tw.apistackbase.entity.CriminalInfomation;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.entity.Prosecutor;
import com.tw.apistackbase.repository.ProsecutorRepository;
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
public class ProsecutorRepositoryTest {
    @Autowired
    private ProsecutorRepository prosecutorRepository;

    @Test
    public void should_return_a_prosecutor_when_find_by_id() {
        //given
        Prosecutor prosecutor_1 = new Prosecutor("Mike");
        Prosecutor prosecutor_2 = new Prosecutor("Lily");
        Prosecutor prosecutor_3 = new Prosecutor("Mary");

        prosecutorRepository.save(prosecutor_1);
        prosecutorRepository.save(prosecutor_2);
        prosecutorRepository.save(prosecutor_3);

        //when
        Prosecutor result  = prosecutorRepository.findById(prosecutor_2.getId()).get();

        //then
        Assertions.assertEquals(prosecutor_2.getId(), result.getId());
        Assertions.assertEquals(prosecutor_2.getName(), result.getName());
    }
}
