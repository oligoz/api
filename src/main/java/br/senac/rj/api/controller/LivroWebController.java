package br.senac.rj.api.controller;

import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroWebController {

    private final LivroService livroService;

    public LivroWebController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public String mostrarLivros(Model model) {
        List<Livro> livros = this.livroService.listarLivros();
        model.addAttribute("livros", livros);
        return "livros/index";
    }

    @GetMapping("/registrar")
    public String getRegistrarLivroForm(Model model) {
        Livro livro = new Livro();
        model.addAttribute("livro", livro);
        return "livros/registrar";
    }

    @PostMapping("/registrar")
    public String postRegistrarLivro(@ModelAttribute Livro livro, RedirectAttributes redirectAttributes) {
        try {
            this.livroService.incluirLivro(livro);
            redirectAttributes.addFlashAttribute("msg", "Livro registrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao registrar o livro: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    @GetMapping("/editar")
    public String getEditarLivroForm(@RequestParam("codigo") Long codigo, Model model) {
        Livro livro = this.livroService.buscarLivroPorCodigo(codigo);
        model.addAttribute("livro", livro);
        return "livros/editar";
    }

    @PostMapping("/editar")
    public String postEditarLivro(@RequestParam("codigo") Long codigo, @ModelAttribute Livro livro, RedirectAttributes redirectAttributes) {
        try {
            this.livroService.atualizarLivro(codigo, livro);
            redirectAttributes.addFlashAttribute("msg", "Livro atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar o livro: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    @GetMapping("/excluir")
    public String excluirLivro(@RequestParam("codigo") Long codigo, RedirectAttributes redirectAttributes) {
        try {
            this.livroService.excluirLivro(codigo);
            redirectAttributes.addFlashAttribute("msg", "Livro excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir o livro: " + e.getMessage());
        }
        return "redirect:/livros";
    }
}
