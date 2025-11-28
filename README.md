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
  
## 🏗️ System Architecture
ArtGalleryVisitor (Abstract Class)
├── StandardVisitor (Concrete Class)
└── EliteVisitor (Concrete Class)
