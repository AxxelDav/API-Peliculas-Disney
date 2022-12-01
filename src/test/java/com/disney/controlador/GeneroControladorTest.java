//package com.disney.controlador;
//
//import com.disney.dto.GeneroDto;
//import com.disney.entidad.Genero;
//import com.disney.servicio.ServicioGenero;
//import com.disney.servicio.ServicioGeneroImplTest;
//import com.disney.servicio.impl.ServicioGeneroImpl;
//import com.disney.util.ConversorEntidadAdto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(GeneroControlador.class)
//public class GeneroControladorTest {
//
//    @MockBean
//    private ServicioGeneroImpl servicioGenero;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    private ConversorEntidadAdto conversor;
//
//    @Test
//    public void testCrearGenero() throws Exception {
//        //given
//        Genero genero = new Genero();
//        genero.setId(1L);
//        genero.setNombre("Aventura");
//        genero.setImagen("Gran Genero");
//
//        GeneroDto generoDto = new GeneroDto();
//        generoDto.setIdGenero(1L);
//        generoDto.setNombre("Aventura");
//        generoDto.setImagen("Gran Genero");
//
//        Mockito.when(servicioGenero.crearGenero(any(Genero.class))).thenReturn(genero);
//        //when
//        ResultActions response = mockMvc.perform(post("/crear/genero")
//                .content(objectMapper.writeValueAsString(genero))
//                .contentType(MediaType.APPLICATION_JSON));
//        //then
//        response.andDo(print())
//                .andExpect(status().isCreated());
////                .andExpect(jsonPath("$.id").value(1L));
////                .andExpect(jsonPath("$.nombre").value("Aventura"))
////                .andExpect(jsonPath("$.imagen", is("Gran Genero")));
//
//    }
//
//
//
//    @Test
//    public void testObtenerGenero() throws Exception {
//        //given
//        Genero genero = new Genero();
//        genero.setId(1L);
//        genero.setNombre("Aventura");
//        genero.setImagen("Gran Genero");
//
//        GeneroDto generoDto = new GeneroDto();
//        generoDto.setIdGenero(1L);
//        generoDto.setNombre("Aventura");
//        generoDto.setImagen("Gran Genero");
//
//        Mockito.when(servicioGenero.obtenerGenero(any(Long.class))).thenReturn(generoDto);
//
//
//        //when
//        ResultActions response = mockMvc.perform(get("/obtener/genero/{id}", 1L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(generoDto)));
//
//        //then
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.imagen", is(generoDto.getImagen())))
//                .andExpect(jsonPath("$.nombre", is(generoDto.getNombre())));
//
//    }
//
//
//
//}
