package com.agenda.dto;

public class ContatoRequestDTO {

    @NotBlank(message = "O nome é obrigatório") 
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O formato do email é inválido")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    // Getters e Setters do Lombok
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}