## Criar Pedido
method: POST
\
url: /orders/{accountId}

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
