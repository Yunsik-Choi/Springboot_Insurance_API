package com.Insurance.hm.test;

import com.Insurance.hm.test.domain.Test;
import com.Insurance.hm.test.domain.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/")
    public String index(){
        return "<h1>Hello</h1>";
    }

    @GetMapping("/api/test/save")
    public Test saveGetTest(
            @RequestParam(value = "name",defaultValue = "최윤식") String name,
            @RequestParam(value = "age", defaultValue = "24") int age){
        Test test = Test.builder()
                        .name(name)
                        .age(age)
                        .build();
        testRepository.saveTest(test);
        return test;
    }

    @GetMapping("/api/test")
    public List<Test> showAllTest(){
        List<Test> allTest = testRepository.findAllTest();
        return allTest;
    }

    @GetMapping("/api/test/{id}")
    public Test showTest(@PathVariable Long id){
        Test test = testRepository.searchTest(id);
        return test;
    }

    @PostMapping("/api/test/save")
    public Test savePostTest(@RequestBody Test test){
        Long testID = testRepository.saveTest(test);
        Test findTest = testRepository.searchTest(testID);
        return findTest;
    }

    @PostMapping("/api/test/{id}/delete")
    public Test deleteTest(@PathVariable Long id){
        Test test = testRepository.deleteTest(id);
        return test;
    }

}
