package com.idquantique.quantis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuantisApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantisApplication.class, args);
	}

	Quantis quantis = new Quantis(Quantis.QuantisDeviceType.QUANTIS_DEVICE_PCI, 0);


	@GetMapping
	public Integer hello() throws QuantisException{
		return quantis.ReadInt();
	}

}
