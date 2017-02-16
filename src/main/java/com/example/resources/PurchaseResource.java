/*
 * Copyright 2014, Optimal Payments PLC, 2 Place Alexis Nihon, suite 700, Westmount, Quebec, Canada
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of Optimal Payments PLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Optimal
 * Payments.
 */

package com.example.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@JsonInclude(Include.NON_NULL)
public class PurchaseResource {
  @JsonProperty
  public String id;

  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  public Date transactionDate;

  @JsonProperty
  public TransactionStatusResource transactionStatus;
  
  
  @NotNull
  @Digits(integer = 9, fraction = 0, message = "integer with maximum 9 digits expected")
  @Min(value = 1, message = "must be greater than 0")
  private BigDecimal amount;

  @Valid
  @NotNull
  private GiftCardResource giftCard;

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public GiftCardResource getGiftCard() {
    return giftCard;
  }

  public void setGiftCard(GiftCardResource giftCard) {
    this.giftCard = giftCard;
  }
}