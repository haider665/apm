package com.elk.apm.service_b.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mojib.haider
 * @since 12/24/24
 */
@RestController
@RequestMapping("serviceB")
public class TempController {

    @GetMapping("/getMessage")
    public ResponseEntity<?> getMessage() {
        return new ResponseEntity<>("Greetings from service B", HttpStatus.OK);
    }
}
