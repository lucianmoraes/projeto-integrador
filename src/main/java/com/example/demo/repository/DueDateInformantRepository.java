package com.example.demo.repository;

import com.example.demo.dto.DueDateInformantDTO;
import com.example.demo.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DueDateInformantRepository extends JpaRepository<Estoque, Long> {

    @Query(value = " SELECT e.id, e.data_producao, e.data_validade, e.quantidade_atual, e.quantidade_inicial, e.temperatura_atual, e.anuncio_id, e.ordem_entrada_id " +
            " FROM estoque e " +
            " INNER JOIN ordem_entrada oe ON e.ordem_entrada_id = oe.id " +
            " INNER JOIN setor s ON oe.setor_id = s.id " +
            " INNER JOIN representante r ON r.armazem_id = s.armazem_id " +
            " WHERE e.data_validade <= :dataValidade AND e.data_validade > Now()", nativeQuery = true)
    List<Estoque> getNotificationInfosByDueDate(LocalDate dataValidade);

}
