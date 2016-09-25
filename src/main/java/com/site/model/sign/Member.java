package com.site.model.sign;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wang0 on 2016/9/13.
 */
@Entity
@Table(name = "member")
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(name = "stu_id")
    private Long stuId;
    private Integer grade;

    @Column(name = "isstart")
    private Integer isstart;
    private Long recordId;
}
