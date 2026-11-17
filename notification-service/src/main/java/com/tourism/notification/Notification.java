package com.tourism.notification;
  import jakarta.persistence.*;
  import java.time.*;
  @Entity
  public class Notification {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private Long userId;
private String message;
private String channel;
private String status;
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
public String getMessage(){return message;} public void setMessage(String v){message=v;}
public String getChannel(){return channel;} public void setChannel(String v){channel=v;}
public String getStatus(){return status;} public void setStatus(String v){status=v;}
  }
