# Real-Time Show Booking System — Low Level Design (LLD)

## Overview

A production-style **Low Level Design (LLD)** implementation of a **Real-Time Show Booking System** built in Java.

This project models how real-world ticketing platforms handle:

- Seat locking
- Payment processing
- Concurrency control
- Booking lifecycle management
- Extensible payment strategies

The system follows clean architecture principles and demonstrates how to design a scalable, maintainable backend system using object-oriented design.

---

## Core Features

### Admin Capabilities
- Add movies
- Add theatres and associate with cities
- Add screens with dynamic seat generation using `SeatFactory`
- Create shows with time and pricing

### User Capabilities
- Search shows by movie name
- Select multiple seats
- Create booking (with seat locking)
- Process payment via multiple modes
- Confirm booking on payment success
- Automatically release seats on payment failure

---

## Real-Time Booking Workflow

The system strictly follows an industry-standard booking lifecycle:

`LOCK → PAY → BOOK → CONFIRM`

1. User selects seats
2. Seats are temporarily **LOCKED**
3. Payment is processed via `PaymentService`
4. On success:
   - Seats are marked as **BOOKED**
   - Booking status becomes **CONFIRMED**
5. On failure:
   - Locks are released
   - Booking status becomes **FAILED**

This prevents race conditions and double booking scenarios.

---

## Concurrency Handling

### SeatLockManager (Singleton)

- Centralized lock management per show
- Thread-safe using `ConcurrentHashMap`
- Lock expiration (TTL-based cleanup)
- Temporary lock state separated from permanent booking state

### Seat State Lifecycle

- `AVAILABLE`
- `LOCKED` (temporary, managed by SeatLockManager)
- `BOOKED` (permanent, managed by Show)

---

## Payment Architecture

Payment is modeled as a **separate domain entity** with its own lifecycle.

### Components

- `Payment` entity
- `PaymentRepository`
- `PaymentService`
- `PaymentStrategy` (Strategy Pattern)
- `PaymentFactory` (Factory Pattern)

### Payment Status

- `PENDING`
- `SUCCESS`
- `FAILED`

### Booking Status

- `PAYMENT_PENDING`
- `CONFIRMED`
- `FAILED`

Payment processing flow:

1. BookingService calls PaymentService
2. PaymentService uses selected PaymentStrategy
3. Payment is saved in PaymentRepository
4. Booking status updated based on payment result

This cleanly separates **financial transaction logic** from booking logic.

---

## Design Patterns Used

### Strategy Pattern
Used for payment processing:
- Credit Card
- UPI
- Wallet

New payment methods can be added without modifying BookingService.

### Factory Pattern
Used for:
- Creating PaymentStrategy implementations (`PaymentFactory`)
- Generating seats dynamically (`SeatFactory`)

### Singleton Pattern
Used for:
- `SeatLockManager` to maintain a globally consistent lock state

---

## Architecture

### Layered Architecture

Controller → Service → Repository → Entity

- **Controller**: Handles input and delegates to services
- **Service**: Contains business logic
- **Repository**: In-memory storage layer
- **Entity**: Domain models with encapsulated state transitions

This ensures:
- High cohesion
- Low coupling
- Clear separation of responsibilities
- Testability and extensibility

---

## Domain Modeling Highlights

- `Screen` defines seat layout
- `Show` maintains seat availability state
- `SeatLockManager` manages temporary locks
- `Booking` stores seatIds, user, and status
- `Payment` is an independent aggregate with transaction tracking

Business invariants are enforced at service and entity level.

---

## Tech Stack

- Java
- Object-Oriented Programming (OOP)
- SOLID Principles
- Strategy Pattern
- Factory Pattern
- Singleton Pattern
- In-memory data structures
- Thread-safe collections (`ConcurrentHashMap`)
- Concurrency-safe booking flow

---

## How to Run

Run the `Main` class to simulate:

1. Admin setup (movie, theatre, screen, show)
2. User searches for show
3. User selects seats
4. Booking is created and seats are locked
5. Payment is processed using chosen mode
6. Booking is confirmed or failed

---

## Why This Project

This project is designed as a serious **Low Level Design (LLD)** exercise suitable for:

- Java backend interviews
- Low-Level System Design rounds
- Concurrency discussions
- Design pattern evaluation
- Demonstrating clean architecture and domain-driven modeling

It goes beyond CRUD operations and focuses on:

- Seat-level concurrency control
- Lock expiration management
- Separation of booking and payment domains
- Extensible and maintainable architecture
- Real-world state transitions

---

## Learning Outcomes

By building this system, you understand:

- How booking platforms prevent double booking
- How to manage temporary locks with TTL
- How to separate financial transactions from booking logic
- How to apply Strategy, Factory, and Singleton patterns correctly
- How to design scalable object-oriented systems
