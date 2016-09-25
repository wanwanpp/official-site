package com.site.model.sign;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wang0 on 2016/9/23.
 */

@Entity
@Table(name = "buqian")
@Getter
@Setter
public class Buqian {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;

    public Buqian(String name,String date) {
        this.date = date;
        this.name = name;
    }
}
