package com.site.config;

import com.site.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//1

//    @Bean
//    UserDetailsService customUserService() { //2
//        return new CustomUserService();
//    }

    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService); //3    用上面的方法的返回值注入参数
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .headers().frameOptions().sameOrigin()
//                .and().authorizeRequests().anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/", true)
//                .failureUrl("/login?error").permitAll() //bootstrap
//                .and().rememberMe().key("swpuiot").tokenValiditySeconds(14100).rememberMeParameter("remember_me")
//                .and().logout().logoutSuccessUrl("/login?logout").permitAll()
//                .and().csrf().disable(); //6

        http.csrf().disable();
    }
}
