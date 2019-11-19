# Blockchain for IoT: a Smart Contracts Approach

This project consists of a proof-of-concept based on a conceptually simple problem: a system built for a robot car to be controlled by several mobile phones. However, the approach to this problem was made with cutting-edge, complex, innovative and technically challenging technologies: Internet of Things, Cloud and Blockchain.

This project is based on the idea of bringing these two technologies together, taking advantage of the best features of both and creating a scalable system in which multiple entities control multiple devices. Reaching a system that guarantees:
  * Auditability and immutability from Blockchain;
  * Cloud processing delegation capability and scalability of node numbers from the Cloud and the Internet of Things.

# Path Taken

After conceiving the basic idea for our project, a solution was needed to demonstrate that the system could be implemented in reality. A simple problem where there is a robot-car being controlled by someone has been identified as a good approach.

The group looked at relatively inexpensive and intuitive technologies. We had to get hardware to simulate three different entities in our problem:
  1. The controlling entity;
  2. The communication entity between the controlling entity and the entity to be controlled;
  3. The entity to be controlled.

For the first entity, smartphones were identified, as it would be an easy way to demonstrate several controlling entities, because smartphones are a fairly common technology today. For the second entity the Raspberry Pi was identified, since it is a computer about the size of a credit card, being versatile and cheap. Finally for the third entity was discovered GoPiGo, a robot with wheels prefabricated to support a Raspberry Pi. The discovery of the GoPiGo was an important catalyst to cement our solution, as it is relatively easy to demonstrate results when the third entity has real actions, in this case moving to where the user commands it.

All that remained was to identify how the technologies would be used together. After several brainstorming sessions and several iterations, the group came up with a system architecture:

!(https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image1.png)

Após a conceção da ideia base para o nosso projeto, faltava chegar a uma solução para poder demonstrar que o sistema era possível de implementar na realidade. Um simples problema em que existe um carro-robô a ser controlado por alguém foi identificado como uma boa aproximação ao problema.

O grupo olhou para tecnologias relativamente baratas e intuitivas. Foi necessário arranjar hardware para simular três diferentes entidades no nosso problema: 
  1. A entidade controladora;
  2. A entidade de comunicação entre a entidade controladora e a entidade a ser controlada;
  3. A entidade a ser controlada.

Para a primeira entidade foram identificados smartphones, uma vez que seria fácil demonstrar várias entidades controladoras, porque smartphones são uma tecnologia bastante comum hoje em dia. Para a segunda entidade foi identificado o Raspberry Pi, uma vez que é um computador de tamanho aproximado a um cartão de crédito, versátil e barato. Finalmente para a terceira entidade foi descoberto o GoPiGo, um robô com rodas pré-fabricado para suportar um Raspberry Pi. A descoberta do GoPiGo foi um importante catalisador para cimentar a nossa solução, uma vez que é relativamente fácil demonstrar resultados quando a terceira entidade faz ações reais, neste caso, movimentar-se para onde o utilizador o comandar.

Faltava apenas identificar como seriam usadas as tecnologias em conjunto. Após várias sessões de brainstorming e diversas iterações, o grupo chegou a uma arquitetura do sistema:

## Tecnologias

The group chose to use the **Amazon Web Services** (AWS) Cloud for this project, because of its speed, quality of service and technology convenience. As such, the services presented below correspond to the services provided by **AWS**.

### AWS IoT Core

**AWS IoT Core** is a managed cloud service that lets connected devices easily and securely interact with cloud applications and other devices. AWS IoT Core can support billions of devices and trillions of messages, and can process and route those messages to AWS endpoints and to other devices reliably and securely. With **AWS IoT Core**, your applications can keep track of and communicate with all your devices, all the time, even when they aren’t connected.

As part of our project, **IoT Core** was used to communicate between the *RaspEstatico* and the Cloud.

### AWS IoT Greengrass

**AWS IoT Greengrass** is a Cloud service that extends other AWS services to devices that are connected to the **IoT Greengrass Core** so that those services run locally on the connected devices.

In the context of this project Greengrass is responsible for the communication between the *RaspEstatico*, Cloud services used and the GoPiGo.

### AWS Lambda

AWS Lambda is a service that lets code run in the Cloud or in other devices when working with AWS IoT Greengrass.

In conjunction with **IoT Greengrass**, this service is used to run local code on the GoPiGo. 

## Cloud Pathway Explained

*RaspEstatico* is waiting for an event to be triggered by the blockchain network, when that happens, the *RaspEstatico* connects to the Cloud through **AWS IoT Core**, publishing a Message Queuing Telemetry Transport (MQTT) which contains the desired direction. Then, the **AWS IoT Greengrass** has a topic subscription for each direction and a corresponding **AWS Lambda** function, which runs locally in the GoPiGo to move it.

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image2.png)

**Note:** Details of how *RaspEstatico* MTQQ is sent can be found in the [*RaspEstatico* folder]: (https://github.com/l-silvestre/fikalab/tree/master/Cloud/RaspEstatico). Details of how **IoT Greengrass** was installed on GoPiGo in order to make it an IoT device and perform tasks locally can be found in the [GoPiGo folder]: (https://github.com/lsilvestre/fikalab/tree/master/Cloud/GoPiGo)
