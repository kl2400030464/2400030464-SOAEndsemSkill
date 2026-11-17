package com.tourism.user;
  import jakarta.persistence.*;
  import java.time.*;
  @Entity
  public class Tourist {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name;
@Column(unique=true) private String email;
private String phone;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
public String getEmail(){return email;} public void setEmail(String v){email=v;}
public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
  }
