//package com.disney.controlador;
//
//
//import com.disney.dto.DetallePeliculaDto;
//import com.disney.entidad.Pelicula;
//import com.disney.servicio.impl.ServicioGeneroImpl;
//import com.disney.servicio.impl.ServicioPeliculaImpl;
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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.CoreMatchers.is;
//
//import java.util.Optional;
//
//
//@WebMvcTest(PeliculaControlador.class)
//public class PeliculaControladorTest {
//
//    @MockBean
//    private ServicioPeliculaImpl servicioPelicula;
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
//
//    @Test
//    public void testDetallePelicula() throws Exception {
//        //given
//        Long peliculaId = 1L;
//        Pelicula pelicula = new Pelicula();
//        pelicula.setTitulo("Dragon");
//        pelicula.setImagen("Terror");
//        pelicula.setCalificacion(9);
//
//        DetallePeliculaDto peliculaDto = new DetallePeliculaDto();
//        peliculaDto.setTitulo("Dragon");
//        peliculaDto.setImagen("Terror");
//        peliculaDto.setCalificacion(9);
//
//        Mockito.when(servicioPelicula.detallePelicula(peliculaId)).thenReturn(pelicula);
//
//        //when
//        ResultActions response = mockMvc.perform(get("/detalle/pelicula/{id}", peliculaId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(pelicula)));
//
//        //then
//        response.andExpect(status().isOk())
//                .andDo(print());
//                .andExpect(jsonPath("$.imagen", is(pelicula.getImagen()))
//                .andExpect(jsonPath("$.calificacion", is(pelicula.getCalificacion()))
//                .andExpect(jsonPath("$.titulo", is(pelicula.getTitulo())));



//    }

//    @Test
//    void testDetallePelicula() {
//        Pelicula pelicula = new Pelicula();
//        pelicula.setTitulo("Dragon");
//        pelicula.setImagen("Terror");
//
//        Mockito.when(servicioPelicula.detallePelicula(1L)).thenReturn(pelicula);
//
//
//        mockMvc.perform(get("/detalle/pelicula/1")
//
//
//
//        mockMvc.perform(post("/detalle/pelicula/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.titulo").value("Dragon"))
//                .andExpect(jsonPath("$.imagen").value("Terror"));
//
//        Mockito.verify(servicioPelicula.detallePelicula(1L));
//    }
//}
