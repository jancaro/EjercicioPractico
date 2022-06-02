package com.example.ejercicioPractico.rest;

import com.example.ejercicioPractico.services.AccountService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(ApiAccount.class)
@ExtendWith(SpringExtension.class)
public class ApiAccountTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    public void testDeleteApi() throws Exception {
        String id = "1234";
        Mockito.when(accountService.deleteAccount(id)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/"+id)).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> assertEquals("true", result.getResponse().getContentAsString()));
    }
}
