package com.example.demo.security;


import com.example.demo.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails admin= User
//                .withUsername("admin")
//                .authorities("BASIC","SPECIAL")
//                .roles("superuser")
//                .password(passwordEncoder.encode("1"))
//                .build();
//        UserDetails user=User
//                .withUsername("user")
//                .roles("basicuser")
//                .authorities("BASIC")
//                .password(passwordEncoder.encode("2"))
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize->{
                    authorize.anyRequest().permitAll();
//                    authorize.requestMatchers("/login").permitAll();
//                    authorize.requestMatchers("/createnewuser").permitAll();
//
//                    authorize.anyRequest().authenticated();




//                    authorize.requestMatchers("/open").permitAll();
//                    authorize.requestMatchers("/closed").authenticated();
//                    authorize.requestMatchers(HttpMethod.POST,"/product").authenticated();
//
//                    authorize.requestMatchers(HttpMethod.GET,"/special").hasAuthority("SPECIAL");
//                    authorize.requestMatchers(HttpMethod.GET,"/basic").hasAnyAuthority("SPESIAL","BASIC");

                })
//                .httpBasic(Customizer.withDefaults())
//                .addFilterBefore(
//                        new BasicAuthenticationFilter(authenticationManager(httpSecurity)),
//                        UsernamePasswordAuthenticationFilter.class
//                )
                .addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public JwtAuthenticationFilter authenticationFilter(){
        return new JwtAuthenticationFilter();
    }

}
