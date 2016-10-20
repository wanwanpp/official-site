package com.site;

import com.site.model.login.Member;
import com.site.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class MemberService implements UserDetailsService {

	@Autowired
	MemberRepo memberRepo;

	@Override
	public UserDetails loadUserByUsername(String name) {

		List<Member> members= memberRepo.findByName(name);
		if(members == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		return members.get(0);
	}

}
