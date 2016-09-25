package com.site.model.sign;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wang0 on 2016/9/13.
 */
@Setter
@Getter
public class ItemEntity {

    private int id;
    private String name;
    private long comeTimeStamp;
    private long leaveTimeStamp;
    private int minutes;

}
