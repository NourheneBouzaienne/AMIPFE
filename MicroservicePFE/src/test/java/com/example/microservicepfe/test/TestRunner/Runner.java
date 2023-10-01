package com.example.microservicepfe.test.TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/resources/featurefiles",
        glue = {"stepdefinitions"}
        ,plugin = {"pretty"}
)
@SpringBootTest(properties = "eureka.client.enabled=false")
public class Runner {


   /* public static void main(String[] args) {

        String[] options = {"--glue", "src/test/java/com/example/microservicepfe/test/stepdefinitions","src/test/java/ressources/featurefiles"};
        byte exitcode;

        exitcode = Main.run(options, Thread.currentThread().getContextClassLoader());

        System.out.println("exitcode = " + exitcode);

    }*/
}