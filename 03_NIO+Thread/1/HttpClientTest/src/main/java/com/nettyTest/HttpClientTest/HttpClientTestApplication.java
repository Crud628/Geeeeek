package com.nettyTest.HttpClientTest;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpClientTestApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(HttpClientTestApplication.class, args);
        String url = "http://127.0.0.1:8808";
        String text;
		try {
			text = HttpClientHelper.getAsString(url);
			System.out.println("url: " + url + " ; response: \n" + text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
