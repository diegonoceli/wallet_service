
# Wallet Service

## Overview

Welcome to the Wallet Service project! This service is designed to manage user funds, allowing operations such as depositing, withdrawing, and transferring money between accounts. While simplified, this project is modeled with care to reflect a realistic production environment.

---

## Objectives

The Wallet Service provides the following functionalities:

- Create wallets for users.
- Retrieve the current balance of a wallet.
- Retrieve the historical balance at a specific point in time.
- Allow deposits and withdrawals.
- Facilitate transfers between wallets.

---

## Setup Instructions

### Prerequisites

Ensure you have the following installed:

- Docker
- Java 21
- Gradle

### Running the Service

To start the Wallet Service, navigate to the project root directory and run the following command:

```bash
docker-compose up
```

This command spins up all necessary containers, including the database and the Spring Boot application, making the application available at `http://localhost:8080`.

### Authorization Requirement

For accessing the API endpoints, include an Authorization header with a mocked token value:

```
Authorization: Bearer eyierei
```

This mock token is required for all operations as part of the service's token-based authentication process.

---

## Endpoints

### 1. Create Wallet

- **URL:** `POST /wallets`
- **Headers:**
    - `Content-Type: application/json`
    - `Authorization: Bearer eyierei`
- **Request Body:**
  ```json
  {
      "name": "Diego",
      "surname": "Noceli",
      "documentNumber": "1234567890"
  }
  ```
- **Description:** Creates a new wallet for a user.

---

### 2. Deposit Funds

- **URL:** `POST /wallets/:wallet_id/deposit`
- **Headers:**
    - `Content-Type: application/json`
    - `Authorization: Bearer eyierei`
- **Path Parameter:**
    - `wallet_id` (e.g., `usr1`)
- **Request Body:**
  ```json
  {
      "depositAmount": 20000.00
  }
  ```
- **Description:** Deposits a specified amount into the user's wallet.

---

### 3. Retrieve Balance

- **URL:** `GET /wallets/:wallet_id/balance`
- **Headers:**
    - `Authorization: Bearer eyierei`
- **Path Parameter:**
    - `wallet_id` (e.g., `usr1`)
- **Description:** Retrieves the current balance of the specified wallet.

---

### 4. Transfer Funds

- **URL:** `POST /wallets/transfer`
- **Headers:**
    - `Content-Type: application/json`
    - `Authorization: Bearer eyierei`
- **Request Body:**
  ```json
  {
      "senderWalletId": "usr1",
      "receiverWalletId": "usr0",
      "amount": 15000.00
  }
  ```
- **Description:** Transfers a specified amount from one wallet to another.

---

### 5. Withdraw Funds

- **URL:** `POST /wallets/:wallet_id/withdraw`
- **Headers:**
    - `Content-Type: application/json`
    - `Authorization: Bearer eyierei`
- **Path Parameter:**
    - `wallet_id` (e.g., `usr1`)
- **Request Body:**
  ```json
  {
      "withdrawalAmount": 100.00
  }
  ```
- **Description:** Withdraws a specified amount from the user's wallet.

---

## Design Choices

### Functional Requirements

- **Create Wallet:** Implements a POST endpoint for user registration.
- **Retrieve Balance:** Separate endpoints for current and historical balances ensure flexibility.
- **Deposit/Withdraw Funds:** Critical for managing transactional operations accurately.
- **Transfer Funds:** Facilitates efficient inter-wallet transactions.

### Non-Functional Requirements

- **Availability:** Highly available and minimizes downtime.
- **Audit and Traceability:** Comprehensive logging for a detailed audit trail.
- **Scalability:** Built to handle increasing load with ease.

---

## Postman Collection

The project includes a Postman collection for quick testing and interaction with the Wallet Service. Import the collection into Postman to test all major operations, including wallet creation, balance retrieval, deposits, withdrawals, and transfers.

---

## Time Tracking

The project is estimated to take between 6 to 8 hours, balancing professional implementation with time efficiency.

---

Thank you for considering this implementation. Your feedback is invaluable for the continuous improvement of this service. Happy coding!
