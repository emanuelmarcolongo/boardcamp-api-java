<h1 align="center"> Boardcamp - Springboot API</h1>
<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt:"Java"/>
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt:"Spring"/>
   <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt:"postgres"/>
   <img src="https://img.shields.io/badge/Render-%46E3B7.svg?style=for-the-badge&logo=render&logoColor=white" alt:"render"/>
</p>

<h1 align="center"> Boardcamp - Uma locadora de jogos de tabuleiro</h1>

<p align="center">
<img src="https://www.mannyandme.com/wp-content/uploads/2017/12/BoardGames-logo.jpg" alt:"render"/> </p>

- Hoje em dia é muito comum as pessoas jogarem jogos eletrônicos, seja no PC, console ou mesmo no celular. Mas a verdade é que existe outro tipo de categoria de jogos muito legais que infelizmente caiu em desuso: os jogos de tabuleiro! 🎲
- Assim como jogos eletrônicos, os jogos de tabuleiro podem proporcionar muitos momentos de diversão, principalmente se você estiver com seus amigos(as) e/ou familiares! No entanto existe um problema gravíssimo associado a eles: os preços. 💸
- Como os preços desses jogos costumam ser muito caros, surgiram as locadoras de jogos de tabuleiro. Assim como fazíamos com as locadoras de VHS (essas sim já eram pra sempre, rs), nós podemos alugar esses jogos e aproveitá-los por um período de tempo sem o compromisso de fazer um super investimento para comprá-los!

## Requisitos

- Java 17.
- PostgreSQL.

### Como rodar o projeto:

Clone o repositório.

```bash
    git clone git@github.com:emanuelmarcolongo/boardcamp-api-java.git
```

Crie um banco de dados com o nome desejado<br>
Crie um arquivo .env para configurar seu banco, como no .env.example

```env
DB_URL=jdbc:postgresql://localhost:5432/{nome-banco}
DB_USERNAME={usuario}
DB_PASSWORD={senha}
```

Para rodar a aplicação, basta ir no arquivo src/java/com/boardcamp/boardcampapi/BoardcampApiAplication.java e rodar.

## Documentação - API - ROTAS

<details>
  <summary>JOGOS (Games) Rota: /games</summary>

- Entidade (GAME):

```JSON
{
  "id": 1,
  "name": "Banco Imobiliário",
  "image": "http://",
  "stockTotal": 3,
  "pricePerDay": 1500
}
```

- ### GET /games
  status: 200 (OK) -> Recebe uma lista dos jogos cadastrados

```JSON
[
  {
    "id": 1,
    "name": "Banco Imobiliário",
    "image": "http://",
    "stockTotal": 3,
    "pricePerDay": 1500
  },
  {
    "id": 2,
    "name": "Detetive",
    "image": "http://",
    "stockTotal": 1,
    "pricePerDay": 2500
  },
]
```

- ### POST /games
- Corpo da Requisição Esperado:

```JSON
{
  "name": "Banco Imobiliário",
  "image": "http://www.imagem.com.br/banco_imobiliario.jpg",
  "stockTotal": 3,
  "pricePerDay": 1500
}
```

- Resposta - status 201 (CREATED)

```JSON
{
  "id": 1,
  "name": "Banco Imobiliário",
  "image": "http://www.imagem.com.br/banco_imobiliario.jpg",
  "stockTotal": 3,
  "pricePerDay": 1500
}
```

- ## Regras de negócio:

- name => Deve estar presente, não nulo e não deve ser uma string vazia ""; <br>
- stockTotal e pricePerDay => devem ser numeros maiores que 0, não podem ser nulos. <br>
- Resposta - status 400 (BAD REQUEST)

```JSON
{
  "timestamp": "2024-02-07T15:13:32.040+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/games"
}
```

<br>

- name => Não pode ser um nome de um jogo já existente.
- Resposta - status 409 (CONFLICT);

```JSON
{
  "Game with given name already exists"
}
```

</details>
<br>
<details>
  <summary>CLIENTES (Customers) Rota: /customers</summary>

- Entidade (Customer):

```JSON
{
  "id": 1,
  "name": "João Alfredo",
  "cpf": "01234567890"
}
```

- ### GET /customers/:id

GET /customers/1 <br>
status: 200 (OK) -> Recebe o cliente com determinado ID. <br>
Resposta:

```JSON
{
  "id": 1,
  "name": "João Alfredo",
  "cpf": "01234567890"
}
```

Caso não haja cliente com determinado ID: <br>
Status da Resposta: 404 (NOT FOUND). <br>
Corpo da Resposta:

```JSON
{
  "Customer not found with given ID"
}
```

- ### POST /customers
- Corpo da Requisição Esperado:

```JSON
{
  "name": "João Alfredo",
  "cpf": "01234567890"
}
```

- Resposta - status 201 (CREATED)

```JSON
{
  "id": 1,
  "name": "João Alfredo",
  "cpf": "01234567890"
}
```

- ## Regras de negócio:

- name => Deve estar presente, não nulo e não deve ser uma string vazia ""; <br>
- cpf => deve ser uma string com 11 caracteres, não pode ser nulo nem string vazia;

Resposta - status 400 (BAD REQUEST)

```JSON
{
  "timestamp": "2024-02-07T15:13:32.040+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/cusotmers"
}
```

<br>

- cpf => Não pode ser o cpf de um cleinte já existente.
- Resposta - status 409 (CONFLICT);

```JSON
{
  "CPF already registered"
}
```

</details>

<br>

<details>
  <summary>ALUGUEIS (Rentals) Rota: /rentals</summary>

- Entidade (Rental):

```JSON
{
  "id": 1,
  "customerId": 1,
  "gameId": 1,
  "rentDate": "2021-06-20",    // data em que o aluguel foi feito, formato LocalDate
  "daysRented": 3,             // por quantos dias o cliente agendou o aluguel
  "returnDate": null,          // data que o cliente devolveu o jogo (null enquanto não devolvido)
  "originalPrice": 4500,       // preço total do aluguel em centavos (dias alugados vezes o preço por dia do jogo)
  "delayFee": 0                // multa total paga por atraso (dias que passaram do prazo vezes o preço por dia do jogo), começa como 0
}
```

- ### GET /rentals

GET /rentals <br>
Status da Resposta: 200 (OK) -> Recebe uma lista com os alugueis realizados. <br>
Corpo da Resposta:

```JSON
[
  {
    "id": 1,
    "rentDate": "2021-06-20",
    "daysRented": 3,
    "returnDate": null, // troca pra uma data quando já devolvido
    "originalPrice": 4500,
    "delayFee": 0, // troca por outro valor caso tenha devolvido com atraso
    "customer": {
      "id": 1,
      "name": "João Alfredo",
		  "cpf": "01234567890"
    },
    "game": {
      "id": 1,
		 "name": "Banco Imobiliário",
		  "image": "http://www.imagem.com.br/banco.jpg",
		  "stockTotal": 3,
		  "pricePerDay": 1500
    }
  }
]
```

- ### POST /rentals
- Cria um novo registro de aluguel.
- Corpo da Requisição Esperado:

```JSON
{
  "customerId": 1,
  "gameId": 1,
  "daysRented": 3
}
```

- Resposta - status 201 (CREATED)

```JSON
{
    "id": 1,
    "rentDate": "2021-06-20",
    "daysRented": 3,
    "returnDate": null,
    "originalPrice": 4500,
    "delayFee": 0,
    "customer": {
      "id": 1,
      "name": "João Alfredo",
		  "cpf": "01234567890"
    },
    "game": {
      "id": 1,
		  "name": "Banco Imobiliário",
		  "image": "http://www.imagem.com.br/banco.jpg",
		  "stockTotal": 3,
		  "pricePerDay": 1500
    }
  }
```

- ## Regras de negócio:

- daysRented => Deve estar presente, não nulo e maior que 0; <br>
- gameId e customerId não podem ser nulos.

Resposta - status 400 (BAD REQUEST)

```JSON
{
  "timestamp": "2024-02-07T15:13:32.040+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/rentals"
}
```

<br>

- gameId => Deve se referir a um jogo existente.
- Resposta - status 404 (NOT FOUND);

```JSON
{
  "No game with given Id"
}
```

- customerId => Deve se referir a um cliente existente.
- Resposta - status 404 (NOT FOUND);

```JSON
{
  "No customer with given Id"
}
```

- Caso o jogo já possua uma quantidade de alugueis presentes (que ainda não foi devolvido)
- Resposta - status 422(UNPROCESSABLE ENTITY);

```JSON
{
  "All copies of this game are already rented"
}
```

- ### PUT /rentals/:id/return
- Retorna um aluguel pendente.

Caso o ID seja válido e o aluguel não tenha sido retornado.

- Resposta - status 200 (OK);
- Corpo da Resposta:

```json
{
  "id": 1,
  "rentDate": "2021-06-20",
  "daysRented": 3,
  "returnDate": "2021-06-25",
  "originalPrice": 4500,
  "delayFee": 3000,
  "customer": {
    "id": 1,
    "name": "João Alfredo",
    "cpf": "01234567890"
  },
  "game": {
    "id": 1,
    "name": "Banco Imobiliário",
    "image": "http://www.imagem.com.br/banco.jpg",
    "stockTotal": 3,
    "pricePerDay": 1500
  }
}
```

- ## Regras de negócio:
- O id do aluguel fornecido deve existir

- Resposta - status 404 (NOT FOUND);
- Corpo da Resposta:

```json
"Rental with given id does not exists."
```

- O aluguel não deve estar finalizado
- Resposta - status 422 (UNPROCESSABLE ENTITY)
- Corpo da Resposta

```json
"This rental has already been returned."
```

- Caso os dias com o jogo (diferença entre o rentDate e o returnDate) seja maior que os dias alugados (daysRented), é acrescentado uma taxa (delayFee) equivalente aos dias com o jogo que excederam os dias alugados multiplicados pelo preço diário (pricePerDay) do jogo alugado!

</details>
