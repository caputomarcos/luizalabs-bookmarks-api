# Magalu - API de Produtos Favoritos dos Clientes

O Magazine Luiza (Magalu) está expandindo seus negócios e uma das novas missões do time de tecnologia é criar uma funcionalidade de Produtos Favoritos de nossos Clientes, em que os nossos aplicativos irão enviar requisições HTTP para um novo backend que deverá gerenciar nossos clientes e seus produtos favoritos.

Esta nova API REST será crucial para as ações de marketing da empresa e terá um grande volume de requisições, então é importante ter em mente que a preocupação com performance é constante.

## Requisitos

A API deve atender aos seguintes requisitos:


## Gerenciamento de clientes

* Criar, atualizar, visualizar e remover clientes
* O cadastro dos clientes deve conter apenas seu nome e endereço de e-mail

* Um cliente não pode se registrar duas vezes com o mesmo endereço de e-mail

## Gerenciamento de produtos favoritos

* Cada cliente só deverá ter uma única lista de produtos favoritos
* Em uma lista de produtos favoritos podem existir uma quantidade ilimitada de produtos
* Um produto não pode ser adicionado em uma lista caso ele não exista

* Um produto não pode estar duplicado na lista de produtos favoritos de um cliente


## Outros requisitos

* A documentação da API de produtos pode ser visualizada neste link: https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec
* O dispositivo que irá renderizar a resposta fornecida por essa nova API irá apresentar o Título, Imagem, Preço e irá utilizar o ID do produto para formatar o link que ele irá acessar. Quando existir um review para o produto, o mesmo será exibido por este dispositivo.

* O acesso à API deve ser aberto ao mundo, porém deve possuir autenticação e autorização.

## Orientações gerais

* Utilize a linguagem Java
* Utilize MySQL ou Postgresql como banco de dados
* As APIs deverão seguir o modelo RESTful com formato JSON
* Faça testes unitários, foque em suíte de testes bem organizados
* Siga as boas práticas de programação
* O desafio deve ser enviado preferencialmente como repositório GIT público (Github ou Gitlab) e deve estar com um modelo de licença de código aberto
* Siga boas práticas de desenvolvimento, de qualidade e de governança de código
* Inclua instruções claras sobre como instalar, testar e executar seu código.

---

# Pré-requisitos

Antes de executar a API, é necessário ter os seguintes pré-requisitos instalados em seu computador:

    - Java 11
    - Docker
    - Docker Compose
    

# Instalação

Para clonar o repositório do projeto, você pode executar o seguinte comando no seu terminal:
    
    git clone https://github.com/caputomarcos/luizalabs-bookmarks-api.git
    
Isso irá criar uma cópia do repositório em sua máquina local. Certifique-se de ter o Git instalado em sua máquina antes de executar este comando.

Após clonar o repositório, execute o seguinte comando para iniciar o servidor:

    ./run start

A API estará disponível em http://localhost:18080.

## Documentação

A documentação da API está disponível no Swagger. Você pode acessá-la em:

http://localhost:18080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## Como usar

Antes de usar a API, você precisará criar uma conta de usuário. Para isso, utilize o seguinte comando cURL:

    curl -X 'POST' 'http://localhost:18080/api/users' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
        "name": "[seu_nome]",
        "email": "[seu_email]",
        "password": "[sua_senha]"
      }'

Após criar a conta, você poderá fazer login e começar a usar a API. Para fazer login, utilize o seguinte comando cURL:

    curl -X 'POST' 'http://localhost:18080/api/auth/login' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
        "username": "[seu_email]",
        "password": "[sua_senha]"
      }'

Isso retornará um token de acesso que você deverá utilizar em todas as suas requisições à API. Por exemplo, para criar um novo bookmark, utilize o seguinte comando cURL:

    curl -X POST http://localhost:18080/api/bookmarks \
        -H 'Content-Type: application/json' \
        -H 'Authorization: Bearer [SEU_TOKEN_JWT]' \
        -d '{
                "title": "Product 9",
                "url": "http://example.com",
                "description": "Bookmark Description",
                "tags": ["tag1", "tag2"]
            }'
Lembre-se de substituir [SEU_TOKEN_JWT] pelo token JWT que você obteve ao fazer login na API.
