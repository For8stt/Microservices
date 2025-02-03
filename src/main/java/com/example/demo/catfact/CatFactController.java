package com.example.demo.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatFactController {
    private final CatFactService catFactService;
    public CatFactController(CatFactService catFactService){
        this.catFactService=catFactService;
    }
    @GetMapping("/catfact")
    public ResponseEntity<CatFactDTO> getFact(@RequestParam(defaultValue = "140") Integer max_length){
        return catFactService.execute(max_length);
    }
}
