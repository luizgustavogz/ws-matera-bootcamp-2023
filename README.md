# ws-matera-bootcamp-2023
Workspace do Matera Bootcamp 09.2023 - API Rest. Utilizando Java, Spring Boot, Spring Initializr, Spring Data, H2 Database, Postman

# Microserviços

- Abordagem arquitetónica de desenvolvimento de software
- Independência e desacoplamento
- Responsabilidade única
- Serviços autônomos -> independência, tem sua própria base de dados

## Vantagens:
- independência
- não é ponto único de falha
- manutenção por serem independentes
- agilidade
- escalabilidade:
	- vertical: aumentar os recursos da máquina (hardware)
	- horizontal: aumentar os serviços
- independência de tecnologia entre os microserviços

## Desvantagens:
- custo
- mais complexo de manter
- comunicação entre microserviços
- infra fica mais complexa
- dificuldade em manter a padronização entre todos os projetos sem "quebrar" a flexibilidade da equipe

# Microserviço vs Monolito

## Monolito:
- mais de um contexto
- mais de um domínio
- um único serviço rodando
- um sistema único que centraliza todas as funcionalidades da aplicação
- preço mais barato
- ao ter uma única linguagem, fica mais fácil a manutenção

## Microserviços:
- rodando em diferentes instâncias
- diferentes tecnologias

depende da necessidade?

# Stateless vs Stateful

## Stateless:
- não mantém estado ou informações sobre sessões entre as chamadas
- chamadas independentes

## Stateful:
- mantém o estado entre as chamadas. Pode armazenar dados sobre sessões ou transações e lembrar o estado atual do cliente
- preserva o dado durante toda execução

tipos de comunicação entre microserviços:

# Tipos de comunicação entre Microserviços

## Síncrona:
- ao fazer uma chamada você espera uma resposta. Ex: REST, SOAP

## Assíncrona
- não espera uma resposta instantânea. Ex: Mensageria, Comunicação orientada a eventos


### MicroA -> posta uma mensagem num serviço de mensageria. <- MicroB