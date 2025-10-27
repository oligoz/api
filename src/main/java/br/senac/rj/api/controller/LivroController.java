package br.senac.rj.api.controller;

import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/livros")
    public List<Livro> listarLivros() {
        return this.livroService.listarLivros();
    }

    @PostMapping("/livros")
    public Livro incluirLivro(@RequestBody Livro livro) {
        return this.livroService.incluirLivro(livro);
    }

    @PutMapping("/livros/{codigo}")
    public Livro atualizarLivro(@PathVariable Long codigo, @RequestBody Livro livroAtualizado) {
        return this.livroService.atualizarLivro(codigo, livroAtualizado);
    }

    @DeleteMapping("/livros/delete")
    public void excluirLivro(@RequestBody Long codigo) {
        this.livroService.excluirLivro(codigo);
    }

    @DeleteMapping("/livros/delete/{codigo}")
    public void excluirLivro2(@PathVariable Long codigo) {
        this.livroService.excluirLivro(codigo);
    }

    @GetMapping("/livros/{codigo}")
    public Optional<Livro> buscarLivroPorCodigo(@PathVariable Long codigo) {
        if (this.livroService.buscarLivroPorCodigo(codigo).isPresent()) {
            return this.livroService.buscarLivroPorCodigo(codigo);
        } else {
            throw new RuntimeException("Livro não encontrado com o código: " + codigo);
        }
    }
}
