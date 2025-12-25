package com.agenda.util;

import com.agenda.dto.ContatoRequestDTO;
import com.agenda.dto.ContatoResponseDTO;
import com.agenda.model.contato;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContatoMapper {

    public contato toEntity(ContatoRequestDTO dto) {
        contato contato = new contato();
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
        return contato;
    }

    public ContatoResponseDTO toDTO(contato contato) {
        return new ContatoResponseDTO(contato);
    }

    public List<ContatoResponseDTO> toDTOList(List<contato> contatos) {
        return contatos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void updateEntityFromDTO(ContatoRequestDTO dto, contato contato) {
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());
    }

}