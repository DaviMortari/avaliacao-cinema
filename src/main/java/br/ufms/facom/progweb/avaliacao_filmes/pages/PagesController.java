package br.ufms.facom.progweb.avaliacao_filmes.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.AvaliacaoDto;
import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.AvaliacaoService;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmesCardDto;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmesService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private FilmesService service;

    @Autowired
    private AvaliacaoService avaliacaoService;

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

    @GetMapping("/avaliacao")
    public String avaliacao(Model model) {
        model.addAttribute("paginaAtual", "avaliacao");
        return "avaliacao";
    }

    @GetMapping("/filmes/{id}")
    public String mostrarPaginaDeAvaliacao(@PathVariable Long id, org.springframework.ui.Model model) {
        try {
            FilmesCardDto filmeParaAvaliar = service.buscarFilmeComoCardDtoPorId(id);
            model.addAttribute("item", filmeParaAvaliar);

            model.addAttribute("avaliacao", new AvaliacaoDto());
            
            return "teste"; // (sem o .html)
        } catch (EntityNotFoundException e) {
            return "redirect:/filmes";
        }
    }

    @PostMapping("/avaliacoes")
    public String salvarAvaliacao(@ModelAttribute("avaliacao") AvaliacaoDto avaliacao) {
        avaliacaoService.salvarAvaliacao(avaliacao);
        return "redirect:/filmes"; 
    }

    @GetMapping("/criarFilme")
    public String criarFilme(){
        return "criarFilme";
    }
}
