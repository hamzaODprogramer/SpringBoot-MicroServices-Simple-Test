# Spring Boot Microservices Project

Projet d'architecture microservices basé sur Spring Boot et Spring Cloud.

## Architecture

Le projet comprend les microservices suivants :

| Service | Description | Port |
|---------|-------------|------|
| **Config-Server** | Serveur de configuration centralisée | 8888 |
| **Discovery** | Service de découverte (Eureka) | 8761 |
| **Gateway** | Passerelle API (Spring Cloud Gateway) | 8080 |
| **Student** | Microservice pour la gestion des étudiants | 8081 |
| **School** | Microservice pour la gestion des écoles | 8082 |

## Technologies

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Eureka](https://img.shields.io/badge/Netflix%20Eureka-4B9CD3?style=for-the-badge&logo=netflix&logoColor=white)
![Spring Cloud Gateway](https://img.shields.io/badge/Gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Zipkin](https://img.shields.io/badge/Zipkin-6DB33F?style=for-the-badge&logo=zipkin&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BA4A00?style=for-the-badge&logo=lombok&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

- **Java** 21
- **Spring Boot** 3.4.0 / 4.0.5
- **Spring Cloud** 2024.0.2 / 2025.1.1
- **MySQL** - Base de données relationnelle
- **Netflix Eureka** - Service Discovery
- **Spring Cloud Config** - Configuration centralisée
- **Spring Cloud Gateway** - API Gateway
- **Zipkin** - Traçage distribué
- **Micrometer Brave** - Observabilité
- **Lombok** - Réduction du code boilerplate
- **Spring Actuator** - Monitoring

## Prérequis

- JDK 21
- Maven
- MySQL (ou conteneur Docker)

## Démarrage

Lancer les services dans l'ordre suivant :

1. **Config-Server**
2. **Discovery (Eureka)**
3. **Gateway**
4. **Student**
5. **School**

Pour Zipkin (tracing distribué) :
```bash
docker-compose up -d
```

## Configuration

Les fichiers de configuration sont gérés via Spring Cloud Config Server.

## Monitoring

- **Actuator** : `/actuator`
- **Eureka Dashboard** : `http://localhost:8761`
- **Zipkin** : `http://localhost:9411`
