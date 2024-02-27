
package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.time.format.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;



/** TODO
 * Implement all the unit test in its corresponding class.
 * Make sure to be as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest{

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
        //Miramos si salta el status de not found al buscar un id no existente de doctores
    void shouldNotGetDoctor() throws Exception{
        int id = 5;
        mockMvc.perform(get("/api/doctors/"+ id))
                .andExpect(status().isNotFound());
    }
    @Test
        //Comprobamos si salta la excepecion de no content si devuelve un getAll vacio
    void shouldGetEmptyDoctor() throws Exception {
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isNoContent());
    }

    @Test
        //Miramos si salta la excepcion isCreated al crear bien un doctor
    void shouldCreateDoctor() throws Exception {

        Doctor doctor = new Doctor("Jose","Fernandez",39,"josefernandez@gmail.com");
        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isCreated());
    }


    @Test
        //Testeamos si salta un ok al borrar todos los doctores
    void shouldDeleteAllDoctors() throws Exception{
        mockMvc.perform(delete("/api/doctors"))
                .andExpect(status().isOk());

    }
    @Test
        //Comprobamos si salta el not found al intentar borrar un doctor no existente
    void shouldNotDeleteDoctor() throws Exception {
        long id = 23;
        mockMvc.perform(delete("/api/doctors/" + id))
                .andExpect(status().isNotFound());
    }

}


@WebMvcTest(PatientController.class)
class PatientControllerUnitTest{

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
        //Miramos si salta el status de not found al buscar un id no existente de pacientes
    void shouldNotGetPatient() throws Exception{
        int id = 5;
        mockMvc.perform(get("/api/patients/"+ id))
                .andExpect(status().isNotFound());
    }

    @Test
        //Comprobamos si salta la excepecion de no content si devuelve un getAll vacio
    void shouldGetEmptyPatient() throws Exception {
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isNoContent());
    }

    @Test
        //Miramos si salta la excepcion isCreated al crear bien un doctor
    void shouldCreatePatient() throws Exception {

        Patient patient = new Patient("Manuel","Perez",20,"manuelperez@gmail.com");
        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isCreated());
    }

    @Test
        //Testeamos si salta un ok al borrar todos los pacientes
    void shouldNotDeletePatient() throws Exception {
        long id = 50;
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
        //Comprobamos si salta el not found al intentar borrar un paciente no existente
    void shouldDeleteAllPatients() throws Exception{
        mockMvc.perform(delete("/api/patients"))
                .andExpect(status().isOk());

    }



}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest{

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
        //Miramos si salta el status de not found al buscar un id no existente de sala
    void shouldNotGetRoom() throws Exception{
        int id = 5;
        mockMvc.perform(get("/api/rooms/"+ id))
                .andExpect(status().isNotFound());
    }

    @Test
        //Comprobamos si salta la excepecion de no content si devuelve un getAll vacio
    void shouldGetEmptyRooms() throws Exception {
        mockMvc.perform(get("/api/rooms"))
                .andExpect(status().isNoContent());
    }

    @Test
        //Miramos si salta la excepcion isCreated al crear bien un sala
    void shouldCreateRoom() throws Exception {

        Room room = new Room("Dermatologia");
        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated());
    }

    @Test
        //Testeamos si salta un ok al borrar todos los salas
    void shouldNotDeleteRoom() throws Exception {
        long id = 2;
        mockMvc.perform(delete("/api/rooms/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
        //Comprobamos si salta el not found al intentar borrar un sala no existente
    void shouldDeleteAllRooms() throws Exception{
        mockMvc.perform(delete("/api/rooms"))
                .andExpect(status().isOk());

    }
}
