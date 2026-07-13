# DevOps CI/CD Pipeline Architecture: MyCalculator

[![Java 17](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A22.svg)](https://maven.apache.org/)
[![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins-D33833.svg)](https://www.jenkins.io/)
[![Docker](https://img.shields.io/badge/Container-Docker-2496ED.svg)](https://www.docker.com/)

> **Development Context:** This project serves as a comprehensive portfolio piece demonstrating modern DevOps capabilities, Continuous Integration / Continuous Deployment (CI/CD) pipelines, and Cloud-Native containerization architectures. It was engineered during an intensive DevOps automation workshop.

## 📖 Overview

While the underlying application is a standard Java Maven project, the true focus of this repository is the **infrastructure and automation wrapper**. The project implements a robust, multi-stage CI/CD pipeline capable of validating, testing, scanning, and containerizing application code automatically upon commit.

## ✨ Pipeline Architecture

This repository showcases mastery of modern build and release pipelines across two different automation platforms:

### 1. Jenkins (Declarative Pipeline)
The `Jenkinsfile` defines a strict, stage-gated declarative pipeline:
- **Build**: Compiles the source code using the Maven toolchain.
- **Test**: Executes automated unit tests and aggregates results via JUnit.
- **SonarQube Quality Gate**: Performs static application security testing (SAST) and code quality analysis.
- **Containerization**: Dynamically builds the Docker image tagging it with the Jenkins `BUILD_ID`.

### 2. GitHub Actions
A cloud-native `.github/workflows/ci-cd.yml` workflow is implemented to provide immediate validation on pull requests and commits to the `main` branch, running parallel Maven builds and automated Docker image compilation in Ubuntu runner environments.

## 🐳 Containerization Strategy

The application relies on a **Multi-Stage Dockerfile** to optimize image size and security:
- **Stage 1 (Builder)**: Utilizes a heavy `maven:eclipse-temurin` image to download dependencies and compile the `.jar` artifact.
- **Stage 2 (Runtime)**: Utilizes a lightweight Alpine JRE image to actually run the application. This drastically reduces the attack surface and deployment footprint.

## 🚀 Local Setup

If you wish to build the application locally without the automated pipelines:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rakesh-pathuri/MyCalculator.git
   cd MyCalculator
   ```

2. **Build with Maven:**
   ```bash
   mvn clean package
   ```

3. **Build with Docker:**
   ```bash
   docker build -t mycalculator .
   ```

---

### Authorship
**DevOps Engineer:** Rakesh Pathuri
*Engineered to demonstrate enterprise-grade CI/CD and automation paradigms.*
