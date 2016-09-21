package com.site.model.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by wang0 on 2016/9/21.
 */
@Getter
@Setter
@ToString
public class RoleMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long memberId;
    private Long roleId;
}
