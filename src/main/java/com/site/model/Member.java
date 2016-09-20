package com.site.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by wang0 on 2016/9/19.
 */

@Getter
@Setter
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int grade;
    private String profession;

}
