package com.example.ejercicioPractico.rest;

import com.example.ejercicioPractico.servicio.ClienteServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ApiCliente.class)
@ExtendWith(SpringExtension.class)
class ApiClienteTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteServicio clienteServicio;

    @Test
    public void testDeleteApi() throws Exception {
        String id = "1234";
        Mockito.when(clienteServicio.deleteCliente(id)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/"+id)).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> assertEquals("true", result.getResponse().getContentAsString()));
    }
}