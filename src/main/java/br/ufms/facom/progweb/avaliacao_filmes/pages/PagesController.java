package br.ufms.facom.progweb.avaliacao_filmes.pages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.AvaliacaoDto;
import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.AvaliacaoRequestDto;
import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.AvaliacaoService;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmesCardDto;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmesService;
import br.ufms.facom.progweb.avaliacao_filmes.series.SeriesCardDto;
import br.ufms.facom.progweb.avaliacao_filmes.series.SeriesService;
import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/pages")
public class PagesController {

    @Autowired
    private FilmesService service;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private SeriesService seriesService;

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

            List<AvaliacaoDto> avaliacaoDto = avaliacaoService.listarPorFilmeId(id);
            model.addAttribute("avaliacoes", avaliacaoDto);

            model.addAttribute("novaAvaliacao", new AvaliacaoDto());
            
            return "teste"; // (sem o .html)
        } catch (EntityNotFoundException e) {
            return "redirect:/filmes";
        }
    }

    @GetMapping("/series/{id}")
    public String mostrarPaginaDeAvaliacaoSerie(@PathVariable Long id, org.springframework.ui.Model model) {
        try {
            SeriesCardDto serieParaAvaliar = seriesService.buscarSerieComoCardDtoPorId(id);
            model.addAttribute("item", serieParaAvaliar);

            List<AvaliacaoDto> avaliacaoDto = avaliacaoService.listarPorSerieId(id);
            model.addAttribute("avaliacoes", avaliacaoDto);

            model.addAttribute("novaAvaliacao", new AvaliacaoDto());
            
            return "teste"; // (sem o .html)
        } catch (EntityNotFoundException e) {
            return "redirect:/series";
        }
    }

    @PostMapping("/avaliacoes")
    public String salvarAvaliacao(@ModelAttribute("avaliacao") AvaliacaoRequestDto avaliacao) {
        avaliacaoService.salvarAvaliacao(avaliacao);
        return "redirect:/filmes"; 
    }

    @GetMapping("/criarFilme")
    public String criarFilme(){
        return "criarFilme";
    }

    @GetMapping("/sobre")
    public String sobre(Model model) {
        model.addAttribute("paginaAtual", "sobre");
        return "sobre";
    }
}
