package info.markott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CinchringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinchringApplication.class, args);
	}

	@RequestMapping("/arrr")
	public String arrr() {
		return "Arrrrrrr!!!!";
	}


}
