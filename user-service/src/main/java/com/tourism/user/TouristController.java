package com.tourism.user;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/users")
public class TouristController {
  private final TouristRepository repository;
  public TouristController(TouristRepository repository){this.repository=repository;}
  @GetMapping public List<Tourist> all(){return repository.findAll();}
  @GetMapping("/{id}") public Tourist get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tourist not found"));}
  @PostMapping public Tourist create(@RequestBody Tourist item){item.setId(null); return repository.save(item);}
  @PutMapping("/{id}") public Tourist update(@PathVariable Long id,@RequestBody Tourist item){
    if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Tourist not found");
    item.setId(id); return repository.save(item);
  }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Tourist not found"); repository.deleteById(id);}
}
