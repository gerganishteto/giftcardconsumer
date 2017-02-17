package com.example.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {"server.port:0", "eureka.client.enabled:false"})
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner
@DirtiesContext
public class PurchaseConsumerControllerTest {

  @Autowired
  MockMvc mockMvc;
  
  @Autowired
  PurchaseConsumerController purchaseConsumerController;
  
  @Test
  public void should_give_me_a_purchase_when_id_is_valid() throws Exception {
    mockMvc
        .perform(
            get("/v1/purchases/{id}", "valid")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(
            jsonPath("$.id").value("valid"));
  }

  @Test
  public void should_return_notfound_when_id_is_invalid() throws Exception {
    mockMvc
        .perform(
            get("/v1/purchases/{id}", "invalid"))
        .andExpect(status().isNotFound());
  }
 
}
