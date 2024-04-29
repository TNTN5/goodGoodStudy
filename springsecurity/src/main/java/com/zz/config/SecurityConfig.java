package com.zz.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/level1/**").hasRole("vip1")
                                .antMatchers("/level2/**").hasRole("vip2")
                                .antMatchers("/level3/**").hasRole("vip3");
//登录，定制登录页面
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");

        http.rememberMe().rememberMeParameter("remember");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3").and()
                .withUser("user2").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2").and()
                .withUser("user3").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");

    }
}
