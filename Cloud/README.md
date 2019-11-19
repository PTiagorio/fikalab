# Cloud

This section explains, with due technical details, all Cloud services used under this project.

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

Neste projeto este serviço foi utilizado para, juntamente com o **IoT Greengrass**, correr código no GoPiGo localmente.

## Cloud Pathway Explained

The *RaspEstatico* is waiting for an event to be triggered by the blockchain network, when the event gets triggered, the *RaspEstatico* connects to the Cloud through **AWS IoT Core**, publishing a Message Queuing Telemetry Transport (MQTT) which contains the desired direction. Then, the **AWS IoT Greengrass** has a topic subscription for each direction and a corresponding **AWS Lambda** function, which runs locally in the GoPiGo to move it.

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image2.png)

**Nota:** Detalhes de como foi enviado o MTQQ do Raspberry Pi 3B+ estático e como instalado o Greengrass no GoPiGo de forma ao mesmo se tornar um dispositivo de IoT e conseguir correr funções localmente podem ser encontrados na [pasta dos Raspberry Pi 3B+](http://www.dropwizard.io/1.0.2/docs/) deste projeto.

**Note:** Details of how *RaspEstatico* MTQQ was sent and how **IoT Greengrass** was installed on GoPiGo in order to make it an IoT device and perform tasks locally can be found in the [*RaspEstatico* folder] (http: // www.dropwizard.io/1.0.2/docs/) of this project.


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
