package com.ohgiraffers.semiproject.config;

import com.ohgiraffers.semiproject.common.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableWebSocketMessageBroker
public class SecurityConfig implements WebSocketMessageBrokerConfigurer {

    // 비밀번호 인코더 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스에 대한 요청은 시큐리티 설정이 돌지 않게 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/home/css/**", "/img/**"); // CSS 파일 및 사진 접근 허용
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            // 인증되지 않은 사용자가 접근할 수 있는 URL
            auth.requestMatchers("/home", "/home/no-search", "/home/no-check", "/home/pass-search", "/", "/user/signup").permitAll();

            // 관리자 권한만 접근 가능한 URL
            auth.requestMatchers("/sidemenu/manager", "/sidemenu/employeeRegister", "/sidemenu/employeeManagement", "/sidemenu/approvalBox")
                    .hasAnyAuthority(UserRole.ADMIN.getRole());

            // 사용자 및 관리자 권한이 있는 URL
            auth.requestMatchers("/main", "/sidemenu/schedule", "/sidemenu/messenger", "/sidemenu/mail",
                            "/sidemenu/adoption", "/sidemenu/animals", "/sidemenu/adoptionComplete", "/sidemenu/stock",
                            "/sidemenu/facilities", "/sidemenu/board", "/sidemenu/mypage")
                    .hasAnyAuthority(UserRole.USER.getRole(), UserRole.ADMIN.getRole());

            // 인증된 사용자만 접근 가능한 URL
            auth.requestMatchers("/user/info", "/schedule/checkin", "/schedule/checkout", "/api/board").authenticated();

            // 그 외 요청은 인증된 사용자만 접근 가능
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/home"); // 로그인 페이지 URL
            login.usernameParameter("code"); // 사용자 ID 필드
            login.passwordParameter("pass"); // 사용자 패스워드 필드
            login.defaultSuccessUrl("/main", true); // 로그인 성공 후 이동할 페이지
            login.failureUrl("/home?error=true"); // 로그인 실패 시 이동할 페이지
        }).logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
            logout.deleteCookies("JSESSIONID"); // 로그아웃 시 쿠키 삭제
            logout.invalidateHttpSession(true); // 세션 무효화
            logout.logoutSuccessUrl("/home"); // 로그아웃 성공 시 이동할 페이지
        }).sessionManagement(session -> {
            session.maximumSessions(1); // 세션의 최대 수 제한
            session.invalidSessionUrl("/home"); // 세션 만료 시 이동할 URL
        }).csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화

        return http.build();
    }

    // 메시지 브로커 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // 브로커를 통한 메시지 전달 경로
        config.setApplicationDestinationPrefixes("/app"); // 애플리케이션 목적지 접두사
    }

    // STOMP 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // 웹소켓 엔드포인트
                .setAllowedOriginPatterns("*") // 모든 Origin 허용
                .withSockJS(); // SockJS 사용
    }
}
