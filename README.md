# Desafio: Consulta vendas
Neste desafio, foi proposto implementar um sistema de consulta de vendas utilizando Spring Boot junto com JPA e JPQL. No projeto, um vendedor está relacionado a muitas vendas e uma venda está relacionada a um vendedor.

## Especificação
As seguintes implementações e consultas foram feitas:

### Relatório de vendas
1. [IN] O usuário informa, opcionalmente, data inicial, data final e um trecho do nome do vendedor.
2. [OUT] O sistema informa uma listagem paginada contendo id, data, quantia vendida e nome do vendedor, das vendas que se enquadrem nos dados informados.

Inforações complementares:
- Se a data final não for informada, foi considerada a data atual do sistema.
- Se a data inicial não for informada, foi considerada a data de 1 ano antes da data final.
- Se o nome não for informado, foi considerado um texto vazio.

### Sumário de vendas por vendedor
1. [IN] O usuário informa, opcionalmente, data inicial, data final.
2. [OUT] O sistema informa uma listagem contendo nome do vendedor e soma de vendas deste vendedor no período informado.

Informações complementares:
- As mesmas do caso de uso Relatório de vendas

Exemplo de relacionamento de ```Sale``` (Venda) e ```Seller``` (Vendedor):

![Diagrama de classe](/img/diagrama_classe.png)

## Instruções de Uso
Antes de prosseguir com as instruções de uso, é requerido que você tenha em sua máquina o Java 21, git e Intellij.

### Instalação
Antes de tudo, recomendo que você, pelo terminal, navegue até o diretório Desktop e rode o comando:
```bash
# Clone o repositório
git clone https://github.com/LucasFrancoBN/desafio_consulta_vendas.git
```
Agora basta você abrir o projeto na IDE Intellij, acessar o arquivo ```DsmetaApplication``` e rodar o projeto.

