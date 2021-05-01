package com.Insurance.hm.test.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Test {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

}
