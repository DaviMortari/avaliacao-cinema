# CineAvalia 🎬

CineAvalia é uma plataforma para avaliação de filmes e séries, permitindo que os usuários compartilhem suas opiniões e descubram novas obras. O projeto foi desenvolvido em Java com Spring Boot.
---
## 👨‍💻 Autores

Este projeto foi desenvolvido por:
* Davi
* Jeovana
* Lívia
---

## 🚀 Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

* **Backend:**
    * Java 17
    * Spring Boot 3
    * Spring Web
    * Spring Data JPA
    * Spring Security
* **Frontend:**
    * Thymeleaf
    * HTML5, CSS3, JavaScript
* **Banco de Dados:**
    * H2 (Em arquivo)
* **Documentação da API:**
    * Springdoc (Swagger UI)
* **Build e Dependências:**
    * Maven

---

## ✨ Funcionalidades

* Listagem de filmes e séries a partir de um catálogo.
* Página de detalhes para cada filme e série.
* Sistema de autenticação e cadastro de usuários.
* Sistema de avaliação com nota e comentário.
* Busca por título, gênero e diretor.
* Exibição dos "Top 3" filmes e séries mais bem avaliados na página inicial.
* API REST para consulta de dados.
* Formulário para cadastro de novos filmes.

---

## ⚙️ Como Executar o Projeto

Siga os passos abaixo para executar o projeto em seu ambiente local.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 17 ou superior](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Apache Maven](https://maven.apache.org/download.cgi) (Opcional, pois o projeto inclui o Maven Wrapper)

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/DaviMortari/avaliacao-cinema.git](https://github.com/DaviMortari/avaliacao-cinema.git)
    ```

2.  **Navegue até a pasta do projeto:**
    ```bash
    cd avaliacao-cinema
    ```

3.  **Execute a aplicação usando o Maven Wrapper:**
    * No Linux ou macOS:
        ```bash
        ./mvnw spring-boot:run
        ```
    * No Windows:
        ```bash
        mvnw.cmd spring-boot:run
        ```

A aplicação estará rodando em `http://localhost:8080`.

---

## 🌐 Acessando a Aplicação

Depois de iniciar, você pode acessar os seguintes endereços no seu navegador:

* **Página Inicial:** `http://localhost:8080/pages/home`
* **Documentação da API (Swagger):** `http://localhost:8080/swagger-ui.html`
* **Console do Banco de Dados (H2):** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:file:./data/meuBanco`
    * **Usuário:** `admin`
    * **Senha:** `admin`