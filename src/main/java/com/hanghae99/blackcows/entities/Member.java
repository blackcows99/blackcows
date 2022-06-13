package com.hanghae99.blackcows.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member extends TimeStamp {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    public Member(String name, String email) {
        super();
    }

//    @Column(nullable = false)
//    private String password;
}
