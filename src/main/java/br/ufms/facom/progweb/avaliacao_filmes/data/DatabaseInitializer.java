package br.ufms.facom.progweb.avaliacao_filmes.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmeRepository;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    FilmeRepository filmeRepository;

    @Override
    public void run(String... args) throws Exception {
        Filmes IlhaDoMedo = new Filmes("Ilha do Medo", "Suspense", "Martin Scorsese", 2010, "Um detetive investiga o desaparecimento de uma prisioneira em um hospital psiquiátrico.");

        Filmes Titanic = new Filmes("Titanic", "Romance", "James Cameron", 1997, "Um romance trágico a bordo do famoso navio que afundou.");

        Filmes Conclave = new Filmes("O Conclave", "Drama", "Ron Howard", 2023, "Após a morte do Papa, cardeais se reúnem para eleger um novo líder da Igreja Católica.");

        Filmes PoderosoChefao = new Filmes("O Poderoso Chefão", "Crime", "Francis Ford Coppola", 1972, "A história da família mafiosa Corleone e sua luta pelo poder.");

        Filmes AutoDaCompadecida = new Filmes("O Auto da Compadecida", "Comédia", "Guel Arraes", 2000, "Dois nordestinos enfrentam o diabo e a morte em uma série de aventuras.");

        filmeRepository.save(IlhaDoMedo);
        filmeRepository.save(Titanic);
        filmeRepository.save(Conclave);
        filmeRepository.save(PoderosoChefao);
        filmeRepository.save(AutoDaCompadecida);
        
    }
}
