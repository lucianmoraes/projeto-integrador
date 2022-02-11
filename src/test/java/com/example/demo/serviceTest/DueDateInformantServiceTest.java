package com.example.demo.serviceTest;

import com.example.demo.dto.DueDateInformantDTO;
import com.example.demo.entity.Estoque;
import com.example.demo.repository.DueDateInformantRepository;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.DueDateInformantService;
import com.example.demo.service.EstoqueService;
import com.example.demo.utils.MockEstoque;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DueDateInformantServiceTest {
    private MockEstoque mockEstoque;
    private DueDateInformantService dueDateInformantService;

    @BeforeEach
    public void init() {
        mockEstoque = new MockEstoque();
        long numeroDias = 30L;
        LocalDate dataValidade = LocalDate.now().plusDays(numeroDias);
        DueDateInformantRepository dueDateInformantRepository = Mockito.mock(DueDateInformantRepository.class);
        this.mockEstoque.setNumeroDias(numeroDias);
        Mockito.when(dueDateInformantRepository.getNotificationInfosByDueDate(dataValidade)).thenReturn(this.mockEstoque.getEstoqueByDataValidade(dataValidade));
        this.dueDateInformantService = new DueDateInformantService(dueDateInformantRepository);
    }

    @Test
    public void deveVerificarDueDateInformantPorDataValidade(){
        long numeroDias = 30L;
        LocalDate dataValidade = LocalDate.now().plusDays(numeroDias);
        List<Estoque> listaEstoqueMock = new ArrayList<>();
        listaEstoqueMock.add(this.mockEstoque.getListaEstoque().get(0));
        List<DueDateInformantDTO> listaMock =  DueDateInformantDTO.converte(listaEstoqueMock);
        List<DueDateInformantDTO> listaService = this.dueDateInformantService.getNotificationInfosByDueDate(numeroDias);
        assert(listaMock.equals(listaService));
    }
}
