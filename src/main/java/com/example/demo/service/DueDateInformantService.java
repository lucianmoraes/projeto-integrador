package com.example.demo.service;

import com.example.demo.dto.DueDateInformantDTO;
import com.example.demo.entity.Estoque;
import com.example.demo.repository.DueDateInformantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DueDateInformantService {

    @Autowired
    DueDateInformantRepository dueDateInformantRepository;

    public List<DueDateInformantDTO> getNotificationInfosByDueDate(long numeroDias){
        LocalDate dataValidade = LocalDate.now().plusDays(numeroDias);
        List<Estoque> listaEstoque = dueDateInformantRepository.getNotificationInfosByDueDate(dataValidade);
        return DueDateInformantDTO.converte(listaEstoque);
    }

}
