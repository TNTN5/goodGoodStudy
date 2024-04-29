# Spring Security

WebSecurityConfigurerAdapter:自定义Security策略

AuthenticationManagerBuilder：自定义认证策略

@EnableWebSecurity：开启Web Security模式



1、导入jar包

```xml
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2、写配置类

```java
//开启security
@EnableWebSecurity			
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/level1/**").hasRole("vip1")
                                .antMatchers("/level2/**").hasRole("vip2")
                                .antMatchers("/level3/**").hasRole("vip3");

//登录，定制登录页面
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");
//		关闭csrf功能
        http.csrf().disable();
        //注销，注销成功后进入页面
        http.logout().logoutSuccessUrl("/");
		//记住我
        http.rememberMe().rememberMeParameter("remember");
    }

    
    //用户认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        //inMemoryAuthentication()从内存中   jdbcAuthentication().dataSource("")从数据库中
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3").and()
                .withUser("user2").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2").and()
                .withUser("user3").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");

    }
}

```

3、页面

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5"
      >
```

```html
<!--是否有登录-->
<div sec:authorize="!isAuthenticated()"></div>
```

```html
<!--是否有权限-->
<div class="column" sec:authorize="hasRole('vip1')">
```

