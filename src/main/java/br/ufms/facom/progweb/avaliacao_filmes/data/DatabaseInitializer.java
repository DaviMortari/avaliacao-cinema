package br.ufms.facom.progweb.avaliacao_filmes.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmeRepository;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.series.Series;
import br.ufms.facom.progweb.avaliacao_filmes.series.SeriesRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner{

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    SeriesRepository seriesRepository;

    @Override
    public void run(String... args) throws Exception {
        
        if (filmeRepository.count() > 0 || seriesRepository.count() > 0) {
            return;
        }

        Filmes IlhaDoMedo = new Filmes("Ilha do Medo", "Suspense", "Martin Scorsese", 2010, "Um detetive investiga o desaparecimento de uma prisioneira em um hospital psiquiátrico.", "https://media.themoviedb.org/t/p/w500/qnWJFFkRv61e030Yc4xG8oVZUqG.jpg");

        Filmes Titanic = new Filmes("Titanic", "Romance", "James Cameron", 1997, "Um romance trágico a bordo do famoso navio que afundou.", "https://image.tmdb.org/t/p/original/1kLYRzVj6byWvFa3SLrAOcfgnfp.jpg");

        Filmes Conclave = new Filmes("O Conclave", "Drama", "Ron Howard", 2023, "Após a morte do Papa, cardeais se reúnem para eleger um novo líder da Igreja Católica.", "https://media.themoviedb.org/t/p/w500/l4WXg5oQPK6GVlISKQNIUGb8rKJ.jpg");

        Filmes PoderosoChefao = new Filmes("O Poderoso Chefão", "Crime", "Francis Ford Coppola", 1972, "A história da família mafiosa Corleone e sua luta pelo poder.", "https://image.tmdb.org/t/p/original/3Tf8vXykYhzHdT0BtsYTp570JGQ.jpg");

        Filmes AutoDaCompadecida = new Filmes("O Auto da Compadecida", "Comédia", "Guel Arraes", 2000, "Dois nordestinos enfrentam o diabo e a morte em uma série de aventuras.", "https://media.themoviedb.org/t/p/w500/2AGBk8Oiv6tamlVcoi6fxPhDUGZ.jpg");

        filmeRepository.save(IlhaDoMedo);
        filmeRepository.save(Titanic);
        filmeRepository.save(Conclave);
        filmeRepository.save(PoderosoChefao);
        filmeRepository.save(AutoDaCompadecida);

        Series lost = new Series("Lost", "Aventura", "J.J. Abrams", 2004, "Sobreviventes de um acidente aéreo lutam para sobreviver em uma ilha misteriosa.", 6);
        Series breakingBad = new Series("Breaking Bad", "Crime", "Vince Gilligan", 2008, "Um professor de química se torna fabricante de metanfetamina após ser diagnosticado com câncer.", 5);
        Series gameOfThrones = new Series("Game of Thrones", "Fantasia", "David Benioff e D.B. Weiss", 2011, "Famílias nobres lutam pelo controle do Trono de Ferro em um mundo medieval.", 8);
        Series strangerThings = new Series("Stranger Things", "Ficção Científica", "The Duffer Brothers", 2016, "Um grupo de crianças enfrenta forças sobrenaturais em uma pequena cidade dos anos 80.", 4);
        Series theOffice = new Series("The Office", "Comédia", "Greg Daniels", 2005, "A vida cotidiana dos funcionários de uma empresa de papel em Scranton, Pensilvânia.", 9);

        seriesRepository.save(lost);
        seriesRepository.save(breakingBad);
        seriesRepository.save(gameOfThrones);
        seriesRepository.save(strangerThings);
        seriesRepository.save(theOffice);
        
    }
}
