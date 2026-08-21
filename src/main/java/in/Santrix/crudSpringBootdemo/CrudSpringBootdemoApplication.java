package in.Santrix.crudSpringBootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication
public class CrudSpringBootdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudSpringBootdemoApplication.class, args);
		System.out.println("container is up");
	}


}
