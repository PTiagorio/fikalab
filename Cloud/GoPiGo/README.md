# GoPiGo

In this section we specify all the necessary GoPiGo files and information for our project.

## Operatig System

The operating system used in GoPiGo was Rapbian for Robots, which can be seen an installation tutorial [here](https://www.dexterindustries.com/howto/install-raspbian-for-robots-image-on-an-sd-card/).

## Files

The files in this folder are responsible for GoPiGo's movement locally, each representing a different direction (in the case of left and right it simply turns).

## GoPiGo in IoT Greengrass

In order to run code on GoPiGo as it were locally to control it and form an IoT, GoPiGo needs to have **AWS IoT Greengrass Core Software** installed and running. For this project we used Greengrass Core Software v1.9.3 for the Armv7l architecture. More information on this can be found [here](https://docs.aws.amazon.com/greengrass/latest/developerguide/what-is-gg.html).

### Greengrass Instalation

Nós instalámos o software seguindo os seguintes passos no Raspberry Pi:

1. Adicionar grupos e users
```
sudo adduser --system ggc_user
sudo addgroup --system ggc_group

```

1. Aumentar a segurança
```
sudo nano 98-rpi.conf **(ou similar) e escrever no final do artigo:**
fs.protected_hardlinks = 1
fs.protected_symlinks = 1

```
