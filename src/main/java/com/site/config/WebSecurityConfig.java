package com.site.config;

import com.site.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() { //2
        return new MemberService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and().authorizeRequests()
                .antMatchers("/*.html").permitAll()
//                .antMatchers("/excelout/**").hasAnyRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/").defaultSuccessUrl("/signDetail", true)
                .failureUrl("/").permitAll()
                .and().rememberMe().key("swpuiot").tokenValiditySeconds(86400).rememberMeParameter("swpuiot")
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().disable(); //6
    }
}
