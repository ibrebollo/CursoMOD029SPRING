package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	JwtAuthConverter converter; 
	
//	@Value("${security.db.url}")
//	String url;
//	
//	@Value("${security.db.driver}")
//	String driver;
//	
//	@Value("${security.db.user}")
//	String user;
//	
//	@Value("${security.db.password}")
//	String password;
	
//	@Bean
//	public InMemoryUserDetailsManager  usersDetailsMemory() throws Exception {
//		List<UserDetails> users=List.of(
//				User.withUsername("user1")
//			          //.password("$2a$12$YUq1fO2Vbz.ONbIo./xmBeGCYFr5m4OLNC8H9HFafn4fpcOnUbqda") //si se va usar un mecanismo de encriptación
//					  .password("{noop}user1")    //lo de {noop} se pone para indicar que no está encriptada  
//					  .roles("USER")
//			          .build(),
//			    User.withUsername("admin")
//			          .password("{noop}admin")
//			          .roles("USER", "ADMIN")
//			          .build(),
//			    User.withUsername("user2")
//			          .password("{noop}user2")
//			          .roles("OPERATOR")
//			          .build()
//          );
//		return new InMemoryUserDetailsManager(users);		
//	}
	
	
	@Bean
	public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {
		
		http.csrf(cr->cr.disable())
		.authorizeHttpRequests(aut->
				aut.requestMatchers(HttpMethod.POST,"/alta").hasRole("ADMIN")
				.requestMatchers("/eliminar").hasAnyRole("ADMIN","OPERATOR")
				.requestMatchers("/alumnos").authenticated() // el endpoint de alumnos está filtrado por usuarios autenticados
				.anyRequest().permitAll())
		.oauth2ResourceServer(oauth2ResourceServer->
		oauth2ResourceServer.jwt(jwt->jwt
				.jwtAuthenticationConverter(converter)))
				.sessionManagement(sessionManagement->
					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
		
	}
	
	// PROBAMOS CON BBDD
//	@Bean
//	public JdbcUserDetailsManager usersDetailsJdbc() {
//
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//
//		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//		ds.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
//
//		ds.setUsername("root");
//
//		ds.setPassword("root");
//
//		JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(ds);
//
//		jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
//
//				+ " from users where user=?");
//
//		jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
//
//				+ "from roles where user=?");
//
//		return jdbcDetails;
//
//	}
	
	// Con properties
//	@Bean
//	public JdbcUserDetailsManager usersDetailsJdbc() {
//
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//		
//		ds.setDriverClassName(driver);
//		ds.setUrl(url);
//		ds.setUsername(user);
//		ds.setPassword(password);
//		
//		JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(ds);
//
//		jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
//				+ " from users where user=?");
//
//		jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
//				+ "from roles where user=?");
//
//		return jdbcDetails;
//
//	}
		
 

}
