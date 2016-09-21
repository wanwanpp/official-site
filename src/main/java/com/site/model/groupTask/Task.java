package com.site.model.groupTask;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Created by wang0 on 2016/9/21.
 */

@Getter
@Setter
@ToString
public class Task {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer group;
    private Timestamp startdate;
    private Timestamp enddate;

}
