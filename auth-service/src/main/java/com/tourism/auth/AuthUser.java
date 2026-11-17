package com.tourism.auth;
import jakarta.persistence.*;
@Entity
@Table(name="auth_users")
public class AuthUser {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name;
  @Column(unique=true, nullable=false) private String email;
  @Column(nullable=false) private String passwordHash;
  public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
  public String getEmail(){return email;} public void setEmail(String v){email=v;}
  public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
}
