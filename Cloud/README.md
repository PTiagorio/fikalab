# Cloud

This section explains, with due technical details, all Cloud services used under this project.

## Technologies

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

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image10.png)
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image2.png)

![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image3.png)
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image4.png)
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image5.png)
![Cloud Pathway](https://github.com/l-silvestre/fikalab/blob/master/Cloud/Images/image6.png)

**NOTE:** Details of how *RaspEstatico* MTQQ is sent can be found in the [*RaspEstatico* folder](https://github.com/l-silvestre/fikalab/tree/master/Cloud/RaspEstatico). Details of how **IoT Greengrass** was installed on GoPiGo in order to make it an IoT device and perform tasks locally can be found in the [GoPiGo folder](https://github.com/lsilvestre/fikalab/tree/master/Cloud/GoPiGo).

## Folder Structure

* **Images** - in this directory we put all the images used to explain the Cloud architecture
* **Lambda** - directory where we put additional information about the project Lambdas and their codes
* **RaspEstatico** - in this directory where we talked more about raspEstatico and insert its code from the Cloud
* **GoPiGo** - directory that has more information and files about GoPiGo
