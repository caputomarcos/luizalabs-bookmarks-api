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

O desafio será avaliado de acordo com a posição e o nível que você está se candidatando. Todos os desenvolvedores podem participar do processo de avaliação técnica, portanto, certifique-se de incluir orientações claras para os avaliadores.
