package com.example.springsOfAlmawalehPOS;

import com.example.springsOfAlmawalehPOS.property.FileStorageProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SpringsOfAlmawalehPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsOfAlmawalehPosApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
