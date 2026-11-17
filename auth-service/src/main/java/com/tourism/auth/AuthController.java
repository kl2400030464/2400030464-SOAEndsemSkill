package com.tourism.auth;

import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthUserRepository repository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  public AuthController(AuthUserRepository repository) { this.repository = repository; }

  @PostMapping("/register")
  public Map<String,Object> register(@RequestBody RegisterRequest request) {
    if (request.name == null || request.email == null || request.password == null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name, email and password are required");
    if (repository.findByEmail(request.email).isPresent())
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
    AuthUser user = new AuthUser();
    user.setName(request.name); user.setEmail(request.email);
    user.setPasswordHash(encoder.encode(request.password));
    repository.save(user);
    return Map.of("id", user.getId(), "name", user.getName(), "email", user.getEmail(), "message", "Registered successfully");
  }

  @PostMapping("/login")
  public Map<String,Object> login(@RequestBody LoginRequest request) {
    AuthUser user = repository.findByEmail(request.email).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    if (!encoder.matches(request.password, user.getPasswordHash()))
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    return Map.of("token", UUID.randomUUID().toString(), "tokenType", "Demo", "userId", user.getId(),
      "email", user.getEmail(), "message", "Demo login successful; token is not enforced by gateway");
  }
  public static class RegisterRequest { public String name; public String email; public String password; }
  public static class LoginRequest { public String email; public String password; }
}
