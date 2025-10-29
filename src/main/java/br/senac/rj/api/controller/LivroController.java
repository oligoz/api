package br.senac.rj.api.controller;

import br.senac.rj.api.exceptions.AtributeNotValidException;
import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/livros")
    public ResponseEntity<?> listarLivros() {
        try {
            List<Livro> livros = this.livroService.listarLivros();
            return ResponseEntity.ok(livros);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @PostMapping("/livros")
    public ResponseEntity<?> incluirLivro(@RequestBody Livro livro) {
        try {
            Livro novoLivro = this.livroService.incluirLivro(livro);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }

    @PutMapping("/livros/{codigo}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long codigo, @RequestBody Livro livroAtualizado) {
        try {
            this.livroService.buscarLivroPorCodigo(codigo);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
        try {
            Livro novoLivroAtualizado = this.livroService.atualizarLivro(codigo, livroAtualizado);
            return ResponseEntity.ok(novoLivroAtualizado);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível atualizar. " + anve.getMessage());
        }
    }

    @DeleteMapping("/livros/delete/{codigo}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long codigo) {
        try {
            this.livroService.excluirLivro(codigo);
            return ResponseEntity.ok("Livro excluído com sucesso.");
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @GetMapping("/livros/{codigo}")
    public ResponseEntity<?> buscarLivroPorCodigo(@PathVariable Long codigo) {
        try {
            Livro livro = this.livroService.buscarLivroPorCodigo(codigo);
            return ResponseEntity.ok(livro);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }
}
