package com.agenda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.agenda.model.contato;
import com.agenda.service.contatoService;
import com.agenda.dto.ContatoRequestDTO;
import com.agenda.dto.ContatoResponseDTO;
import com.agenda.util.ContatoMapper;

@RestController
@RequestMapping("/contatos")
public class contatoController {

    private final contatoService service;
    private final ContatoMapper mapper; 

    public contatoController(contatoService service, ContatoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // --- 1. LISTAR TODOS (GET) ---
    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> listarTodos() {
        List<contato> contatos = service.listarTodos();
        List<ContatoResponseDTO> dtos = mapper.toDTOList(contatos);
        
        return ResponseEntity.ok(dtos);
    }

    // --- 2. SALVAR (POST) ---
    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody ContatoRequestDTO request) {
        try {
            contato contatoParaSalvar = mapper.toEntity(request);
            
            contato contatoSalvo = service.salvar(contatoParaSalvar);
            
            ContatoResponseDTO response = mapper.toDTO(contatoSalvo);
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- 3. SALVAR VÁRIOS (POST /contatos/lote) ---
    @PostMapping("/lote")
    public ResponseEntity<Object> salvarVarios(@RequestBody List<ContatoRequestDTO> requests) {
        try {
            List<contato> entidades = requests.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());

            List<contato> salvos = service.salvarTodos(entidades);

            List<ContatoResponseDTO> response = mapper.toDTOList(salvos);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao importar dados: " + e.getMessage());
        }
    }

    // --- 4. ATUALIZAR (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody ContatoRequestDTO request) {
        try {
            contato contatoExistente = service.buscarPorId(id);
            
            mapper.updateEntityFromDTO(request, contatoExistente);
            
            contato contatoAtualizado = service.salvar(contatoExistente);
            
            return ResponseEntity.ok(mapper.toDTO(contatoAtualizado));
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- 5. BUSCAR POR NOME (GET) ---
    @GetMapping("/buscar")
    public ResponseEntity<List<ContatoResponseDTO>> buscarPorNome(@RequestParam("nome") String nome) {

        List<contato> contatos = service.buscarPorNome(nome);

        return ResponseEntity.ok(mapper.toDTOList(contatos));
    }

    // --- 6. DELETAR (DELETE) ---
    @DeleteMapping("/{id}")// Recebe o ID do contato a ser deletado
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}