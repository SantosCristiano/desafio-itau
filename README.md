# API de Produtos de Seguros

## Tecnologias Utilizadas
- JDK 17
- Spring Boot
- H2 Database
- Maven
- Micrometer para métricas
- Spring Cloud Sleuth para rastreamento distribuído

## Pré-requisitos
- Java 17 instalado
- Maven instalado

## Instruções para Execução
1. Clone o repositório:
    ```bash
    git clone https://github.com/SantosCristiano/desafio-itau
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd desafio-itau
    ```
3. Execute o comando para rodar a aplicação:
    ```bash
    mvn spring-boot:run
    ```

## Instruções para Executar Testes e Verificar Cobertura
1. Para executar os testes:
    ```bash
    mvn test
    ```
2. Para gerar o relatório de cobertura de testes, execute:
    ```bash
    mvn clean verify
    ```
   O relatório será gerado no diretório `target/site/jacoco` e pode ser aberto no navegador.

## Endpoints
- `POST /produtos`: Cria um novo produto.
    - **Request Body**:
      ```json
      {
        "nome": "Seguro de Vida Individual",
        "categoria": "VIDA",
        "preco_base": 100.00
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "nome": "Seguro de Vida Individual",
        "categoria": "VIDA",
        "preco_base": 100.00,
        "preco_tarifado": 103.20
      }
      ```
- `PUT /produtos/{id}`: Atualiza um produto existente.
    - **Request Body**:
      ```json
      {
        "nome": "Seguro de Vida Familiar",
        "categoria": "VIDA",
        "preco_base": 150.00
      }
      ```
    - **Response**:
      ```json
      {
        "id": 1,
        "nome": "Seguro de Vida Familiar",
        "categoria": "VIDA",
        "preco_base": 150.00,
        "preco_tarifado": 154.40
      }
      ```
- `GET /produtos`: Lista todos os produtos.
    - **Response**:
      ```json
      [
        {
          "id": 1,
          "nome": "Seguro de Vida Individual",
          "categoria": "VIDA",
          "preco_base": 100.00,
          "preco_tarifado": 103.20
        },
        {
          "id": 2,
          "nome": "Seguro de Auto",
          "categoria": "AUTO",
          "preco_base": 50.00,
          "preco_tarifado": 55.25
        }
      ]
      ```

## Decisões de Design
- Utilizei Spring Boot para agilidade no desenvolvimento e fácil configuração.
- A base de dados H2 foi escolhida pela simplicidade e conveniência para testes locais.
- A lógica de cálculo de preços foi implementada no serviço `ProdutoService`, encapsulando a complexidade e facilitando a manutenção.
- A aplicação inclui observabilidade com Micrometer para métricas e Spring Cloud Sleuth para rastreamento distribuído, facilitando o monitoramento e diagnóstico.