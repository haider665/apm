package com.elk.apm.service_a.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mojib.haider
 * @since 12/23/24
 */
@RestController
@RequestMapping("/serviceA")
public class ServiceAController {

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return new ResponseEntity<>("hello from service A", HttpStatus.OK);
    }
}
