package br.ufms.facom.progweb.avaliacao_filmes.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @GetMapping("/home")
    public String cinema(Model model) {
        model.addAttribute("paginaAtual", "home");
        return "cinema";
    }

    @GetMapping("/entrar")
    public String entrar() {
        return "entrar";
    }

    @GetMapping("/criarConta")
    public String criarConta() {
        return "criarConta";
    }

    @GetMapping("/filmes")
    public String filmes(Model model) {
        model.addAttribute("paginaAtual", "filmes");
        return "filmes";
    }

    @GetMapping("/series")
    public String series(Model model) {
        model.addAttribute("paginaAtual", "series");
        return "series";
    }
}
