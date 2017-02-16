package com.example;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class PurchaseNotFoundException extends HttpClientErrorException{

  private static final long serialVersionUID = 7725752566356909421L;
  
  public PurchaseNotFoundException(HttpStatus statusCode) {
    super(statusCode);
  }

  public PurchaseNotFoundException(HttpStatus statusCode, String statusText,
      HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
    super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
  }
}
