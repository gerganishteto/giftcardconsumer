/*
 * Copyright 2015, Optimal Payments PLC, 2 Place Alexis Nihon, suite 700, Westmount, Quebec, Canada
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of Optimal Payments PLC
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Optimal
 * Payments.
 */

package com.example.resources;

public enum TransactionStatusResource {
  APPROVED, DECLINED, ERROR;

  /**
   * @return null on null transactionStatusResource
   */
  public static String toString(TransactionStatusResource transactionStatusResource) {
    if (null == transactionStatusResource) {
      return null;
    }
    return transactionStatusResource.toString();
  }

  /**
   * @return null on null transactionStatus
   */
  public static TransactionStatusResource toValue(String transactionStatus) {
    if (null == transactionStatus) {
      return null;
    }
    return TransactionStatusResource.valueOf(transactionStatus);
  }
}