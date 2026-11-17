package com.tourism.notification;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/notifications")
public class NotificationController {
  private final NotificationRepository repository;
  public NotificationController(NotificationRepository repository){this.repository=repository;}
  @GetMapping public List<Notification> all(){return repository.findAll();}
  @GetMapping("/{id}") public Notification get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Notification not found"));}
  @PostMapping public Notification create(@RequestBody Notification item){
        item.setId(null);
        item.setStatus("QUEUED");
        return repository.save(item);
      }
  @PutMapping("/{id}") public Notification update(@PathVariable Long id,@RequestBody Notification item){
    if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Notification not found");
    item.setId(id); return repository.save(item);
  }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Notification not found"); repository.deleteById(id);}
}
