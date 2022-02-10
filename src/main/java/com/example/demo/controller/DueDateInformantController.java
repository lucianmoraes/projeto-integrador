package com.example.demo.controller;

import com.example.demo.dto.DueDateInformantDTO;
import com.example.demo.service.DueDateInformantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date/informant")
public class DueDateInformantController {

    @Autowired
    private DueDateInformantService dueDateInformantService;

    @GetMapping("/{numeroDias}")
    public ResponseEntity<List<DueDateInformantDTO>> getEstoque(@PathVariable Long numeroDias) {
        List<DueDateInformantDTO> ordemEntrada = dueDateInformantService.getNotificationInfosByDueDate(numeroDias);
        return ResponseEntity.status(HttpStatus.OK).body(ordemEntrada);
    }
}
