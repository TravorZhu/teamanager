package top.travorzhu.teamanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.travorzhu.teamanager.storage.StorageService;

@SpringBootApplication
public class TeamanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamanagerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
		};
	}
}
