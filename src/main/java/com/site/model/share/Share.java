package com.site.model.share;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "share")
@Getter
@Setter
@ToString
public class Share{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private String sharepeople;
    private Integer fanwei;
    private Timestamp createTime;
    private Integer groupid;


    public Share(String title,String content,String sharepeople,Integer fanwei,Integer group) {
        this.title=title;
        this.content=content;
        this.sharepeople=sharepeople;
        this.fanwei=fanwei;
        this.createTime=new Timestamp(System.currentTimeMillis());
    }

    public Share() {

    }


}
