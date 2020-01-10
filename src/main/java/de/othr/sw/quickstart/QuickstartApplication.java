package de.othr.sw.quickstart;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickstartApplication {

	public static void main(String[] args) {
		//only this needed to run
		SpringApplication.run(QuickstartApplication.class, args);
		//create Bank customer and account for bank
	}
	/*@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Hello World from Application Runner");

	}*/
}
