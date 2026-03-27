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
