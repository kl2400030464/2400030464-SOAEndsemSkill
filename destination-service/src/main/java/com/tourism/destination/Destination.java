package com.tourism.destination;
  import jakarta.persistence.*;
  import java.time.*;
  @Entity
  public class Destination {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name;
private String country;
private String description;
private Double pricePerPerson;
private Boolean available;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
public String getCountry(){return country;} public void setCountry(String v){country=v;}
public String getDescription(){return description;} public void setDescription(String v){description=v;}
public Double getPricePerPerson(){return pricePerPerson;} public void setPricePerPerson(Double v){pricePerPerson=v;}
public Boolean getAvailable(){return available;} public void setAvailable(Boolean v){available=v;}
  }
