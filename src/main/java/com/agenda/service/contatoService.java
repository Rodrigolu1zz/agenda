package com.agenda.service;

import com.agenda.model.contato;
import com.agenda.repository.contatoRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class contatoService {
    private final contatoRepository repository;

    public contatoService(contatoRepository repository) {
        this.repository = repository;
    }

    public List<contato> listarTodos() {
        return repository.findAll();
    }
    public contato salvar(contato contato) {
        // Regra para o nome ser obrigatório
        if (contato.getNome() == null || contato.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do contato não pode ser vazio.");
        }
        // Regra para não permitir dois contatos com o mesmo nome (Pode ser desabilitado)            
        if (contato.getId() == null && !repository.findByNomeContainingIgnoreCase(contato.getNome()).isEmpty()) {
            throw new IllegalArgumentException("Já existe um contato cadastrado com este nome.");
        }
        return repository.save(contato);
    }

    public List<contato> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public contato buscarPorId(Long id) {
        // .orElseThrow() -> Se achar, retorna. Se não achar, lança o erro.
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado com o ID: " + id));
    }
    public void deletar(Long id) {
        repository.deleteById(id);
    }
    // --- SALVAR VÁRIOS (LOTE) --- Esse método novo salva vários contatos de uma vez, com uma unica requisição
    public List<contato> salvarTodos(List<contato> contatos) {
        return contatos.stream()
                .map(this::salvar) 
                .toList(); 
    }
}
