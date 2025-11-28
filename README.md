# Art Gallery Visitor Management System

A comprehensive Java desktop application for managing art gallery operations, including visitor management, artwork sales, and reward systems. Built with Java Swing and Object-Oriented Programming principles.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object_Oriented-ED8B00?style=for-the-badge)

## 🚀 Features

### Visitor Management
- **Tiered Membership**: Standard (₹1000) and Elite (₹2000) passes
- **Visitor Registration**: Complete profile management
- **Visit Tracking**: Automated visit logging and activity monitoring
- **Account Management**: Active/inactive status with termination rules

### Artwork Operations
- **Purchase System**: Complete artwork buying workflow
- **Dynamic Discounts**: 10%-15% (Standard) and 40% (Elite) discounts
- **Cancellation Management**: Tier-specific cancellation fees (10% Standard, 5% Elite)
- **Bill Generation**: Automated receipt generation

### Rewards System
- **Reward Points**: 5x points/rupee (Standard), 10x points/rupee (Elite)
- **Loyalty Benefits**: Discount upgrades for frequent Standard visitors (after 5 visits)
- **Elite Privileges**: Personal art advisors (5000+ points) and exclusive event access

### Data Management
- **File I/O**: Persistent data storage using text files
- **Data Recovery**: Session persistence and data backup

  // Create and manage a Standard Visitor
StandardVisitor visitor = new StandardVisitor(101, "John Doe", "Male", 
                                             "1234567890", "2024-01-15", 
                                             1000.0, "Standard");
// Log visits and make purchases
visitor.logVisit();
String result = visitor.buyProduct("Starry Night", 5000.0);
visitor.generateBill();

// Handle cancellations
String cancelResult = visitor.cancelProduct("Starry Night", "Change of mind");

## 🏗️ System Architecture
ArtGalleryVisitor (Abstract Class)<br>
├── StandardVisitor (Concrete Class)<br>
└── EliteVisitor (Concrete Class)

### Core Classes

- **ArtGalleryVisitor**: Abstract base class with common attributes and methods
- **StandardVisitor**: Standard membership with basic benefits and upgrade options
- **EliteVisitor**: Premium membership with exclusive privileges

## 💻 Technologies Used

- **Java SE** - Core programming language
- **Java Swing** - Graphical User Interface
- **Object-Oriented Programming** - Inheritance, Polymorphism, Abstraction, Encapsulation
- **File I/O** - Data persistence management
- **Exception Handling** - Robust error management
## 📥 Installation & Setup

### Prerequisites
- Java JDK 8 or higher
- Git (for cloning repository)

### Installation Steps
1. Clone the repository:
```bash
git clone https://github.com/yourusername/art-gallery-management.git
