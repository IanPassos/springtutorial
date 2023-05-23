package br.unesp.rc.springtutorial;



import br.unesp.rc.springtutorial.service.FisicaService;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringtutorialApplication implements CommandLineRunner{


	@Autowired
	private FisicaService fisicaService;

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringtutorialApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

	}
}
