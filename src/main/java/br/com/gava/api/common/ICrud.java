package br.com.gava.api.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICrud {

  @GetMapping
  ResponseEntity<?> getAll(
    Pagination pagination,
    @RequestParam(required = false) String search
  );

  @GetMapping("/{id}")
  ResponseEntity<?> getItem(@PathVariable long id);

}
