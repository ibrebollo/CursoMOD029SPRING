package init;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public InMemoryUserDetailsManager  usersDetailsMemory() throws Exception {
		List<UserDetails> users=List.of(
				User.withUsername("user1")
			          //.password("$2a$12$YUq1fO2Vbz.ONbIo./xmBeGCYFr5m4OLNC8H9HFafn4fpcOnUbqda") //si se va usar un mecanismo de encriptación
					  .password("{noop}user1")    //lo de {noop} se pone para indicar que no está encriptada  
					  .roles("USER")
			          .build(),
			    User.withUsername("admin")
			          .password("{noop}admin")
			          .roles("USER", "ADMIN")
			          .build(),
			    User.withUsername("user2")
			          .password("{noop}user2")
			          .roles("OPERATOR")
			          .build()
          );
		return new InMemoryUserDetailsManager(users);		
	}
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {
		
		http.csrf(cr->cr.disable())
		.authorizeHttpRequests(aut->
				aut.requestMatchers(HttpMethod.POST,"/alta").hasRole("ADMIN")
				.requestMatchers("/eliminar").hasAnyRole("ADMIN","OPERATOR")
				.requestMatchers("/alumnos").authenticated()
				.anyRequest().permitAll())
		.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}

}
