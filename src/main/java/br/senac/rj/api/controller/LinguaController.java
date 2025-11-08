package br.senac.rj.api.controller;

import br.senac.rj.api.exceptions.AtributeNotValidException;
import br.senac.rj.api.model.Lingua;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LinguaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/linguas")
public class LinguaController {

    private final LinguaService linguaService;

    public LinguaController(LinguaService linguaService) {
        this.linguaService = linguaService;
    }

    @GetMapping("/include")
    public ResponseEntity<?> incluirLivro(@RequestBody Lingua lingua) {
        try {
            Lingua novaLingua = this.linguaService.incluirLingua(lingua);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLingua);
        } catch (AtributeNotValidException anve) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(anve.getMessage());
        }
    }
}
