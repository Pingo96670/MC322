# MC322

Repositório para os projetos de MC322 da dupla 18.

Feito com Java 21.0.5.

## Tabela de conteúdos
<!-- TOC -->
* [Informações Gerais](#informações-gerais)
* [Dupla](#dupla)
* [Execução](#execução)
    * [A partir da pasta **raiz**](#a-partir-da-pasta-raiz)
    * [A partir da pasta do **Lab*XX***](#a-partir-da-pasta-do-labxx)
* [Novidades - Lab 4](#novidades---lab-4)
    * [Diagrama de Classes](#diagrama-de-classes)
    * [Interfaces](#interfaces)
    * [Exceções](#exceções)
    * [Central de comunicações](#central-de-comunicações)
    * [Atualizações para os robôs](#atualizações-para-os-robôs)
  * [Visão geral do programa](#visão-geral-do-programa)
    * [Menu de Execução](#menu-de-execução)
    * [Menu Interativo - Geral](#menu-interativo---geral)
    * [Menu de Robôs](#menu-de-robôs)
    * [Menu da Central de Comunicação](#menu-da-central-de-comunicação)
    * [Menu de Ambiente](#menu-de-ambiente)
    * [Mapa Plano](#mapa-plano1)
* [Reportando problemas](#reportando-problemas)
<!-- TOC -->

## Informações Gerais

Este projeto consiste em um simulador de robôs, com progressiva adição de novos recursos a cada iteração.

- Lab01
    - Ambiente bidimensional
    - Apenas um robô genérico
- Lab02
    - Ambiente tridimensional
    - Novos tipos de robô (Camel, Fast, Parrot e Jet)
    - Checagem de colisão
- Lab03
    - Menu interativo
    - Obstáculos
    - Sensores
- Lab04
    - Interfaces
    - Exceções
    - Central de comunicações
    - Ações específicas para os robôs

## Dupla
- Kauã Rodrigues da Conceição
    - IntelliJ IDEA 2024.3.5
- João Pedro Kurohiji Bonani
    - Visual Studio Code 1.99.3

## Execução
### A partir da pasta **raiz**
Para compilar e executar o Lab*XX* a partir da pasta **raiz** do projeto, abra a prompt de comando na pasta "MC322" e use:

  ```
  javac LabXX/*.java
  java -cp LabXX Main
  ```

### A partir da pasta do **Lab*XX***
Para compilar e executar o Lab*XX* a partir de sua própria pasta, abra a prompt de comando na pasta "LabXX" e use:

  ```
  javac *.java
  java Main
  ```

## Novidades - Lab 4
### Diagrama de Classes
![alt text](https://github.com/Pingo96670/MC322/blob/main/Lab04/img/Lab04%20-%20UML.jpg "Diagrama de Classes - Lab04")

### Interfaces
Foram implementadas novas interfaces para facilitar a interação e manipulação de objetos com características em comun.
- Communicable: Estabelece a capacidade de comunicação de um robô.
- Entity: Engloba objetos do ambiente, como robôs e obstáculos.
- FluidHandler: Estabelece funcionalidades.
- SelfLearner: Interface voltada para robôs adaptativos. Atualmente apenas implementada no tipo Parrot Bot.
- Sensorable: Engloba qualquer robô capaz de usar sensores.
- WaterCarrier: Especificação de FluidHandler. Atualmente apenas implementada no tipo Camel Bot.

### Exceções
Novas exceções foram adicionadas para lidar com situações específicas:
- InvalidMinSpeedException: Lançada quando um robô Fast Bot tenta ser criado com misSpeed > sensorRadius.
- NoSuchObjectTypeException: Lançada quando um tipo não registrado de objeto (obstáculo, entidade) tenta ser chamado.
- ObjectOutOfBoundsException: Lançada na tentativa de adicionar um obstáculo fora dos limites do ambiente.
- ObjectOverlapException: Lançada na tentativa de adicionar um obstáculo em uma posição que já possui outro objeto.
- RobotUnavailableException: Lançada na tentativa de usar uma ação (íntegra) de um robô desligado. 
- UnsuccessfulRemovalException: Lançada quando um robô não pôde ser retirado de robotList.

Os métodos que lançam essas exceções estão registrados com *throws*.

### Central de comunicações
O menu da central de comunicação é simples, permitindo a visualização de todas as mensagens registradas por ela.
Essa funcionalidade está relacionada à interface *Communicable*.

### Atualizações para os robôs
Cada robô possui agora uma tarefa específica e mais complexa que pode ser executada através de seus respectivos menus.
Além disso, os robôs também podem ser ligados ou desligados.


## Visão geral do programa
### Menu de Execução
A partir do menu de execução, é possível selecionar entre duas opções:
- **Testes automáticos**
    - Conjunto de testes pré-implementados para testar os novos recursos adicionados.
- **Menu interativo**
    - Menu que permite a execução manual de comandos.

---

### Menu Interativo - Geral
O menu interativo utiliza, de forma geral, a navegação numerada, e permite realizar o controle dos robôs, a análise de mensagens enviadas por eles e a análise do ambiente.

---

### Menu de Robôs
O menu de robôs permite a seleção de um robô para ser controlado. Cada robô possui características e comandos específicos, os quais são detalhados em seus respectivos menus.

Os submenus dos robôs aceitam comando em texto. Os comando não são *case sensitive*, e palavras entre parênteses (não colchetes) não são necessárias para leitura do comando.

Para um resumo dos robôs disponíveis, temos:

- **Sandy - Camel Robot:**
    - Possui uma velocidade máxima, podendo se movimentar apenas se a distância movida for menor que esse valor
    - Possui um reservatório de água que pode ser enchido ou esvaziado
    - Possui um sensor de água que pode detectar obstáculos do tipo ÁGUA a uma distância de 6 unidades
- **Speedy - Fast Robot:**
    - Possui uma velocidade mínima e uma velocidade máxima, podendo apenas executar movimentos de distância entre os dois valores
- **Talky - Parrot Robot:**
    - Pode se mover verticalmente
    - Pode aprender frases e repeti-las aleatoriamente
- **Jetty - Jet Robot:**
    - Pode se mover verticalmente
    - Consome uma unidade de combustível por unidade de distância movida

Cada submenu de robô possui dois modos, ***INFO***, que contém informações sobre o robô e uma lista de comandos, e ***FLAT MAP***[^1], que apresenta um mapa plano do ambiente, permitindo a fácil visualização do movimento do robô.

O modo do menu pode ser trocado através do comando "SWAP".

---

### Menu da Central de Comunicação
O menu da central de comunicação é simples, permitindo a visualização de todas as mensagens registradas por ela.

---

### Menu de Ambiente
O menu de ambiente apresenta informações básicas do ambiente, como suas dimensões e os robôs ativos, suas coordenadas e suas direções.

---

### Mapa Plano[^1]
O mapa plano consiste em uma visualização simplificada do ambiente, considerando apenas as dimensões X e Z. Por esse motivo, robôs acima de outros objetos são omitidos.

**Legenda de mapa plano:**
- 0: Vazio
- B: Robô (altura: 1 | Não fixo ao chão)
- W: Água (altura: 0)
- R: Rocha (altura: 1)
- M: Montanha (altura: 5)
- T: Árvore (altura: 2)

## Reportando problemas
Caso encontre algum problema, por favor abra uma *issue* no repositório do projeto, com os passos para reproduzir o efeito inesperado.

---

[^1]: Para a visualização do mapa plano, é recomendado um terminal com suporte à coloração *ANSI*, permitindo a melhor identificação de elementos.

