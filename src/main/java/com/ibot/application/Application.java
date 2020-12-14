/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application;
import com.ibot.module.deposit.entities.DepositCard;
import com.ibot.module.deposit.repository.DepositRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * @author Allen
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {DepositCard.class})  // scan JPA entities
@EnableJpaRepositories({"com.ibot.module.deposit.repository"})
//@EnableJpaRepositories(repositoryFactoryBeanClass=DepositRepository.class)
@ComponentScan({"com.ibot.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("ok");

    }

     
}
