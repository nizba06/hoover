package com.robotic.hoover.controller;

import com.robotic.hoover.model.HooverRequest;
import com.robotic.hoover.model.HooverResponse;
import com.robotic.hoover.service.HooverService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hoover")
public class HooverController {
    private static final Logger logger = LoggerFactory.getLogger(HooverController.class);

    private final HooverService hooverService;

    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @PostMapping("/navigate")
    public ResponseEntity<HooverResponse> navigate(@RequestBody HooverRequest request) {
        logger.info("Received hoover navigation request");
        HooverResponse response = hooverService.navigate(request);
        return ResponseEntity.ok(response);
    }
}
