package com.tourism.payment;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/payments")
public class PaymentController {
  private final PaymentRepository repository;
  public PaymentController(PaymentRepository repository){this.repository=repository;}
  @GetMapping public List<Payment> all(){return repository.findAll();}
  @GetMapping("/{id}") public Payment get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found"));}
  @PostMapping public Payment create(@RequestBody Payment item){
        item.setId(null);
        item.setStatus("SUCCESS");
        item.setTransactionReference("DEMO-" + java.util.UUID.randomUUID());
        return repository.save(item);
      }
  @PutMapping("/{id}") public Payment update(@PathVariable Long id,@RequestBody Payment item){
    if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
    item.setId(id); return repository.save(item);
  }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found"); repository.deleteById(id);}
}
