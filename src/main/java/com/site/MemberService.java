package com.site;

import com.site.mapper.login.MemberMapper;
import com.site.model.login.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MemberService implements UserDetailsService {

	@Autowired
	MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String stuId) {

		Member member= memberMapper.findByStuId(Long.valueOf(stuId));
		if(member == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		return member;
	}

}
