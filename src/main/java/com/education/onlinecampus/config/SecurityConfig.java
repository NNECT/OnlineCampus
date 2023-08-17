package com.education.onlinecampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() //인증 인가가 필요한 URL 지정
                .antMatchers("/").authenticated() //해당 url에 접근하기 위해서는 authenticated(인증,인가)가 필요함
                .anyRequest().permitAll() //anyRequest: 그외의 모든 URL , permitAll 인증,인가가 필요없이 통과
                .and()
                .formLogin()//Form Login 방식 적용
                .loginPage("/Member_login") //로그인 페이지
                .defaultSuccessUrl("/")// 로그인 성공시 페이지
                .failureUrl("/Member_login_fail")//로그인 실패시 페이지
                .and()
                .logout()//로그아웃
                .logoutUrl("/Member_logout")//로그아웃
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}