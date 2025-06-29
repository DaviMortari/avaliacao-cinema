/**
 * home.js
 * Este script é responsável por carregar dinamicamente o conteúdo da página inicial,
 * incluindo os carrosséis de filmes e séries em destaque.
 */
document.addEventListener('DOMContentLoaded', function() {

    // Adiciona um efeito de sombra ao cabeçalho quando a página é rolada
    window.addEventListener('scroll', function() {
        const header = document.getElementById('header');
        if (window.scrollY > 50) {
            header.classList.add('scrolled');
        } else {
            header.classList.remove('scrolled');
        }
    });

    /**
     * Função reutilizável para configurar um carrossel (seja de filmes ou séries).
     * @param {string} containerSelector - O seletor CSS para o contêiner do carrossel (ex: '#carousel-filmes').
     * @param {string} apiUrl - O endpoint da API para buscar os dados.
     * @param {string} itemType - O tipo de item ('filmes' or 'series') para construir o link correto.
     */
    async function setupCarousel(containerSelector, apiUrl, itemType) {
        const carouselContainer = document.querySelector(containerSelector);
        
        if (!carouselContainer) {
            console.error(`Elemento ${containerSelector} não foi encontrado no DOM.`);
            return;
        }

        carouselContainer.innerHTML = `<p style="color: white; text-align: center;">Carregando destaques...</p>`;
        
        let scrollInterval = null;

        carouselContainer.addEventListener('mousemove', (event) => {
            const carouselRect = carouselContainer.getBoundingClientRect();
            const mouseX = event.clientX - carouselRect.left;
            const carouselWidth = carouselRect.width;
            const hotZoneWidth = carouselWidth * 0.15;
            const speedFactor = 8;

            if (mouseX < hotZoneWidth) {
                const intensity = (hotZoneWidth - mouseX) / hotZoneWidth;
                startScrolling(-speedFactor * intensity, carouselContainer);
            } else if (mouseX > carouselWidth - hotZoneWidth) {
                const intensity = (mouseX - (carouselWidth - hotZoneWidth)) / hotZoneWidth;
                startScrolling(speedFactor * intensity, carouselContainer);
            } else {
                stopScrolling();
            }
        });

        carouselContainer.addEventListener('mouseleave', stopScrolling);

        function startScrolling(speed, container) {
            if (scrollInterval) clearInterval(scrollInterval);
            scrollInterval = setInterval(() => {
                container.scrollLeft += speed;
            }, 10);
        }

        function stopScrolling() {
            clearInterval(scrollInterval);
            scrollInterval = null;
        }

        try {
            const response = await fetch(apiUrl);
            if (!response.ok) {
                throw new Error(`Erro na API (${apiUrl}): ${response.statusText}`);
            }
            const data = await response.json();

            carouselContainer.innerHTML = '';

            if (data.length === 0) {
                carouselContainer.innerHTML = `<p style="color: white; text-align: center;">Nenhum item em destaque encontrado.</p>`;
                return;
            }

            data.forEach(item => {
                const itemCard = document.createElement('div');
                itemCard.classList.add('movie-card'); // Reutiliza a mesma classe de estilo
                const sinopseCurta = item.sinopse && item.sinopse.length > 100 
                    ? item.sinopse.substring(0, 97) + '...' 
                    : item.sinopse;

                itemCard.innerHTML = `
                    <a href="/pages/${itemType}/${item.id}" class="movie-card-link">
                        <img src="${item.imagem || 'https://placehold.co/250x350/141414/f4f4f4?text=CineAvalia'}" alt="Pôster de ${item.titulo}">
                        <div class="movie-info">
                            <h3>${item.titulo}</h3>
                            <div class="rating">
                                <i class="fas fa-star"></i>
                                <span>${item.mediaAvaliacoes ? item.mediaAvaliacoes.toFixed(1) : 'N/A'}</span>
                            </div>
                            <p>${sinopseCurta || 'Clique para ver mais detalhes.'}</p>
                        </div>
                    </a>
                `;
                carouselContainer.appendChild(itemCard);
            });

        } catch (error) {
            console.error(`Falha ao carregar destaques de ${apiUrl}:`, error);
            carouselContainer.innerHTML = `<p style="color: red; text-align: center;">Ocorreu um erro ao carregar os destaques.</p>`;
        }
    }

    // Chama a função para configurar os dois carrosséis
    setupCarousel('#carousel-filmes', '/api/filmes/listar', 'filmes');
    setupCarousel('#carousel-series', '/api/series/listar', 'series');
});
