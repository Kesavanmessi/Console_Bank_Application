# Console Bank Application

## 📌 Project Overview
A robust, production-quality Java console application designed to simulate core banking operations. This project demonstrates advanced Java concepts including **concurrency**, **stream processing**, **custom exception handling**, and **SOLID principles** within a clean, package-based architecture.

## 🚀 Key Concepts Demonstrated

### 1. Object-Oriented Design (OOD)
- **Encapsulation**: Models (`Account`) protect their state.
- **Abstraction**: `BankService` abstracts business logic from the main application.
- **Inheritance**: Custom exception hierarchy (`BankException` -> `InsufficientBalanceException`).

### 2. Concurrency & Thread Safety
- **Problem**: In a real banking system, multiple transaction channels (ATM, Mobile, Web) access the same account simultaneously. Without synchronization, this leads to **Race Conditions** (e.g., lost updates).
- **Solution**:
  - `BankService` methods (`deposit`, `withdraw`) are marked `synchronized`.
  - This ensures that only one thread can modify an account's balance at a time, maintaining **Atomicity**.
  - **Simulation**: The app spawns multiple threads using `TransactionTask` (implements `Runnable`) to perform simultaneous deposits and withdrawals, proving the system's data integrity.

### 3. Java Streams API
- Utilized for efficient data processing and reporting:
  - **Filtering**: Finding accounts with balances above a threshold.
  - **Mapping & Reduction**: Calculating total bank liquidity (`mapToDouble`, `sum`).
  - **Sorting**: generating sorted reports dynamically.

### 4. Robust Error Handling
- **Custom Exceptions**: Defined a clear hierarchy with `InsufficientBalanceException` and `InvalidAmountException`.
- **Flow Control**: Demonstrates correct usage of `throw` vs `throws`.
- **Best Practices**: Specific catch blocks take precedence over generic ones; `finally` blocks used for cleanup.

### 5. Collections & Comparators
- Usage of `Map` for O(1) account lookups.
- Custom `Comparator` implementations in `AccountComparators` to sort accounts by Name or Balance (Descending).

## 🛠 Project Structure
```
src/main/java/com/kesavan/bank
├── app         # Main entry point and orchestration
├── exception   # Custom error handling hierarchy
├── model       # Domain entities (Account, User)
├── service     # Business logic and thread-safe operations
└── util        # Helpers and Comparators
```

## 💻 How to Run

### Prerequisites
- JDK 11 or higher.

### Steps
1. Navigate to the project root:
   ```bash
   cd Bank_Application
   ```
2. Compile the project:
   ```bash
   javac -d bin src/main/java/com/kesavan/bank/**/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin com.kesavan.bank.app.BankApplication
   ```

## 📋 Sample Output
```text
Starting Bank Application...
Creating account: ACC-001
Initial Balance: 1000.0
Starting concurrent transactions...
Thread Deposit-Thread-0 deposited 100.0 to ACC-001
Thread Deposit-Thread-2 deposited 100.0 to ACC-001
Thread Withdraw-Thread-0 withdrew 50.0 from ACC-001
...
All transactions completed.
Final Balance: 1250.0

--- Reporting Demo ---
Accounts sorted by Holder Name:
Account{accountNumber='ACC-002', holderName='Alice', balance=5000.0}
Account{accountNumber='ACC-003', holderName='Bob', balance=200.0}
Account{accountNumber='ACC-001', holderName='Kesavan', balance=1250.0}

Accounts sorted by Balance (Desc):
Account{accountNumber='ACC-002', holderName='Alice', balance=5000.0}
Account{accountNumber='ACC-001', holderName='Kesavan', balance=1250.0}
Account{accountNumber='ACC-003', holderName='Bob', balance=200.0}

[Finally] Application cleanup/shutdown sequence completed.
```
