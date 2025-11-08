package br.senac.rj.api.model;

import jakarta.persistence.*;

@Entity
@Table(name="lingua")
public class Lingua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao;

    public Lingua() {
    }

    public Lingua(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
