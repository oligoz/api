package br.senac.rj.api.validate;

import br.senac.rj.api.exceptions.AtributeNotValidException;

public class LivroValidate {

    public static void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new AtributeNotValidException("O título do livro não pode ser nulo ou vazio.");
        }
    }

    public static void validarPreco(Double preco) {
        if (preco == null || preco <= 0) {
            throw new AtributeNotValidException("O preço do livro não pode ser nulo, zero ou negativo.");
        }
    }
}
