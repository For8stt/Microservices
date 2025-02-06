package com.example.demo.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private final TrasferService trasferService;

    public BankController(TrasferService trasferService){
        this.trasferService=trasferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO){
        return trasferService.execute(transferDTO);
    }

}
