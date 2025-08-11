package nocast.storeservice.security.config;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.user.service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                .maximumSessions(2))
                .rememberMe((rememberMe) ->
                        rememberMe
                                .rememberMeParameter("auth")
                                .userDetailsService(userDetailsService)
                                .rememberMeCookieName("auth")
                                .rememberMeServices(rememberMeServices())
                                .configure(http))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login", "/register").permitAll()
                                .requestMatchers("/admin**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/product/", true))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect(request.getContextPath() + "/login");
                        }))
                .build();
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices =
                new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
