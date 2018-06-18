package top.travorzhu.teamanager.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.travorzhu.teamanager.Entity.User.MyUserDetailService;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/factory/**").hasRole("FACTORY")
                .antMatchers("/retail/**").hasRole("RETAIL")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/login_success")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .rememberMe();
    }
}
