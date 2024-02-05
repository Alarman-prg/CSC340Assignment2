package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
                
                Scanner ScannerObj = new Scanner(System.in);
                System.out.print("\n\nEnter a search term: ");
                String word = ScannerObj.nextLine();
                
                getMatches(word);
                System.exit(0);
	}
        
            public static void getMatches(String word) {
        try {
            String url = "https://collectionapi.metmuseum.org/public/collection/v1/search?q=" + word;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String result = root.findValue("total").asText();

            System.out.println("Returns the total amount of matches to the word " + word +
                    "\n in the Metropolitan Museum of Art database: " + result);
            //System.out.println("Amount: " + result);

        } catch (JsonProcessingException ex) {
            System.out.println("error in getMatches");

        }
    }

}
