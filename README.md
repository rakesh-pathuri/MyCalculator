# DevOps CI/CD Pipeline

[![Java 17](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A22.svg)](https://maven.apache.org/)
[![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins-D33833.svg)](https://www.jenkins.io/)
[![Docker](https://img.shields.io/badge/Container-Docker-2496ED.svg)](https://www.docker.com/)

> **Development Context:** This project was developed during a DevOps automation workshop to test and demonstrate my skills in CI/CD and automation.

## About the Project

While the underlying application is a simple Java Calculator built with Maven, the main focus of this repository is the automation infrastructure. It implements a complete CI/CD (Continuous Integration / Continuous Deployment) pipeline that automatically builds, tests, scans, and containerizes the code.

## Pipeline Architecture

This repository uses two different automation platforms to show how the code moves from development to deployment.

### 1. Jenkins
The project includes a `Jenkinsfile` that defines the following stages:
- **Build**: Compiles the Java code using Maven.
- **Test**: Runs automated unit tests to ensure the code works.
- **Code Quality**: Uses SonarQube to scan the code for bugs and security issues.
- **Docker Build**: Packages the application into a Docker container.

### 2. GitHub Actions
There is also a GitHub Actions workflow (`.github/workflows/ci-cd.yml`). When new code is pushed to the repository, GitHub automatically builds and tests the Java application in the cloud.

## Docker Containerization

The application is packaged using a multi-stage Dockerfile:
- **Stage 1 (Builder)**: Uses a Maven image to download dependencies and build the Java application.
- **Stage 2 (Runtime)**: Copies the built application into a much smaller, lightweight Java runtime image (Alpine). This keeps the final container size small and secure.

## How to Run Locally

If you want to build and run the application manually on your own computer:

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
**Developed by:** Rakesh Pathuri
*Built to demonstrate CI/CD pipelines, Docker containerization, and DevOps automation.*
