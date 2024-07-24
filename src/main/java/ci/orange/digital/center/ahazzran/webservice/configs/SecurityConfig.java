package ci.orange.digital.center.ahazzran.webservice.configs;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import ci.orange.digital.center.ahazzran.webservice.filters.JwtRequestFilter;
import ci.orange.digital.center.ahazzran.webservice.services.ApprenantDetailsService;
import ci.orange.digital.center.ahazzran.webservice.services.UtilisateurDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtRequestFilter jwtRequestFilter;
    private UtilisateurDetailsService utilisateurDetailsService;
    private ApprenantDetailsService apprenantDetailsService;

    public SecurityConfig(final JwtRequestFilter jwtRequestFilter, UtilisateurDetailsService utilisateurDetailsService,
                            ApprenantDetailsService apprenantDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.utilisateurDetailsService = utilisateurDetailsService;
        this.apprenantDetailsService = apprenantDetailsService;
    }
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            // Configurez vos fournisseurs d'authentification ici
            auth.userDetailsService(apprenantDetailsService).passwordEncoder(passwordEncoder());
            auth.userDetailsService(utilisateurDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   /* @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and();

          http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage()
                    );
                }
            )
            .and();
        http
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                // .antMatchers("/auth/**").hasRole("USER")
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                // .antMatchers("/api/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Disable CSRF protection
                                                                                            // for simplicity
    }



}


