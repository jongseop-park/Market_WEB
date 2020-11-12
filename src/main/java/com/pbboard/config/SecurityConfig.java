package com.pbboard.config;

import com.pbboard.user.service.CustomAuthenticationProvider;
import com.pbboard.user.service.UserLoginFailHandler;
import com.pbboard.user.service.UserLoginSuccessHandler;
import com.pbboard.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

// 시큐리티 구성 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    @Autowired
    private CustomAuthenticationProvider provider;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserLoginSuccessHandler successHandler;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // 리소스 파일 접근 가능(무조건)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**"
                , "/img/**", "/js/**", "/sass/**");
    }

    // http 인증관련 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
               /* .antMatchers("/login").permitAll()
                            // login 전부 허용*/
                .antMatchers("/mypage/**", "/cart/**", "/men/detail/qna_form").hasRole("USER")
                            // 마이페이지, 장바구니 페이지는 사용자만 사용(USER 권한)
                .antMatchers("/**").permitAll()
                            // 나머지 상품목록 확인 가능
                .anyRequest().authenticated()
                            // 이외에는 전부 권한 얻은 사람만 접근
            .and()
                .formLogin()
                    .failureHandler(failureHandler) // 커스텀 클래스 추가
                    //.usernameParameter("username")
                    //.passwordParameter("password")
                    .loginPage("/login") // 로그인시 사용할 경로
                    //.loginProcessingUrl("/")
                    .successHandler(successHandler)
                    //.defaultSuccessUrl("/", true)
            .and()
                .logout()
                    .logoutSuccessUrl("/") // 로그아웃 성공시 이동할 경로
                    .invalidateHttpSession(true) // 로그인했던 정보 삭제
            .and()
                .csrf().disable() //  csrf 보호기능 미사용
        ;
    }

    // 로그인할때 필요한 정보를 가져오는 곳
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 저장된 패스워드와 사용자가 입력한 패스워드 비교
    /*    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    */
          auth.authenticationProvider(provider);
    }

    // 패스워드에 사용할 인코더 빈에 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 실패 핸들러 빈 등록
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new UserLoginFailHandler();
    }

    // 커스텀 인증 Provider 빈 등록
    @Bean
    public CustomAuthenticationProvider authenticationProvider(UserService userService) {
        return new CustomAuthenticationProvider(userService);
    }
}
