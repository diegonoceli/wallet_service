# Wallet Service

## Overview

Welcome to the Wallet Service project! This service is designed to manage user funds, allowing for operations such as depositing, withdrawing, and transferring money between accounts. This project, while simplified, is modeled with care to reflect a realistic production environment.

## Objectives

The primary objective is to provide a robust service that can:

- Create Wallets for users.
- Retrieve the current balance of a wallet.
- Retrieve the historical balance at a specific point in time.
- Allow deposits and withdrawals.
- Facilitate transfers between wallets.

## Setup Instructions

Follow the steps below to get the Wallet Service up and running on your local machine.

### Prerequisites

Ensure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Java 21](https://jdk.java.net/21/)
- [Gradle](https://gradle.org/install/)

### Running the Service

**Start the Wallet Service:**

The complete environment, including the database and the Spring Boot application, can be started using Docker. Navigate to the project root directory and run the following command:

```bash
docker-compose up
```

This command will spin up all the necessary containers as specified in your `docker-compose.yml` file, making the application available at `http://localhost:8080`.

### Authorization Requirement

For accessing the API endpoints, make sure to include an `Authorization` header with a mocked token value:

- `Authorization: Bearer eyierei`

This mock token is required for all operations as part of the service's token-based authentication process.

### Postman Collection

Included in this project is a Postman collection, designed to facilitate testing of the API endpoints. This collection contains pre-configured requests for all major operations, including wallet creation, balance retrieval, fund deposits, withdrawals, and transfers. You can import this collection into Postman to instantly test and interact with the Wallet Service.

## Design Choices

### Functional Requirements

- **Create Wallet:** Implemented using a POST endpoint allowing users to register new wallets.
- **Retrieve Balance:** Two distinct endpoints handle both current and historical balance queries, ensuring users have flexibility in managing funds.
- **Deposit/Withdraw Funds:** These operations are critical for the transactional nature of the service, implemented with precision to maintain financial accuracy.
- **Transfer Funds:** Facilitates seamless money transfer between wallets, ensuring efficient inter-user transactions.

### Non-Functional Requirements

- **Availability:** Designed to be highly available, minimizing downtime and ensuring operational continuity.
- **Audit And Traceability:** Each transaction is logged to provide a comprehensive audit trail, essential for future audits and ensuring accountability.

### Technical Requirements

The service leverages the flexibility of RESTful APIs using Spring Boot for rapid development and scalability. The use of Java adds robustness and reliability.

## Compromises and Trade-offs

Given the project's time constraints, some advanced features and optimizations were minimized to meet core requirements efficiently. Future enhancements could include:

- Enhanced security measures beyond basic JWT authentication.
- More granular logging for finer traceability.
- Performance optimizations catered to high-load environments.

## Time Tracking

The project is estimated to require between 6 to 8 hours, balancing professional implementation with time efficiency.

---

Thank you for considering this implementation. Your insights and feedback are invaluable for the continuous enhancement of this service. Happy coding!