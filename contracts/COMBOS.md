## Adicionar Combo
method: POST
\
url: /combos

```json
{
  "name": "Premium",
  "description": "Melhor combo que você verá hoje",
  "image": "http://...",
  "price": 3290 // Preço sempre como inteiro
}
```
Response:
```json
{
  "id": "xxx-xxxxx-xxx",
  "description": "Melhor combo que você verá hoje",
  "image": "http://...",
  "price": 3290 // Preço sempre como inteiro
}
```

## Atualizar Combo
method: PUT
\
url: /combos/{id}

```json
{
  "name": "Premium",
  "description": "Melhor combo que você verá hoje",
  "image": "http://...",
  "price": 3290 // Preço sempre como inteiro
}
```

Response:
```json
{
  "id": "xxx-xxxxx-xxx",
  "description": "Melhor combo que você verá hoje",
  "image": "http://...",
  "price": 3290 // Preço sempre como inteiro
}
```

## Deletar Combo
method: DELETE
\
url: /combos/{id}
\
Response:
```
statusCode: 200
```

## Listar Combos
method: GET
\
url: /combos

Response:

```json
{
  "data": [
    {
      "id": "xxx-xxxxx-xxx",
      "description": "Melhor combo que você verá hoje",
      "image": "http://...",
      "price": 3290, // Preço sempre como inteiro,
      "productsDto": [
        {
          "id": "xxx-xxxxx-xxx",
          "name": "COCA (600 ml)",
          "description": "Deliciosa Coca de 600 ml",
          "price": 750,
          "image": "http://...",
          "links": {
            "self": "http://..."
          }
        }
      ],
      "links": {
        "self": "http://..."
      }
    }
  ],
  "meta": {
    "total": 20,
    "pages": 2,
    "items": 10,
    "current": 1,
    "links": {
      "self": "http://...",
      "next": "http://...",
      "previous": "http://..."
    }
  }
}
```

## Mostrar Combo
method: GET
\
url: /combos/{id}

Response:

```json
{
  "id": "xxx-xxxxx-xxx",
  "description": "Melhor combo que você verá hoje",
  "image": "http://...",
  "price": 3290, // Preço sempre como inteiro,
  "productsDto": [
    {
      "id": "xxx-xxxxx-xxx",
      "name": "COCA (600 ml)",
      "description": "Deliciosa Coca de 600 ml",
      "price": 750,
      "image": "http://...",
      "links": {
        "self": "http://..."
      }
    }
  ],
  "links": {
    "self": "http://..."
  }
}
```

## Mostrar Itens do Combo
method: GET
\
url: /combos/{id}/productsDto

Response:

```json
[
  {
    "id": "xxx-xxxxx-xxx",
    "name": "COCA (600 ml)",
    "description": "Deliciosa Coca de 600 ml",
    "price": 750,
    "image": "http://...",
    "links": {
      "self": "http://..."
    }
  }
]
```

## Adicionar Item ao Combo
method: POST
\
url: /combos/{id}/productsDto
```json
{
  "name": "COCA (600 ml)",
  "description": "Deliciosa Coca de 600 ml",
  "price": 750, // Preço sempre como inteiro
  "image": "http://..."
}
```

Response:
```json
[
  {
    "id": "xxx-xxxxx-xxx",
    "name": "COCA (600 ml)",
    "description": "Deliciosa Coca de 600 ml",
    "price": 750,
    "image": "http://...",
    "links": {
      "self": "http://..."
    }
  }
]
```