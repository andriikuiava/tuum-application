# API Endpoints

## Accounts

### Create Account
- **URL:** `POST http://localhost:8080/accounts/create`
- **Body:**
  ```json
  {
    "country": "US",
    "customerId": 1,
    "currencies": ["SEK"]
  }

### Retrieve Account
- **URL:** `GET http://localhost:8080/accounts/{account_id}`
- **Parameters:**
    - `account_id`: ID of the account to retrieve

## Transactions

### Create Transaction
- **URL:** `POST http://localhost:8080/transactions/create`
- **Body:**
  ```json
  {
    "account_id": 47,
    "currency": "EUR",
    "amount": 0.01,
    "direction": "IN",
    "description": "Payment for services"
  }
  ```

### Retrieve Transaction
- **URL:** `GET http://localhost:8080/transactions/{transaction_id}`
- **Parameters:**
    - `transaction_id`: ID of the transaction to retrieve
