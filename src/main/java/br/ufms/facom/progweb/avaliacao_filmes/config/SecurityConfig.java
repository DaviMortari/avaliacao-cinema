package br.ufms.facom.progweb.avaliacao_filmes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilita a configuração de segurança web do Spring Security
public class SecurityConfig {

    @Bean // Este método cria e expõe um bean do tipo PasswordEncoder
    public PasswordEncoder passwordEncoder() {
        // Usando BCrypt, que é o recomendado.
        // Ele lida com a geração de "salt" automaticamente.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())) 
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(HttpMethod.POST, "/api/filmes","api/avaliacoes").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/avaliacoes").permitAll()
                    // .requestMatchers("/**").permitAll() // Permite acesso a TUDO sem autenticação

                    .requestMatchers(
                            "/pages/criarConta",
                            "/pages/home",
                            "/usuarios/salvar", // Seu endpoint de salvar usuário
                            "/pages/entrar",    // Sua página de login
                            "/perform_login",
                            "/pages/filmes",
                            "/pages/series",
                            "/pages/sobre",
                            "/api/filmes/listar",
                            "/api/series/listar",   // Endpoint de processamento de login do Spring Security
                            "/css/**",          
                            "/js/**",           
                            "/img/**",          
                            "/api/filmes",      
                            "/api/avaliacoes/filme/**" 
                    ).permitAll() // Permite acesso a estas rotas/recursos sem autenticação
                    .anyRequest().authenticated() // Todas as outras requisições exigem autenticação
            )
            .formLogin(formLogin -> // Configura o formulário de login padrão do Spring Security
                formLogin
                    .loginPage("/pages/entrar") // Sua página de login personalizada
                    .loginProcessingUrl("/perform_login") // URL para onde o formulário de login envia os dados
                    .defaultSuccessUrl("/pages/home", true) // Para onde redirecionar após login bem-sucedido
                    .failureUrl("/pages/entrar?error=true") // Para onde ir em caso de falha no login
                    .permitAll() // Permite acesso à página de login e ao processamento
            )
            .logout(logout -> // Configura o logout
                logout
                    .logoutUrl("/perform_logout")
                    .logoutSuccessUrl("/pages/entrar?logout") // Para onde ir após logout
                    .invalidateHttpSession(true) // Invalida a sessão HTTP
                    .deleteCookies("JSESSIONID") // Remove o cookie de sessão
                    .permitAll()
            );

        return http.build();
    }
}
