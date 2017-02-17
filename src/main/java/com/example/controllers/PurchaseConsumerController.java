package com.example.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.PurchaseNotFoundException;
import com.example.resources.PurchaseResource;



@RestController
@RequestMapping("v1")
@RibbonClient(name = "oneplatform-sample-giftcard")
@Configuration
public class PurchaseConsumerController {

  static final String GIFTCARD_URL = "http://oneplatform-sample-giftcard/v1/purchases/{id}";

  public static final String APPLICATION_JSON_UTF8_VALUE = "application/json; charset=utf-8";
  
  @LoadBalanced
  @Bean
  RestTemplate myRestTemplate(){
    return new RestTemplate();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/purchases/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PurchaseResource> lookup(
      @PathVariable("id") final String id) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    HttpEntity<String> entity = new HttpEntity<>("", headers);
    
    try {
      PurchaseResource response = myRestTemplate()
          .exchange(GIFTCARD_URL, HttpMethod.GET, entity,
              new ParameterizedTypeReference<PurchaseResource>() { }, id).getBody(); 

      if (response == null) {
        return new ResponseEntity<PurchaseResource>(response,
            HttpStatus.NOT_FOUND);
      }

      response.id = "valid";
      return new ResponseEntity<PurchaseResource>(response, HttpStatus.OK); 

    } catch (RestClientException ex) {
      if (ex.getMostSpecificCause() instanceof HttpClientErrorException) {
        HttpClientErrorException http = (HttpClientErrorException) ex.getMostSpecificCause();
        
        if (http.getStatusCode().equals(HttpStatus.NOT_FOUND) && http.getStatusCode().is4xxClientError()) {
          
          String errorMessage = "purchase id " + id.toString() + " does not exist";
          
          PurchaseNotFoundException exception = new PurchaseNotFoundException(http.getStatusCode(), 
              errorMessage, http.getResponseHeaders(), http.getResponseBodyAsByteArray(), 
              StandardCharsets.UTF_8);
          
          throw exception;
        }
      }
      throw new RuntimeException(ex);
    }
  }

}
