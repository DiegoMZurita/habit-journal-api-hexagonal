# Spring Boot Hexagonal API

## 📌 Descripción
Este proyecto es una API REST desarrollada con **Spring Boot**, diseñada siguiendo el enfoque de **Arquitectura Hexagonal (Ports & Adapters)**. El objetivo principal es demostrar cómo construir aplicaciones backend desacopladas, mantenibles y fácilmente testeables, aplicando principios de diseño limpio y buenas prácticas modernas.

La arquitectura separa claramente el **dominio**, los **casos de uso** y las **infraestructuras externas**, permitiendo que el core de la aplicación no dependa de frameworks ni detalles técnicos.

## 🧱 Arquitectura Hexagonal
La estructura del proyecto está organizada en capas bien definidas:

- **Dominio**
  - Entidades
  - Interfaces de puertos (Ports)
  - Lógica de negocio pura

- **Aplicación**
  - Casos de uso
  - Orquestación de la lógica de negocio

- **Infraestructura**
  - Adaptadores de entrada (Controllers REST)
  - Adaptadores de salida (Persistencia, APIs externas)
  - Configuración de Spring
