package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application entry point for Poseidon Skeleton.
 * 
 * This class initializes and starts the Spring Boot application, enabling
 * component scanning, auto-configuration, and configuration property support.
 */
@SpringBootApplication
public class PoseidenSkeletonApplication {

    /**
     * Main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PoseidenSkeletonApplication.class, args);
    }

}
