
document.addEventListener('DOMContentLoaded', function() {

    const seriesGridContainer = document.querySelector('.grid-series'); 
    // Ponto B: Aqui ele encontra o seu <div class="grid-series"></div> no HTML.

    if (!seriesGridContainer) {
        console.error('Elemento .grid-series não encontrado no DOM.');
        return;
    }
    
    seriesGridContainer.innerHTML = '<p>Carregando series...</p>'; // Feedback visual

    async function fetchAndDisplaySerie() {
        console.log("cheguei até aqui");
        try {
            const response = await fetch('/api/series/listar'); // Ponto C: Busca os dados da sua API
            // ... (lógica para tratar a resposta e os erros) ...
            const serieData = await response.json();

            seriesGridContainer.innerHTML = ''; // Limpa o "Carregando..."

            if (serieData.length === 0) {
                seriesGridContainer.innerHTML = '<p>Nenhuma serie encontrada.</p>';
                return;
            }

            serieData.forEach(serie => {
                // Ponto D: Para cada filme, cria o HTML do card
                const serieCardElement = document.createElement('div');
                serieCardElement.classList.add('series-card-item'); 
                // Adapte esta parte para corresponder exatamente à estrutura do seu card
                // e aos dados que vêm da sua API (serie.titulo, serie.urlPoster, etc.)
                serieCardElement.innerHTML = `
                    <img src="${serie.imagem || '/img/placeholder_poster.jpg'}" alt="Pôster de ${serie.titulo}">
                    <div class="series-card-item-info">
                        <h3>${serie.titulo}</h3>
                        <p class="genero">${serie.genero || 'Gênero não informado'}</p>
                        <div class="rating">
                            <i class="fas fa-star"></i> ${serie.avaliacao ? serie.avaliacao.toFixed(1) : 'N/A'}
                        </div>
                        <a href="/pages/series/${serie.id}" class="btn-ver-detalhes">Avaliar</a>
                    </div>
                `;
                // Ponto E: Adiciona o card criado DENTRO do <div class="grid-series">
                seriesGridContainer.appendChild(serieCardElement);
            });

        } catch (error) {
            console.error('Falha ao carregar e exibir series:', error);
            seriesGridContainer.innerHTML = '<p>Ocorreu um erro ao carregar os series.</p>';
        }
    }

    // Ponto F: ESTA É A CHAMADA DA FUNÇÃO!
    // Assim que o DOM está pronto (Ponto A), esta linha executa a função
    // fetchAndDisplayserie(), que faz todo o trabalho.
    fetchAndDisplaySerie();

    // Função auxiliar para gerar as estrelas de avaliação (exemplo)
    function renderStars(rating) {
        if (typeof rating !== 'number' || rating < 0 || rating > 5) {
            return Array(5).fill('<i class="far fa-star"></i>').join(''); // 5 estrelas vazias por padrão
        }
        let starsHTML = '';
        const fullStars = Math.floor(rating);
        const halfStar = rating % 1 >= 0.5;
        const emptyStars = 5 - fullStars - (halfStar ? 1 : 0);

        for (let i = 0; i < fullStars; i++) {
            starsHTML += '<i class="fas fa-star"></i>';
        }
        if (halfStar) {
            starsHTML += '<i class="fas fa-star-half-alt"></i>';
        }
        for (let i = 0; i < emptyStars; i++) {
            starsHTML += '<i class="far fa-star"></i>';
        }
        return starsHTML;
    }

    // Chama a função para buscar e renderizar os series quando a página carregar
    fetchSerie();

    // Lógica para filtros e paginação (simulação)
    console.log('Página de Filmes carregada e pronta para buscar dados dinâmicos!');
    // Aqui você adaptaria a lógica de filtros e paginação para refazer a chamada a fetchSeries()
    // com os parâmetros adequados (ex: /api/series?genero=acao&pagina=2)
});