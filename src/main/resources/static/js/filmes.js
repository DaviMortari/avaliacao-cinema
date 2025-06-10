
document.addEventListener('DOMContentLoaded', function() {
    // Ponto A: Este bloco de código SÓ RODA quando o HTML inteiro já foi carregado.

    const moviesGridContainer = document.querySelector('.grid-filmes'); 
    // Ponto B: Aqui ele encontra o seu <div class="grid-filmes"></div> no HTML.

    if (!moviesGridContainer) {
        console.error('Elemento .grid-filmes não encontrado no DOM.');
        return;
    }
    
    moviesGridContainer.innerHTML = '<p>Carregando filmes...</p>'; // Feedback visual

    async function fetchAndDisplayMovies() {
        try {
            const response = await fetch('/filmes/info'); // Ponto C: Busca os dados da sua API
            // ... (lógica para tratar a resposta e os erros) ...
            const moviesData = await response.json();

            moviesGridContainer.innerHTML = ''; // Limpa o "Carregando..."

            if (moviesData.length === 0) {
                moviesGridContainer.innerHTML = '<p>Nenhum filme encontrado.</p>';
                return;
            }

            moviesData.forEach(movie => {
                // Ponto D: Para cada filme, cria o HTML do card
                const movieCardElement = document.createElement('div');
                movieCardElement.classList.add('movie-card-item'); 
                // Adapte esta parte para corresponder exatamente à estrutura do seu card
                // e aos dados que vêm da sua API (movie.titulo, movie.urlPoster, etc.)
                movieCardElement.innerHTML = `
                    <img src="${movie.urlPoster || 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD4JItKF96N-zMY_y_JCL2JD_P2cmGdDOJ5g&s'}" alt="Pôster de ${movie.titulo}">
                    <div class="movie-card-item-info">
                        <h3>${movie.titulo}</h3>
                        <p class="genero">${movie.genero || 'Gênero não informado'}</p>
                        <div class="rating">
                            <i class="fas fa-star"></i> ${movie.avaliacao ? movie.avaliacao.toFixed(1) : 'N/A'}
                        </div>
                        <a href="/filmes/${movie.id}" class="btn-ver-detalhes">Ver Detalhes</a>
                    </div>
                `;
                // Ponto E: Adiciona o card criado DENTRO do <div class="grid-filmes">
                moviesGridContainer.appendChild(movieCardElement);
            });

        } catch (error) {
            console.error('Falha ao carregar e exibir filmes:', error);
            moviesGridContainer.innerHTML = '<p>Ocorreu um erro ao carregar os filmes.</p>';
        }
    }

    // Ponto F: ESTA É A CHAMADA DA FUNÇÃO!
    // Assim que o DOM está pronto (Ponto A), esta linha executa a função
    // fetchAndDisplayMovies(), que faz todo o trabalho.
    fetchAndDisplayMovies();

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

    // Chama a função para buscar e renderizar os filmes quando a página carregar
    fetchMovies();

    // Lógica para filtros e paginação (simulação)
    console.log('Página de Filmes carregada e pronta para buscar dados dinâmicos!');
    // Aqui você adaptaria a lógica de filtros e paginação para refazer a chamada a fetchMovies()
    // com os parâmetros adequados (ex: /api/filmes?genero=acao&pagina=2)
});