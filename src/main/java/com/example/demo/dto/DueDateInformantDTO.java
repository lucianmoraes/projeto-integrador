package com.example.demo.dto;

import com.example.demo.entity.Estoque;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
public class DueDateInformantDTO {
    private Long vendedorId;
    private String email;
    private String nome;
    private String mensagem;

    public static List<DueDateInformantDTO> converte(List<Estoque> estoque){
        List<DueDateInformantDTO> listaDueDateInformant = new ArrayList<>();
        for (Estoque e: estoque) {
            DueDateInformantDTO dueDateInformantDTO = DueDateInformantDTO.builder()
                    .vendedorId(e.getAnuncio().getVendedor().getId())
                    .nome(e.getAnuncio().getVendedor().getNome())
                    .email(e.getAnuncio().getVendedor().getEmail())
                    .build();
            dueDateInformantDTO.setMensagem(DueDateInformantDTO.createMensagem(e));
            listaDueDateInformant.add(dueDateInformantDTO);
        }

        return listaDueDateInformant;
    }

    public static String createMensagem(Estoque dto){
        return dto.getAnuncio().getVendedor().getNome() + ", você possui " + dto.getQuantidadeAtual() + " unidades do produto " + dto.getAnuncio().getProduto().getNome() + " que vencerá no dia " + dto.getDataValidade() + ". Recomendamos realizar uma oferta para que não haja perda de estoque. ";
    }

}
