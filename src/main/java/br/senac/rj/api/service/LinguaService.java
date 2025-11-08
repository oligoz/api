package br.senac.rj.api.service;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Lingua;
import br.senac.rj.api.repository.LinguaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinguaService {

    private final LinguaRepository linguaRepository;

    public LinguaService(LinguaRepository linguaRepository) {
        this.linguaRepository = linguaRepository;
    }

    public List<Lingua> listarLinguas() {
        List<Lingua> linguas = this.linguaRepository.findAll();
        if (linguas.isEmpty()) {
            String mensagem = "Nenhuma língua cadastrada.";
            throw new ResourceNotFoundException(mensagem);
        }
        return linguas;
    }

    public Lingua buscarLinguaPorCodigo(Long codigo) {
        Optional<Lingua> lingua = this.linguaRepository.findById(codigo);
        if (lingua.isEmpty()) {
            String mensagem = "Língua não encontrada com o código: " + codigo;
            throw new ResourceNotFoundException(mensagem);
        }

        Lingua l = lingua.get();
        return l;
    }

    public Lingua incluirLingua(Lingua lingua) {
        return this.linguaRepository.save(lingua);
    }

    public void excluirLingua(Long codigo) {
        Optional<Lingua> lingua = this.linguaRepository.findById(codigo);
        if (lingua.isEmpty()) {
            String mensagem = "Língua não encontrada com o código: " + codigo;
            throw new ResourceNotFoundException(mensagem);
        }
        this.linguaRepository.deleteById(codigo);
    }

    public Lingua atualizarLingua(Long codigo, Lingua linguaAtualizada) {
        Optional<Lingua> lingua = this.linguaRepository.findById(codigo);

        Lingua linguaExistente = lingua.get();
        linguaExistente.setDescricao(linguaAtualizada.getDescricao());

        return this.linguaRepository.save(linguaExistente);
    }
}
