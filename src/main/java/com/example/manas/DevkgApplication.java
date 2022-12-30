package com.example.manas;

import com.example.manas.entity.Position;
import com.example.manas.service.PositionService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;
import java.io.IOException;

@SpringBootApplication
public class DevkgApplication {

	public static void main( String[] args ) throws IOException {
		SpringApplication.run(DevkgApplication.class, args);
	}

}
