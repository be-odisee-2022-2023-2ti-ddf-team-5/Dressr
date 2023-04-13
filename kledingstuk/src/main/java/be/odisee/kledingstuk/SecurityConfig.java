package be.odisee.kledingstuk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

    @Autowired
    DataSource datasource;

    @Bean
    public UserDetailsManager authenticateUsers() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);

        users.setUsersByUsernameQuery("select emailadress, passwoord, true as enabled from personen where emailadress=?");
        users.setAuthoritiesByUsernameQuery("select personen.emailadress, rollen.type as authority from personen,rollen"+
                " where personen.id=rollen.persoon_id and emailadress=?");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login*").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/index*").permitAll()
                        .requestMatchers("/**").authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index.html")
                )
                .exceptionHandling().accessDeniedPage("/403.html");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
