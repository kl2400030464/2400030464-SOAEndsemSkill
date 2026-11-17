package com.tourism.booking;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/bookings")
public class BookingController {
  private final BookingRepository repository;
  public BookingController(BookingRepository repository){this.repository=repository;}
  @GetMapping public List<Booking> all(){return repository.findAll();}
  @GetMapping("/{id}") public Booking get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found"));}
  @PostMapping public Booking create(@RequestBody Booking item){item.setId(null); return repository.save(item);}
  @PutMapping("/{id}") public Booking update(@PathVariable Long id,@RequestBody Booking item){
    if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found");
    item.setId(id); return repository.save(item);
  }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found"); repository.deleteById(id);}
}
