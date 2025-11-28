 package Coursework;

public class StandardVisitor extends ArtGalleryVisitor {
    // Additional attributes
    private boolean upgradeDiscount;
    private final int visitLimit;
    private float discountPercent;

    // Constructor
    public StandardVisitor(int visitorId, String fullName, String gender, String contactNumber,
                           String registrationDate, double ticketCost, String ticketType) {
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost,
        ticketType);
        this.visitLimit = 5; // Set default visit limit
        this.discountPercent = 0.10f; // Default discount percent
        this.upgradeDiscount= false; // Default eligibility
    }

    // Accessor methods
    public boolean getUpgradeDiscount() {
        return upgradeDiscount;
    }

    public int getVisitLimit() {
        return visitLimit;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }
    // buyProduct method
    @Override
    public String buyProduct(String artworkName, double artworkPrice) {
    if (!isActive) {
        return "Please log in before making a purchase.";
    }
    if (this.artworkName != null && this.artworkName.equals(artworkName)) {
        return "You have already purchased the artwork '" + artworkName + "'.";
    }
    this.artworkName = artworkName;
    this.artworkPrice = artworkPrice;
    this.isBought = true;
    this.buyCount++;
    return "Artwork '" + artworkName + "' purchased successfully" + artworkPrice + ".";
    }
       public boolean checkDiscountUpgrade() {
        if (visitCount >= visitLimit) {
            upgradeDiscount = true;
            discountPercent = 0.15f;
        } else {
            upgradeDiscount = false;
            discountPercent = 0.10f;
        }
        return upgradeDiscount;
    }
    
    @Override
    public double calculateDiscount() {
        if (!isBought) {
            discountAmount = 0;
            finalPrice=artworkPrice;
            return discountAmount;
        }

        checkDiscountUpgrade();  // Call the method here

        discountAmount = artworkPrice * discountPercent;
        finalPrice = artworkPrice - discountAmount;

        return discountAmount;
    }
    
    @Override
    public double calculateRewardPoint() {
    if (!isBought) {
        // No product bought, so no new reward points earned
        return rewardPoints;
    }

    // Calculate reward points: 5 points for each rupee spent on final price
    rewardPoints += finalPrice * 5;

    return rewardPoints;
    }
    
    @Override
    public  void generateBill() {
        if (!isBought) {
            System.out.println("No purchase made to generate a bill.");
        } else {
            System.out.println("Visitor ID: " + visitorId);
            System.out.println("Visitor Name: " + fullName);
            System.out.println("Artwork Name: " + artworkName);
            System.out.println("Artwork Price: " + artworkPrice);
            System.out.println("Discount Amount: " + calculateDiscount());
            System.out.println("Final Price: " + finalPrice);
        }
    }

    // terminateVisitor method
    private void terminateVisitor() {
        isActive = false;
        upgradeDiscount = false;
        visitCount = 0;
        cancelCount = 0;
        rewardPoints = 0;
    }

    // cancelProduct method
    @Override
    public String cancelProduct(String artworkName, String cancellationReason) {
    // Check if cancellation limit is reached
    if (cancelCount >= cancelLimit) {
        terminateVisitor();
        return "Your account has been terminated due to excessive cancellations.";
    }

    // Check if any product has been purchased
    if (!isBought) {
        return "No product to cancel.";
    }

    // Check if the given artwork name matches the purchased artwork
    if (this.artworkName != null && this.artworkName.equals(artworkName)) {
        // Reset purchase details
        this.artworkName = null;
        this.isBought = false;

        // Calculate refundable amount with a 10% cancellation fee
        refundableAmount = artworkPrice - (artworkPrice * 0.10);

        // Deduct reward points earned for this purchase
        rewardPoints -= finalPrice * 5;

        // Update cancellation count and buy count
        cancelCount++;
        buyCount--;

        // Record the cancellation reason
        this.cancellationReason = cancellationReason;

        // Return success message
        return "Cancellation successful. Refundable Amount: " + refundableAmount;
    }

    // If artwork names do not match
    return "Incorrect artwork name.";
    }   
    // Display details method
    @Override
    public void display() {
        super.display(); // Call the parent class display method
        System.out.println("Eligible for Discount Upgrade: " + upgradeDiscount);
        System.out.println("Visit Limit: " + visitLimit);
        System.out.println("Discount Percent: " + discountPercent);
    }
    
}

