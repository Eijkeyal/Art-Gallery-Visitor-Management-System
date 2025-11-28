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

## 🎮 Usage Examples

### Creating and Managing a Standard Visitor

```java
// Create a Standard Visitor
StandardVisitor visitor = new StandardVisitor(101, "John Doe", "Male", 
                                             "1234567890", "2024-01-15", 
                                             1000.0, "Standard");

// Log visits and make purchases
visitor.logVisit();
String result = visitor.buyProduct("Starry Night", 5000.0);
visitor.generateBill();

// Handle cancellations
String cancelResult = visitor.cancelProduct("Starry Night", "Change of mind");

// Create an Elite Visitor
EliteVisitor eliteVisitor = new EliteVisitor(201, "Jane Smith", "Female",
                                            "9876543210", "2024-01-20",
                                            2000.0, "Elite");

// Log multiple visits and purchases
eliteVisitor.logVisit();
eliteVisitor.logVisit();
String purchaseResult = eliteVisitor.buyProduct("Mona Lisa", 10000.0);
eliteVisitor.generateBill();

// Check for elite benefits
boolean hasAdvisor = eliteVisitor.assignPersonalArtAdvisor();
boolean hasEventAccess = eliteVisitor.exclusiveEventAccess();
// 1. Create visitor
StandardVisitor visitor = new StandardVisitor(101, "John Doe", "Male", 
                                             "1234567890", "2024-01-15", 
                                             1000.0, "Standard");

// 2. Log multiple visits to qualify for discount upgrade
visitor.logVisit();
visitor.logVisit();
visitor.logVisit();
visitor.logVisit();
visitor.logVisit(); // 5th visit - qualifies for discount upgrade

// 3. Check and apply discount upgrade
boolean isUpgraded = visitor.checkDiscountUpgrade();

// 4. Purchase artwork with upgraded discount
String purchaseResult = visitor.buyProduct("The Persistence of Memory", 8000.0);

// Writing visitor data to file
try {
    FileWriter writer = new FileWriter("visitors.txt", true);
    writer.write(visitor.getVisitorId() + "," + visitor.getFullName() + "," + 
                 visitor.getTicketType() + "," + visitor.getRewardPoints() + "\n");
    writer.close();
} catch (IOException e) {
    System.out.println("Error writing to file: " + e.getMessage());
}
// 5. Calculate benefits
double discount = visitor.calculateDiscount();
double rewardPoints = visitor.calculateRewardPoint();

// 6. Generate bill
visitor.generateBill();

// 7. Display visitor details
visitor.display();

Output Examples
Successful Purchase:

Purchase successful: Starry Night for 5000.0
Discount Applied: 500.0
Final Price: 4500.0
Reward Points Earned: 22500.0
Cancellation Output:

Cancellation successful for: Starry Night
Refundable Amount: 4500.0 (after 10% cancellation fee)
Reason: Change of mind

This markdown format provides:
- Clear code blocks with proper syntax highlighting
- Multiple practical examples
- Complete workflow demonstration
- Expected output examples
- Easy copy-paste functionality for users

```

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
git clone https://github.com/Eijkeyal/Art-Gallery-Visitor-Management-System.git
