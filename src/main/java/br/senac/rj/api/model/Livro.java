package br.senac.rj.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter
    private Long codigo;

//    @Getter
//    @Setter
    private String titulo;

//    @Getter
//    @Setter
    private Double preco;

    @ManyToOne
    private Lingua lingua;

    public Livro(){

    }

    public Livro(String titulo, Double preco) {
        this.titulo = titulo;
        this.preco = preco;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Lingua getLingua() {
        return lingua;
    }

    public void setLingua(Lingua lingua) {
        this.lingua = lingua;
    }
}
