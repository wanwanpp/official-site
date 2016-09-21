package com.site.model.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by wang0 on 2016/9/21.
 */

@Setter
@Getter
@ToString
public class Roles implements Serializable {


    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String mark;

}
