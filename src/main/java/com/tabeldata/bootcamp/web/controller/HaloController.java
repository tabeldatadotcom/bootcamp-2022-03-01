package com.tabeldata.bootcamp.web.controller;

import com.tabeldata.bootcamp.web.dao.ExampleDao;
import com.tabeldata.bootcamp.web.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HaloController {

    @Autowired
    private ExampleDao dao;

    @GetMapping("/list")
    public List<Example> list() {
        return dao.list();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Example data = this.dao.findById(id);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException erdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/show")
    public Example showData(
            @RequestParam(name = "msg", required = true) String message,
            @RequestParam(name = "oth", required = false) String other,
            BigDecimal salary,
            Boolean active) {
        Example data = new Example();
        data.setMessage(message);
        data.setOther(other);
        data.setSalary(salary);
        data.setIsActive(active);
        data.setTransactionDate(LocalDate.now());
        data.setTransactionDatetime(LocalDateTime.now());
        return data;
    }

    @PostMapping(value = "/input")
    public Example inputData(@RequestBody @Valid Example data) {
        this.dao.insert(data);
        return data;
    }

}
