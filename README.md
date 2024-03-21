<h1 align="center">
<p>ViaCEP API Integration: Kotlin Spring Framework 🚀🤓💻</p>
</h1>

## ✅ Objetivo do projeto
<p style="text-align: justify;"> O objetivo geral da tarefa é desenvolver uma API Restful em Kotlin utilizando o framework Spring, que consuma dados da API de CEP (viacep.com.br) e os armazene em um banco de dados H2. A API deve expor endpoints para inserir, buscar, atualizar e deletar informações com base no código CEP, utilizando validações em backend para garantir a integridade dos dados. As propriedades da aplicação devem ser configuradas em um arquivo application.yml, e o projeto deve seguir o padrão MVC. Além disso, testes unitários e de integração devem ser implementados utilizando Mockito e MockMvc, respectivamente, incluindo a simulação de chamadas externas usando o WireMock. O Swagger deve ser configurado para documentação dos endpoints e os códigos HTTP devem ser retornados conforme o padrão REST.</p>

## 💻 Tecnologias utilizadas

<ol>
  <li> <strong>Kotlin</strong></li>
  <li> <strong>Spring Framework</strong></li>
  <li> <strong>Banco de dados H2</strong></li>
  <li> <strong>OpenFeign</strong></li>
  <li> <strong>Junit5</strong></li>
  <li> <strong>Mockito</strong></li>
  <li> <strong>MockMvc</strong></li>
  <li> <strong>WireMock</strong></li>
  <li> <strong>Swagger</strong></li>
</ol>

## 📒 Pré-requisitos

Antes de executar esta aplicação, verifique se você possui o seguinte ambiente configurado:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - Versão 17 ou superior.
- [Gradle](https://gradle.org//) - Ferramenta de gerenciamento de projetos Java.
- [Git](https://git-scm.com/) - Sistema de controle de versão distribuído.
- Uma IDE de sua escolha, como [IntelliJ IDEA](https://www.jetbrains.com/idea/).

## 👽💻 Clonar o projeto

1. Abra um terminal.

2. Clone este repositório executando o seguinte comando:

    Usando https:
    ```bash
    git clone https://github.com/moisesmiiranda/PocViaCepIntegrationAPI.git
    ```
    Usando ssh:
    ```bash
    git clone git@github.com:moisesmiiranda/PocViaCepIntegrationAPI.git
    ```

3. Navegue até o diretório do projeto:

    ```bash
    cd PocViaCepIntegrationAPI
    ```

4. Execute o seguinte comando Gradle para compilar e iniciar a aplicação:

    ```bash
    ./gradlew bootRun
    ```
5. Após a inicialização bem-sucedida, a aplicação estará disponível em `http://localhost:8080`.

## 🤓📗 Documentação da API

A documentação da API pode ser acessada em `http://localhost:8080/swagger-ui/index.html`, onde você encontrará informações sobre os endpoints disponíveis e como usá-los.

## ⚒️👽 Testes

Para executar os testes automatizados, você pode usar o seguinte comando Gradle:




