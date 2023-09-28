# Workspace do Matera Bootcamp 09.2023
- API Rest, Java, Spring Boot, Spring Initializr, Spring Data, H2 Database, JPA, Postman/Insomnia, Rest Assured


# Microserviços
- Abordagem arquitetónica de desenvolvimento de software
- Independência e desacoplamento
- Responsabilidade única
- Serviços autônomos -> independência, tem sua própria base de dados

## Vantagens:
- Independência
- Não é ponto único de falha
- Manutenção por serem independentes
- Agilidade
- Escalabilidade:
	- Vertical: aumentar os recursos da máquina (hardware)
	- Horizontal: aumentar os serviços
- Independência de tecnologia entre os microserviços

## Desvantagens:
- Custo
- Mais complexo de manter
- Comunicação entre microserviços
- Infra fica mais complexa
- Dificuldade em manter a padronização entre todos os projetos sem "quebrar" a flexibilidade da equipe


# Microserviço vs Monolito

## Monolito:
- Mais de um contexto
- Mais de um domínio
- Um único serviço rodando
- Um sistema único que centraliza todas as funcionalidades da aplicação
- Preço mais barato
- Ao ter uma única linguagem, fica mais fácil a manutenção

## Microserviços:
- Rodando em diferentes instâncias
- Diferentes tecnologias


# Stateless vs Stateful

## Stateless:
- Não mantém estado ou informações sobre sessões entre as chamadas
- Chamadas independentes

## Stateful:
- Mantém o estado entre as chamadas. Pode armazenar dados sobre sessões ou transações e lembrar o estado atual do cliente
- Preserva o dado durante toda execução


# Tipos de comunicação entre Microserviços

## Síncrona:
- Ao fazer uma chamada você espera uma resposta. Ex: REST, SOAP

## Assíncrona
- Não espera uma resposta instantânea. Ex: Mensageria, Comunicação orientada a eventos


### MicroA -> posta uma mensagem num serviço de mensageria. <- MicroB


# Anotações de relacionamento (Associações):

### @ManyToOne
- n > 1

### @ManyToMany
- n > n

### @OneToOne
- 1 > 1

### @JoinColumn

### FetchType.Lazy (Preguiçoso)
- Todo relacionamento terminado em ToMany
- Todos os mapeamentos do Hibernate são Lazy por padrão, se não especificar o tipo de "fetch" (busca)
- Carrega sobre demanda

### FetchType.Eager (Ansioso)
- Todo relacionamento terminado em ToOne
- Carrega todos os dados de uma vez


# Threads
- A mínima unidade de instruções programadas: threads do SO, threads do Java

## Banco (nosso microserviço)
- Java -> JVM -> faz o gerenciamento das threads -> JVM faz o gerenciamento da mínima unidade de trabalho
- Chega um resquest em algum de nossos endpoints:
	- Quando crio uma conta -> utiliza uma thread
	- Quando faço um pix -> utiliza uma thread

## Vantagens:
- Processamento simunltâneo

## Desvantagens:
- Concorrência


# Tipos de Locks

## Otimista
- Conflitos entre as threads são raros
	- Quando acontecer podem ser envolvidos mecanismos de resolução
- Várias threads podem acessar o recurso de forma simultânea
- Versionado -> cada objeto tem uma versão. Na hora de alterar o objeto, verifica se:
	- A versão que tenho em memória == versão que está salva na base de dados -> sucesso : erro
	- A cada alteração do objeto, após salvar, a versão é alterada
- No caso de Hibernate, gerencia a versão

## Pessimista
- Conflitos entre as threads acontecem com frequência
- Os locks são restritos e faz com que uma única thread acesse o recurso ao mesmo tempo
