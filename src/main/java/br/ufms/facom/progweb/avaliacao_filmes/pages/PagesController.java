package br.ufms.facom.progweb.avaliacao_filmes.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @GetMapping("/cinema")
    public String cinema() {
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
}
