package Coursework;
abstract class ArtGalleryVisitor{
    // Protected attributes
    protected int visitorId, visitCount, cancelCount, buyCount;
    protected String fullName, gender, contactNumber, registrationDate, ticketType;
    protected double ticketCost, rewardPoints, finalPrice, discountAmount, artworkPrice, refundableAmount;
    protected String cancellationReason, artworkName;
    protected boolean isActive, isBought;
    protected final int  cancelLimit;

    // Constants for ticket types and their costs
    private static final int STANDARD_TICKET_COST = 1000;
    private static final int ELITE_TICKET_COST = 2000;

    // Constructor
    public ArtGalleryVisitor(int visitorId, String fullName,String gender,String contactNumber,String registrationDate,
    double ticketCost,String ticketType) {
        this.visitorId = visitorId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.ticketType = ticketType;
        this.registrationDate = registrationDate;
        this.ticketCost = ticketCost;

        // Initialize default values
        this.visitCount = 0;
        this.rewardPoints = 0;
        this.finalPrice = 0;
        this.discountAmount = 0;
        this.refundableAmount = 0;
        this.cancelCount = 0;
        this.buyCount = 0;
        this.isActive = false;
        this.isBought = false;
        this.cancellationReason = "";
        this.cancelLimit = 3; // Default value
    }

    // Accessor (getter) methods
    public int getVisitorId(){
        return visitorId; 
    }

    public String getFullName(){
        return fullName;
    }

    public String getGender(){
        return gender;
    }

    public String getContactNumber(){ 
        return contactNumber;
    }

    public String getRegistrationDate(){
        return registrationDate; 
    }

    public double getTicketCost(){
        return ticketCost; 
    }

    public String getTicketType(){ 
        return ticketType; 
    }

    public boolean isBought(){
        return isBought;
    }

    public void setFullName(String fullName){
        this.fullName = fullName; 
    }

    public void setGender(String gender){ 
        this.gender = gender; 
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setBought(boolean bought) {
        this.isBought = bought;
    }

    // Method to log visits
    public void logVisit() {
        visitCount++;
        isActive = true;
    }
    //for getter method
    public String getArtworkName() {
        return artworkName;
    }

    public double getArtworkPrice() {
        return artworkPrice;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
    //for setter method
    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public void setArtworkPrice(double artworkPrice) {
        this.artworkPrice = artworkPrice;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
    public boolean hasLoggedVisit(){
    return visitCount>0;
}

    // Abstract methods
    public abstract String buyProduct(String artworkName, double artworkPrice);

    public abstract double calculateDiscount();

    public abstract  double calculateRewardPoint();

    public abstract String cancelProduct(String artworkName, String cancellationReason);

    public abstract void generateBill();

    // Method to display visitor details
    public void display() {
        System.out.println("Visitor ID: " + visitorId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Ticket Cost: " + ticketCost);
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Visit Count: " + visitCount);
        System.out.println("Reward Points: " + rewardPoints);
        System.out.println("Cancel Count: " + cancelCount);
        System.out.println("Buy Count: " + buyCount);
        System.out.println("Is Active: " + isActive);
        System.out.println("Is Bought: " + isBought);
        System.out.println("Final Price: " + finalPrice);
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Artwork Name: " + artworkName);
        System.out.println("Artwork Price: " + artworkPrice);
        System.out.println("Refundable Amount: " + refundableAmount);
        System.out.println("Cancellation Reason: " + cancellationReason);
    }
}
