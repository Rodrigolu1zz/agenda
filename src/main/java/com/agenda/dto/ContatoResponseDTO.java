package com.agenda.dto;

import com.agenda.model.contato;

public class ContatoResponseDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public ContatoResponseDTO() {}

    public ContatoResponseDTO(contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.email = contato.getEmail();
        this.telefone = contato.getTelefone();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}