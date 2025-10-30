package br.senac.rj.api.service;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.repository.LivroRepository;
import br.senac.rj.api.validate.LivroValidate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = this.livroRepository.findAll();
        if (livros.isEmpty()) {
            String mensagem = "Nenhum livro cadastrado.";
            throw new ResourceNotFoundException(mensagem);
        }
        return livros;
    }

    public Livro incluirLivro(Livro livro) {
        LivroValidate.validarTitulo(livro.getTitulo());
        LivroValidate.validarPreco(livro.getPreco());
        return this.livroRepository.save(livro);
    }

    public Livro buscarLivroPorCodigo(Long codigo) {
        Optional<Livro> livro = this.livroRepository.findById(codigo);
        if (livro.isEmpty()) {
            String mensagem = "Livro não encontrado com o código: " + codigo;
            throw new ResourceNotFoundException(mensagem);
        }

        Livro l = livro.get();
        return l;
    }

    public void excluirLivro(Long codigo) {
        Optional<Livro> livro = this.livroRepository.findById(codigo);
        if (livro.isEmpty()) {
            String mensagem = "Livro não encontrado com o código: " + codigo;
            throw new ResourceNotFoundException(mensagem);
        }
        this.livroRepository.deleteById(codigo);
    }

    public Livro atualizarLivro(Long codigo, Livro livroAtualizado) {
        Optional<Livro> livro = this.livroRepository.findById(codigo);

        Livro livroExistente = livro.get();

        LivroValidate.validarTitulo(livroAtualizado.getTitulo());
        LivroValidate.validarPreco(livroAtualizado.getPreco());

        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setPreco(livroAtualizado.getPreco());
        return this.livroRepository.save(livroExistente);
    }
}
