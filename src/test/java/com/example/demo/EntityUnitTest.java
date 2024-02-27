package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

 

    /** TODO
     * Implement tests for each Entity class: Doctor, Patient, Room and Appointment.
     * Make sure you are as exhaustive as possible. Coverage is checked ;)
     */
	   @Test
    //Testeamos si inserta bien un doctor
    void doctorTest(){

        d1 = new Doctor("Smith","Cover",50,"smithcover@gmail.com");

        entityManager.persistAndFlush(d1);
    }
    @Test
        //Comprobamos si inserta bien un paciente
    void patientTest(){

        p1 = new Patient("Pepe", "Sanchis",20, "pepesanchis@gmail.com");

        entityManager.persistAndFlush(p1);

    }
    @Test
        //Miramos si inserta bien una sala
    void roomTest(){

        r1 = new Room("Cardiology");

        entityManager.persistAndFlush(r1);


    }
    @Test
        //Testamos si inserta ya un cita completa e incluso 3
    void appointmentTest(){
        d1 = new Doctor("Smith","Cover",50,"smithcover@gmail.com");
        r1 = new Room("Cardiology");
        p1 = new Patient("Pepe", "Sanchis",20, "pepesanchis@gmail.com");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        LocalDateTime startsAt= LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
        a1 = new Appointment(p1,d1,r1,startsAt,finishesAt);
        a2 = new Appointment(p1,d1,r1,startsAt,finishesAt);
        a3 = new Appointment(p1,d1,r1,startsAt,finishesAt);

        entityManager.persistAndFlush(a1);
        entityManager.persistAndFlush(a2);
        entityManager.persistAndFlush(a3);
    }

}
