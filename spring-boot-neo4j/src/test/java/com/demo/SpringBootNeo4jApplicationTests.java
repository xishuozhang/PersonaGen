package com.demo;

import com.demo.entity.Person;
import com.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.neo4j.driver.Values.parameters;

@SpringBootTest
class SpringBootNeo4jApplicationTests {

    @Autowired
    PersonRepository repository;




    @Test
   void getAll(){
        List<Person> all = repository.getAll();
        for (Person person :all){
            System.out.println(person);
        }
    }


}
