package com.tourism.destination;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/destinations")
public class DestinationController {
  private final DestinationRepository repository;
  public DestinationController(DestinationRepository repository){this.repository=repository;}
  @GetMapping public List<Destination> all(){return repository.findAll();}
  @GetMapping("/{id}") public Destination get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Destination not found"));}
  @PostMapping public Destination create(@RequestBody Destination item){item.setId(null); return repository.save(item);}
  @PutMapping("/{id}") public Destination update(@PathVariable Long id,@RequestBody Destination item){
    if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Destination not found");
    item.setId(id); return repository.save(item);
  }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Destination not found"); repository.deleteById(id);}
}
