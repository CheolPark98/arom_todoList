package arom.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Todo1stApplication {

	public static void main(String[] args) {
		SpringApplication.run(Todo1stApplication.class, args);
	}

}
