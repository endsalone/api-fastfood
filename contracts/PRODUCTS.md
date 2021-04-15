## Adicionar Produto
method: POST
\
url: /productsDto

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
{
  "id": "xxx-xxxxx-xxx",
  "name": "COCA (600 ml)",
  "description": "Deliciosa Coca de 600 ml",
  "price": 750, // Preço sempre como inteiro
  "image": "http://..."
}
```

## Atualizar Produto
method: PUT
\
url: /productsDto/{id}

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
{
  "id": "xxx-xxxxx-xxx",
  "name": "COCA (600 ml)",
  "description": "Deliciosa Coca de 600 ml",
  "price": 650, // Preço sempre como inteiro
  "image": "http://..."
}
```

## Deletar Produto
method: DELETE
\
url: /productsDto/{id}

Response:
```
statusCode: 200
```

## Listar Produtos
method: GET
\
url: /productsDto

Response:

```json
{
  "data": [
    {
      "id": "xxx-xxxxx-xxx",
      "name": "COCA (600 ml)",
      "description": "Deliciosa Coca de 600 ml",
      "price": 750,
      // Preço sempre como inteiro
      "image": "http://...",
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

## Mostrar Produto
method: GET
\
url: /productsDto/{id}

Response:

```json
{
  "id": "xxx-xxxxx-xxx",
  "name": "COCA (600 ml)",
  "description": "Deliciosa Coca de 600 ml",
  "price": 750,
  // Preço sempre como inteiro
  "image": "http://...",
  "links": {
    "self": "http://..."
  }
}
```