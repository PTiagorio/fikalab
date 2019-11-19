# Blockchain for IoT: a Smart Contracts Approach

This project consists of a proof-of-concept based on a conceptually simple problem: a system built for a robot car to be controlled by several mobile phones. However, the approach to this problem was made with cutting-edge, complex, innovative and technically challenging technologies: Internet of Things, Cloud and Blockchain.

This project is based on the idea of bringing these two technologies together, taking advantage of the best features of both and creating a scalable system in which multiple entities control multiple devices. Reaching a system that guarantees:
  * Auditability and immutability from Blockchain;
  * Cloud processing delegation capability and scalability of node numbers from the Cloud and the Internet of Things.

## Path Taken

After conceiving the basic idea for our project, a solution was needed to demonstrate that the system could be implemented in reality. A simple problem where there is a robot-car being controlled by someone has been identified as a good approach.

The group looked at relatively inexpensive and intuitive technologies. We had to get hardware to simulate three different entities in our problem:
1. The controlling entity;
1. The communication entity between the controlling entity and the entity to be controlled;
1. The entity to be controlled.

For the first entity, smartphones were identified, as it would be an easy way to demonstrate several controlling entities, because smartphones are a fairly common technology today. For the second entity the Raspberry Pi was identified, since it is a computer about the size of a credit card, being versatile and cheap. Finally for the third entity was discovered GoPiGo, a robot with wheels prefabricated to support a Raspberry Pi. The discovery of the GoPiGo was an important catalyst to cement our solution, as it is relatively easy to demonstrate results when the third entity has real actions, in this case moving to where the user commands it.

All that remained was to identify how the technologies would be used together. After several brainstorming sessions and several iterations, the group came up with a system architecture:

![General Architecture](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image9.png)
Figura 1 - Mockup da Arquitetura do Sistema.

Neste mockup estão presentes as entidades identificadas anteriormente. Criando uma ponte entre elas e este mockup: as entidades controladoras são os telemóveis; a entidade de comunicação é o Raspberry Pi, o qual é habitualmente denominado de *RaspEstatico* no nosso projeto; e finalmente o GoPiGo que é a entidade controlada.

A blockchain está presente entre os telemóveis e o RaspEstático. A Blockchain irá permitir que todos os comandos realizados pelos telemóveis sejam conhecidos por todos os nós da rede e que os mesmos tenham características de imutabilidade e auditabilidade. A cloud / IoT serve de ponte entre o Raspberry e o GoPiGo. Será usado software para que o GoPiGo esteja subscrito à cloud, o qual consegue fazer com que todos os dispositivos subscritos se movam quando o RaspEstático comunica um comando vindo dos telemóveis. No nosso caso só existe um GoPiGo, mas a escalabildade é uma das principais características deste sistema, graças ao uso de IoT através da Cloud. 

Os aspetos técnicos das tecnologias utilizadas serão explicados nos próximos capítulos, mas no geral a comunicação entre nodos do sistema pode ser sumarizada por:
1. Comando dado por um utilizador do telemóvel para o carro se mexer (toque dum botão);
1. RaspEstático está à escuta de uma evento despoletado pela Blockchain;
1. RaspEstático comunica à cloud o comando a realizar;
1. Cloud comunica ao GoPiGo o comando a realizar;

### GLOBAL PRE-REQUISITES
## npm Install
Windows:
[npm](https://www.npmjs.com/get-npm)  

Ubuntu:

```
$ sudo apt-get update
$ sudo apt-get install nodejs
$ sudo apt-get install npm
```
