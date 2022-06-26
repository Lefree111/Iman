package com.company.controller;

import com.company.dto.JsonDTO;
import com.company.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/json")
public class JsonController {

    private final JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PutMapping("/updateJson/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody JsonDTO dto) {
        return ResponseEntity.ok(jsonService.update(id, dto));
    }

    @GetMapping("/readAndWrite")
    public ResponseEntity<?> json(){
        return ResponseEntity.ok(jsonService.readAndWrite());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JsonDTO dto) {
        jsonService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allElemtn")
    public List<JsonDTO> getAll() {
        return jsonService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(jsonService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJson(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(jsonService.delete(id));
    }
}