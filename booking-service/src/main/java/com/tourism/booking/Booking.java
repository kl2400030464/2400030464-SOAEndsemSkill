package com.tourism.booking;
  import jakarta.persistence.*;
  import java.time.*;
  @Entity
  public class Booking {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long userId;
private Long destinationId;
private LocalDate travelDate;
private Integer numberOfPeople;
private Double totalAmount;
private String status;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
public Long getDestinationId(){return destinationId;} public void setDestinationId(Long v){destinationId=v;}
public LocalDate getTravelDate(){return travelDate;} public void setTravelDate(LocalDate v){travelDate=v;}
public Integer getNumberOfPeople(){return numberOfPeople;} public void setNumberOfPeople(Integer v){numberOfPeople=v;}
public Double getTotalAmount(){return totalAmount;} public void setTotalAmount(Double v){totalAmount=v;}
public String getStatus(){return status;} public void setStatus(String v){status=v;}
  }
