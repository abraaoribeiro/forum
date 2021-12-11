package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
    ) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
        http?.
            csrf()?.disable()?.
            authorizeRequests()?.
            antMatchers("/topicos")?. hasAuthority("ROLE_LEITURA_ESCRITA")?.
            antMatchers(HttpMethod.POST, "/login")?.permitAll()?.
            anyRequest()?.
            authenticated()?.
            and()?.
            addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)?.
            addFilterBefore(JWTAutheticationFilter(jwtUtil= jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)?.
            sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
       auth?.userDetailsService(userDetailsService)?.
               passwordEncoder(bCryptPasswordEncode())
    }

    @Bean
    fun bCryptPasswordEncode(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}