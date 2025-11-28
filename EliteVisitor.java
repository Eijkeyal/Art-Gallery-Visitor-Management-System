 

public class EliteVisitor extends ArtGalleryVisitor{

    // Unique attributes for EliteVisitor
    private boolean assignedPersonalArtAdvisor;
    private boolean exclusiveEventAccess;

    // Constructor
    public EliteVisitor(int visitorId, String fullName, String gender, String contactNumber, 
                        String registrationDate, double ticketCost, String ticketType) {
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost, 
        ticketType);
        this.assignedPersonalArtAdvisor = false;
        this.exclusiveEventAccess = false;
    }

    // Accessor methods
    public boolean getAssignedPersonalArtAdvisor() {
    return assignedPersonalArtAdvisor;
    }

    public boolean getExclusiveEventAccess() {
    return exclusiveEventAccess;
    }

    // Method to assign a personal art advisor
    public boolean assignPersonalArtAdvisor() {
    if (rewardPoints > 5000) {
        this.assignedPersonalArtAdvisor = true;
    }
    return this.assignedPersonalArtAdvisor;
    }

    // Method to check exclusive event access
    public boolean checkExclusiveEventAccess() {
    if (assignedPersonalArtAdvisor) {
        this.exclusiveEventAccess = true;
    }
    return this.exclusiveEventAccess;
    }

    // Overriding buyProduct method
    @Override
    public String buyProduct(String artworkName, double artworkPrice) {
        if (!isActive) {
            return "Please log in before making a purchase.";
        }
        if (this.artworkName == null || !this.artworkName.equals(artworkName)) {
            this.artworkName = artworkName;
            this.artworkPrice = artworkPrice;
            this.isBought = true;
            this.buyCount++;
            return "Purchase successful for artwork: " + artworkName;
        }
        return "This artwork has already been purchased.";
    }

    // Method to calculate discount
    @Override
     public double calculateDiscount() {
    double discountAmount = artworkPrice * 0.40; // Calculate 40% discount
    double finalPrice = artworkPrice - discountAmount; // Calculate final price after discount
    return finalPrice; // Return the discounted price
    }
    // Method to calculate reward points
    @Override
    public double calculateRewardPoint() {
        if (isBought) {
        double finalPrice = calculateDiscount();//Get discounted price from calculateDiscount()
        rewardPoints += finalPrice * 10;//add reward points(10 points per rupee)
        }
        return rewardPoints;
    }
    // Overriding generateBill method
    @Override
    public void generateBill() {
        if (!isBought) {
            System.out.println("No purchase to generate bill.");
        } else {
            double discountAmount = artworkPrice * 0.40;
            double finalPrice = artworkPrice - discountAmount;
            System.out.println("Visitor ID: " + visitorId);
            System.out.println("Visitor Name: " + fullName);
            System.out.println("Artwork Name: " + artworkName);
            System.out.println("Artwork Price: " + artworkPrice);
            System.out.println("Discount Amount: " + discountAmount);
            System.out.println("Final Price: " + finalPrice);
        }
    }

    // Method to terminate visitor
    private void terminateVisitor() {
        isActive = false;
        assignedPersonalArtAdvisor = false;
        exclusiveEventAccess = false;
        visitCount = 0;
        cancelCount = 0;
        rewardPoints = 0;
    }

    // Method to cancel product
    @Override
    public String cancelProduct(String artworkName, String cancellationReason) {
    // Check if cancellation limit reached
    if (cancelCount >= 3) {
        terminateVisitor();  // Terminate account
        return "Your account has been terminated due to excessive cancellations.";
    }

    // Check if any product has been purchased
    if (buyCount == 0) {
        return "No product to cancel.";
    }

    // Check if artwork name matches
    if (this.artworkName != null && this.artworkName.equals(artworkName)) {
        // Calculate refundable amount after 5% cancellation fee
        double refundableAmount = artworkPrice - (artworkPrice * 0.05);

        // Deduct reward points based on discounted final price
        double finalPrice = calculateDiscount(); // reuse discount calculation
        rewardPoints -= finalPrice * 10;

        // Reset purchase info
        this.artworkName = null;
        isBought = false;

        // Update cancellation and purchase counters
        cancelCount++;
        buyCount--;
        return "Cancellation successful. Refund amount: " + refundableAmount +
               ". Reason: " + cancellationReason;
    }

    // If artwork names don't match
    return "Incorrect artwork name.";
    }
    // Overriding display method
    @Override
    public void display() {
        super.display();
        System.out.println("Assigned Personal Art Advisor: " + assignedPersonalArtAdvisor);
        System.out.println("Exclusive Event Access: " + exclusiveEventAccess);
    }
}
