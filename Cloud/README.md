# Cloud

Nesta secção são explicados, com os devidos detalhes técnicos, todas os serviços da Cloud utilizados no âmito deste projeto.

## Tecnologias

O grupo optou por utilizar a Cloud da **Amazon Web Services** (AWS) neste projeto, por motivos de rapidez, qualidade dos serviços e comodidade com a tecnologia. Como tal, os serviços apresentados a seguir correspondem aos serviços prestados pela **AWS**.

### AWS IoT Core

AWS IoT Core is a managed cloud service that lets connected devices easily and securely interact with cloud applications and other devices. AWS IoT Core can support billions of devices and trillions of messages, and can process and route those messages to AWS endpoints and to other devices reliably and securely. With AWS IoT Core, your applications can keep track of and communicate with all your devices, all the time, even when they aren’t connected.

No âmbito do nosso projeto, o IoT Core foi utilizado para realizar a comunicação entre o Rasberry Pi 3B+ estático e a Cloud.

### AWS IoT Greengrass

AWS IoT Greengrass é um serviço Cloud que extende outros serviços da AWS a dispositivos que estejam conectados ao IoT Greengrass Core, de forma a que os mesmos serviços sejam executados localmente nesses mesmos dispositivos.

No contexto deste projeto o Greengrass é o que realiza a comunicação entre o Raspberry Pi 3B+ estático, serviços utilizados da Cloud e o GoPiGo.

### AWS Lambda

AWS Lambda is a service that lets run code in Cloud or in other devices when working with AWS IoT Greengrass.

## Cloud Pathway Explained

The Raspberry Pi 3B+ is listening decisions taken in the blockchain, and after get some results it connects to the Cloud through **AWS IoT Core**, publishing an Message Queuing Telemetry Transport (MQTT) message that tells which direction is supposed to make. Then, the **AWS IoT Greengrass** subscribes that topic to the corresponding **AWS Lambda** function, that runs locally in the GoPiGo the function needed to move the same GoPiGo.

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image2.png)

**Nota:** Detalhes de como foi enviado o MTQQ do Raspberry Pi 3B+ estático e como instalado o Greengrass no GoPiGo de forma ao mesmo se tornar um dispositivo de IoT e conseguir correr funções localmente podem ser encontrados na [pasta dos Raspberry Pi 3B+](http://www.dropwizard.io/1.0.2/docs/) neste projeto.




### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc


```
until finished
```
