package ufps.api_edu.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ufps.api_edu.service.serviceImpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/js/**", "/css/**", "/img/**", "/Confirmacion/**").permitAll()
                                .requestMatchers("/", "/registrarse", "/iniciar_sesion", "/registro").permitAll()
                                .requestMatchers("/unidades/crear_unidad").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/editar_unidad, /unidades/*/eliminar_unidad").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/temas/crear_tema").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/temas/*/editar_tema", "/unidades/*/temas/*/eliminar_tema").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/temas/*/recursos/*/eliminar_recurso").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/temas/*/preguntas/**").hasAnyAuthority("PROFESOR", "SADMIN")
                                .requestMatchers("/unidades/*/temas/*/evaluacion/**").hasAnyAuthority("ESTUDIANTE")

                                .anyRequest().authenticated()) //Ajustar esto por authenticated() cuando se despliegue
                                //.csrf(AbstractHttpConfigurer::disable) //Quitar esto cuanto no se pruebe desde postman
                .formLogin(form -> form
                        .loginPage("/iniciar_sesion")
                        .permitAll())
                .logout(logout -> logout
                        // Configure logout behavior here
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .clearAuthentication(true)   // Clear SecurityContext on logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/cerrar_sesion")) // Custom logout URL
                        .logoutSuccessUrl("/?logout")  // Redirect after successful logout
                );
        return httpSecurity.build();
    }
}
