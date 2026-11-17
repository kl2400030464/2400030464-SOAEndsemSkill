package com.tourism.payment;
  import jakarta.persistence.*;
  import java.time.*;
  @Entity
  public class Payment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long bookingId;
private Double amount;
private String method;
private String status;
private String transactionReference;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getBookingId(){return bookingId;} public void setBookingId(Long v){bookingId=v;}
public Double getAmount(){return amount;} public void setAmount(Double v){amount=v;}
public String getMethod(){return method;} public void setMethod(String v){method=v;}
public String getStatus(){return status;} public void setStatus(String v){status=v;}
public String getTransactionReference(){return transactionReference;} public void setTransactionReference(String v){transactionReference=v;}
  }
