 

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class ArtGalleryGUI extends JFrame {
    // for Visitor storage
    private ArrayList<ArtGalleryVisitor> visitorList = new ArrayList<>();

    // Text fields 
    private JTextField visitorIdText, fullNameText, contactText, ticketPriceText;
    private JTextField artworkNameText, artworkPriceText, cancelReasonText;

    // Radio buttons for gender
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;

    // Combo boxes
    private JComboBox<String> dayCombo, monthCombo, yearCombo;
    private JComboBox<String> ticketPriceCombo;
    //log visit
    private boolean isActive = false;//it becomes true when visit is logged

    // Buttons

    private JButton btnAddVisitor, btnLogVisit, btnBuy, btnCalculateDiscount, 
    btnCalculateRewardsPoint, btnGenerateBill, btnDisplayDetails, 
    btnClearFields, btnCancelProduct, btnCheckUpgrade, btnAssignAdvisor,btnRead,btnSave;
    private DefaultTableModel tableModel;

    // Table or TextArea to display visitor details or bill
    private JTextArea displayArea;
    public ArtGalleryGUI() {
        setTitle("Visitor Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);

        // Visitor Details Section 
        visitorIdText = new JTextField(15);
        fullNameText = new JTextField(15);
        contactText= new JTextField(15);
        ticketPriceText = new JTextField(15);

        ticketPriceCombo = new JComboBox<>(new String[]{"Standard", "Elite"});
        JTextField registrationDateField = new JTextField(15);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();

        genderGroup.add(maleRadio); 
        genderGroup.add(femaleRadio); 
        genderGroup.add(otherRadio);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Visitor Panel

        JPanel visitorPanel = new JPanel(new BorderLayout());
        visitorPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Visitor Details Section",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                new Color(0, 0, 0)
            ));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0;
        gbc.gridy=0;
        formPanel.add(new JLabel("Visitor ID:"), gbc);
        gbc.gridx=1;
        formPanel.add(visitorIdText, gbc);

        gbc.gridx=2;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx=3;
        formPanel.add(fullNameText, gbc);

        gbc.gridx=0; gbc.gridy=1;
        formPanel.add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        gbc.gridx=1; formPanel.add(genderPanel, gbc);

        gbc.gridx=2; formPanel.add(new JLabel("Registration Date:"), gbc);
        gbc.gridx=3;
        gbc.gridy=1;
         // Create combo boxes for date
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++)
            days[i-1] = String.valueOf(i);
        dayCombo = new JComboBox<>(days);

        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        monthCombo = new JComboBox<>(months);

        String[] years = new String[50];
        for (int i = 0; i < 50; i++)
            years[i] = String.valueOf(1980 + i);
        yearCombo = new JComboBox<>(years);

        // Add day, month, year to a small panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
        datePanel.add(dayCombo);
        datePanel.add(monthCombo);
        datePanel.add(yearCombo);
        formPanel.add(datePanel, gbc);

        gbc.gridx=0; gbc.gridy=2;
        formPanel.add(new JLabel("Contact Number:"), gbc);
        gbc.gridx=1; formPanel.add(contactText, gbc);
        gbc.gridx=2; formPanel.add(new JLabel("Ticket Type:"), gbc);
        gbc.gridx=3; 
        formPanel.add(ticketPriceCombo,gbc);

        gbc.gridx=0; gbc.gridy=3;
        formPanel.add(new JLabel("Ticket Price:"), gbc);
        gbc.gridx=1; 
        ticketPriceText.setEditable(false); // make it non-editable
        formPanel.add(ticketPriceText, gbc);
        // Add ActionListener to update ticket price automatically
        ticketPriceCombo.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedType = (String) ticketPriceCombo.getSelectedItem();
                    if (selectedType.equals("Standard")) {
                        ticketPriceText.setText("1000");
                    } else if (selectedType.equals("Elite")) {
                        ticketPriceText.setText("2000");
                    } else {
                        ticketPriceText.setText("");
                    }
                }
            });

        visitorPanel.add(formPanel, BorderLayout.CENTER);

        JPanel visitorButtons = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        btnAddVisitor = new JButton("Add Visitor");
        btnAddVisitor.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    addVisitor();
                }
            });
        visitorButtons.add(btnAddVisitor);

        btnDisplayDetails = new JButton("Display Details");
        btnDisplayDetails.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    displayDetails();
                }
            });
        visitorButtons.add(btnDisplayDetails);

        btnRead = new JButton("Read");
        btnRead.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    read();
                }
            });
        visitorButtons.add(btnRead);

        btnSave= new JButton("Save");
        btnSave.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    save();
                }
            });
        visitorButtons.add(btnSave);

        btnClearFields = new JButton("Clear Text Fields");
        btnClearFields.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    clearFields();
                }
            });
        visitorButtons.add(btnClearFields);

        Dimension btnSize = new Dimension(150,30);
        btnAddVisitor.setPreferredSize(btnSize);
        btnDisplayDetails.setPreferredSize(btnSize);
        btnRead.setPreferredSize(btnSize);
        btnSave.setPreferredSize(btnSize);
        btnClearFields.setPreferredSize(btnSize);

        visitorButtons.add(btnAddVisitor);
        visitorButtons.add(btnDisplayDetails);
        visitorButtons.add(btnRead);
        visitorButtons.add(btnSave);
        visitorButtons.add(btnClearFields);
        visitorPanel.add(visitorButtons, BorderLayout.SOUTH);
        mainPanel.add(visitorPanel, BorderLayout.NORTH);

        // ------------------- Purchase Section -------------------
        JPanel purchasePanel = new JPanel(new BorderLayout());
        purchasePanel.setBorder(BorderFactory.createTitledBorder("Purchase Section"));

        JPanel purchaseForm = new JPanel(new GridBagLayout());
        GridBagConstraints Gbc = new GridBagConstraints();
        Gbc.insets = new Insets(5,5,5,5);
        Gbc.anchor = GridBagConstraints.WEST;

        JTextField purchaseVisitorIdField = new JTextField(15);
        artworkNameText = new JTextField(15);   
        artworkPriceText = new JTextField(15);  
        cancelReasonText = new JTextField(15);

        Gbc.gridx=0; 
        Gbc.gridy=0; 
        purchaseForm.add(new JLabel("Visitor ID:"), Gbc);
        Gbc.gridx=1;
        purchaseForm.add(purchaseVisitorIdField, Gbc);

        Gbc.gridx=0;
        Gbc.gridy=1; 
        purchaseForm.add(new JLabel("Artwork Name:"), Gbc);
        Gbc.gridx=1;
        purchaseForm.add(artworkNameText, Gbc);

        Gbc.gridx=0;
        Gbc.gridy=2;
        purchaseForm.add(new JLabel("Artwork Price:"), Gbc);
        Gbc.gridx=1;
        purchaseForm.add(artworkPriceText, Gbc);

        Gbc.gridx=0;
        Gbc.gridy=3;
        purchaseForm.add(new JLabel("Cancel Reason:"), Gbc);
        Gbc.gridx=1;
        purchaseForm.add(cancelReasonText, Gbc);

        purchasePanel.add(purchaseForm, BorderLayout.CENTER);

        JPanel purchaseButtons = new JPanel(new GridLayout(3,2,5,5));
        btnBuy = new JButton("Buy");
        btnBuy.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    buy();
                } 
            });
        purchaseButtons.add(btnBuy);

        btnCalculateDiscount=new JButton("Calculate Discount");
        btnCalculateDiscount.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    calculateDiscount();
                }
            });
        purchaseButtons.add(btnCalculateDiscount);

        btnCalculateRewardsPoint=new JButton("Reward Points");
        btnCalculateRewardsPoint.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    calculateRewardPoints();
                }
            });
        purchaseButtons.add(btnCalculateRewardsPoint);

        btnGenerateBill=new JButton("Generate Bill");
        btnGenerateBill.addActionListener(new ActionListener()
            { 
                public void actionPerformed(ActionEvent e){
                    generateBill();
                }
            });
        purchaseButtons.add(btnGenerateBill);

        btnCancelProduct=new JButton("Cancel Product");
        btnCancelProduct.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    cancelProduct();
                }
            });
        purchaseButtons.add(btnCancelProduct);
        btnCheckUpgrade = new JButton("Check Upgrade Discounts");
        btnCheckUpgrade.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    checkUpgrade();
                }
            });
        purchaseButtons.add(btnCheckUpgrade);
        purchasePanel.add(purchaseButtons, BorderLayout.SOUTH);

        mainPanel.add(purchasePanel, BorderLayout.WEST);

        // Manage Visitor Log 
        JPanel managePanel = new JPanel();
        managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));
        managePanel.setBorder(BorderFactory.createTitledBorder("Manage Visit"));
        // Create buttons
        Dimension manageBtnSize = new Dimension(200, 40);
        btnLogVisit = new JButton("Log Visit");
        btnLogVisit.setPreferredSize(manageBtnSize);
        btnLogVisit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent f){
                    logVisitor();
                }
            });
        managePanel.add(btnLogVisit);

        btnAssignAdvisor = new JButton("Assign Personal Advisory");
        btnAssignAdvisor.setPreferredSize(manageBtnSize);
        btnAssignAdvisor.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    assignPersonalArtAdvisor();
                }
            });
        managePanel.add(btnAssignAdvisor);



        // Set same preferred size for all buttons
        //Dimension btnSize = new Dimension(200, 40);
        btnLogVisit.setPreferredSize(btnSize);
        btnAssignAdvisor.setPreferredSize(btnSize);
        btnCalculateDiscount.setPreferredSize(btnSize);

        // Add buttons directly
        managePanel.add(btnLogVisit);
        managePanel.add(btnAssignAdvisor);
        // managePanel.add(btnCalculateDiscount);

        // Add this panel to the main panel (right side)
        mainPanel.add(managePanel, BorderLayout.EAST);
        // ------------------- Display Section -------------------
        String[] columns = {"Visitor ID", "Full Name", "Gender", "Contact","Date","Ticket Type", 

                "Ticket Price","Artwork Name","Artwork Price","Cancel Reason"};
        tableModel = new DefaultTableModel(columns,0);
        JTable displayTable = new JTable(tableModel);
        displayTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(displayTable);
        scrollPane.setPreferredSize(new Dimension(800,200));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Display Section"));
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(displayPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ArtGalleryGUI();
    }

    private void addVisitor() {
        try {
            // --- VISITOR ID ---
            String visitorIdStr = visitorIdText.getText().trim();
            if (visitorIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Visitor ID cannot be empty.");
                return;
            }
            int visitorId;
            try {
                visitorId = Integer.parseInt(visitorIdStr);
                if (visitorId < 0) {
                    JOptionPane.showMessageDialog(this, "Visitor ID cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Visitor ID must be a valid number.");
                return;
            }

            String fullName = fullNameText.getText().trim();
            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Full Name cannot be empty.");
                return;
            }
            if (!fullName.matches("[a-zA-Z\\s]+")) {  // only letters and spaces allowed
                JOptionPane.showMessageDialog(this, "Full Name cannot contain numbers or symbols.");
                return;
            }

            String contactNumber = contactText.getText().trim();
            if (contactNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Contact Number cannot be empty.");
                return;
            }
            if (contactNumber.length() > 10) {
                JOptionPane.showMessageDialog(this, "Contact Number cannot exceed 10 digits.");
                return;
            }
            try {
                long num = Long.parseLong(contactNumber);
                if (num < 0) {
                    JOptionPane.showMessageDialog(this, "Contact Number cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Contact Number must be numeric only.");
                return;
            }

            // Date part unchanged
            String day = (String) dayCombo.getSelectedItem();
            String month = (String) monthCombo.getSelectedItem();
            String year = (String) yearCombo.getSelectedItem();
            String registrationDate = day + "/" + month + "/" + year;

            String gender = "";
            if (maleRadio.isSelected()) {
                gender = "Male";
            } else if (femaleRadio.isSelected()) {
                gender = "Female";
            } else if (otherRadio.isSelected()) {
                gender = "Other";
            } else {
                JOptionPane.showMessageDialog(this, "Please select a gender.");
                return;
            }

            String ticketType = (String) ticketPriceCombo.getSelectedItem();
            String ticketCostStr = ticketPriceText.getText().trim();
            if (ticketCostStr.isEmpty()) { 
                JOptionPane.showMessageDialog(this, "Ticket Cost cannot be empty.");
                return;
            }
            double ticketCost;
            try {
                ticketCost = Double.parseDouble(ticketCostStr);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this,"Ticket Cost must be a valid number.");
                return;
            }

            boolean isExist = false;
            for (ArtGalleryVisitor v : visitorList) {
                if (v.getVisitorId() == visitorId) {
                    JOptionPane.showMessageDialog(this, "Duplicate Visitor ID");
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                ArtGalleryVisitor visitor;
                if (ticketType.equalsIgnoreCase("Standard")) {
                    visitor = new StandardVisitor(visitorId, fullName, gender, contactNumber,
                        registrationDate, ticketCost, ticketType);
                } else {
                    visitor = new EliteVisitor(visitorId, fullName, gender, contactNumber,
                        registrationDate, ticketCost, ticketType);
                }

                String artworkName = artworkNameText.getText().trim();
                visitor.setArtworkName(artworkName);

                String artworkPriceStr = artworkPriceText.getText().trim();
                double artworkPrice = 0;
                if (!artworkPriceStr.isEmpty()) { 
                    try {
                        artworkPrice = Double.parseDouble(artworkPriceStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Artwork Price must be a valid number.");
                        return;
                    }
                }
                visitor.setArtworkPrice(artworkPrice);

                visitor.setCancellationReason(cancelReasonText.getText().trim()); 

                visitorList.add(visitor);
                JOptionPane.showMessageDialog(this, "Visitor added successfully!");
            }

        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void logVisitor(){
        try {
            int visitorId = Integer.parseInt(visitorIdText.getText());
            boolean found = false;

            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    visitor.logVisit();  // increment visit count
                    isActive=true;
                    JOptionPane.showMessageDialog(null, "Visit logged successfully!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Visitor ID not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID.");
        }
    }

    private void displayDetails(){
        try{

            tableModel.setRowCount(0);  
            // Display ALL current visitors (including new ones)
            for (ArtGalleryVisitor visitor : visitorList) {
                if(visitor!=null){
                    tableModel.addRow(new Object[] {
                            visitor.getVisitorId(),
                            visitor.getFullName(),
                            visitor.getGender(),
                            visitor.getContactNumber(),
                            visitor.getRegistrationDate(),
                            visitor.getTicketType(),
                            visitor.getTicketCost(),
                            visitor.getArtworkName(),
                            visitor.getArtworkPrice(),
                            visitor.getCancellationReason(),
                        });
                }

            }

        }catch(Exception none){
            JOptionPane.showMessageDialog(this,"There is no data to show");

        }
    }

    private void clearFields(){
        visitorIdText.setText("");
        fullNameText.setText("");
        contactText.setText("");
        genderGroup.clearSelection();
        ticketPriceCombo.setSelectedIndex(0);
        ticketPriceText.setText("");
        artworkNameText.setText("");
        artworkPriceText.setText("");
        cancelReasonText.setText("");
        dayCombo.setSelectedIndex(0);
        monthCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        visitorIdText.setText("");
    }

    private void buy() {
        try {
            // --- Get Visitor ID ---
            String visitorIdStr = visitorIdText.getText().trim();
            if (visitorIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Visitor ID cannot be empty.");
                return;
            }
            int visitorId = Integer.parseInt(visitorIdStr);
            if (visitorId < 0) {
                JOptionPane.showMessageDialog(this, "Visitor ID cannot be negative.");
                return;
            }

            // --- Check if visitor is active ---
            if (!isActive) {
                JOptionPane.showMessageDialog(this, "Please log in first to make a purchase.");
                return;
            }

            // --- Get Artwork Details ---
            String artworkName = artworkNameText.getText().trim();
            if (artworkName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Artwork name cannot be empty");
                return;
            }

            String artworkPriceStr = artworkPriceText.getText().trim();
            if (artworkPriceStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Artwork price cannot be empty.");
                return;
            }
            double artworkPrice = Double.parseDouble(artworkPriceStr);

            // --- Find Visitor and make purchase ---
            boolean found = false;
            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    found=true;
                    // --- Check if visitor has logged visit ---
            if (!visitor.hasLoggedVisit()){ // using your visit flag
                JOptionPane.showMessageDialog(this, "You must log your visit before making a purchase!");
                return; // stop purchase if not visited
            }
            
                    visitor.buyProduct(artworkName, artworkPrice);  // handles isBought & buyCount
                    visitor.setBought(true);
                    JOptionPane.showMessageDialog(this,
                        "Purchase Successful!\n" +
                        "To see the final price, calculate discount using the discount button.",
                        "Purchase Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Visitor ID not found in records!");
            }
            displayDetails();//it refreshes table after purchase
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format in Visitor ID or Artwork Price.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void assignPersonalArtAdvisor() {
        try {
            // --- VISITOR ID INPUT ---
            String visitorIdStr = visitorIdText.getText().trim();
            if (visitorIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Visitor ID cannot be empty.");
                return;
            }

            int visitorId = Integer.parseInt(visitorIdStr);

            // --- SEARCH IN VISITOR LIST ---
            boolean found = false;
            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    found = true;

                    // --- CHECK IF ELITE VISITOR ---
                    if (visitor instanceof EliteVisitor) {
                        EliteVisitor eliteVisitor = (EliteVisitor) visitor; // casting
                        eliteVisitor.assignPersonalArtAdvisor(); // method call
                        JOptionPane.showMessageDialog(this, "Personal Art Advisor assigned to Elite Visitor: " 
                            + eliteVisitor.getFullName());
                    } else {
                        JOptionPane.showMessageDialog(this, "This visitor is not an Elite Visitor. Advisor cannot be assigned.");
                    }
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Visitor ID not found in the system.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Visitor ID must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void checkUpgrade() {
        try {
            int visitorId = Integer.parseInt(visitorIdText.getText().trim());
            boolean found = false;

            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    found = true;

                    // Check if a purchase has been made
                    if (!visitor.isBought()) {
                        JOptionPane.showMessageDialog(null, 
                            "Purchase required first to check for upgrade.");
                        break;
                    }

                    // Only StandardVisitors can upgrade
                    if (visitor instanceof StandardVisitor standardVisitor) {
                        boolean upgraded = standardVisitor.checkDiscountUpgrade();
                        if (upgraded) {
                            JOptionPane.showMessageDialog(null, 
                                "Congratulations! You are eligible for upgraded discount (15%).");
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                "No upgrade. Your discount remains 10%.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Only Standard Visitors can check for discount upgrade.");
                    }
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Visitor ID not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Visitor ID.");
        }
    }

    private void calculateDiscount() {
        try {
            int visitorId = Integer.parseInt(visitorIdText.getText().trim());
            boolean found = false;

            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    found = true;

                    if (!visitor.isBought()) {
                        JOptionPane.showMessageDialog(null, 
                            "Purchase required first to calculate discount.");
                        break;
                    }

                    double discount = visitor.calculateDiscount();
                    double finalPrice = visitor.getFinalPrice(); // getter in parent class

                    JOptionPane.showMessageDialog(null, 
                        "Discount Amount: " + discount + "\nFinal Price: " + finalPrice);
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Visitor ID not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Visitor ID.");
        }
    }

    private void calculateRewardPoints() {
        try {
            String visitorIdStr = visitorIdText.getText().trim();
            if (visitorIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Visitor ID cannot be empty.");
                return;
            }

            int visitorId = Integer.parseInt(visitorIdStr);

            // Search for visitor in visitorList
            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    double points = visitor.calculateRewardPoint(); // Polymorphism here when ticket type separates both child classes overrides calculate point () 
                    JOptionPane.showMessageDialog(this, 
                        "Reward Points for Visitor " + visitorId + ": " + points);
                    return;
                }
            }

            // If not found
            JOptionPane.showMessageDialog(this, "Visitor ID not found!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Visitor ID. Please enter a number.");
        }
    }

    private void cancelProduct() {
        try {
            // --- Read input values ---
            String visitorIdStr = visitorIdText.getText().trim();
            String artworkName = artworkNameText.getText().trim();
            String cancellationReason = cancelReasonText.getText().trim();

            if (visitorIdStr.isEmpty() || artworkName.isEmpty() || cancellationReason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
                return;
            }

            int visitorId = Integer.parseInt(visitorIdStr);

            // --- Search visitor in visitorList ---
            boolean found = false;
            for (ArtGalleryVisitor visitor : visitorList) {
                if (visitor.getVisitorId() == visitorId) {
                    found = true;

                    // --- Call cancelProduct depending on visitor type ---
                    String message;
                    if (visitor instanceof EliteVisitor) {
                        message = ((EliteVisitor) visitor).cancelProduct(artworkName, cancellationReason);
                    } else if (visitor instanceof StandardVisitor) {
                        message = ((StandardVisitor) visitor).cancelProduct(artworkName, cancellationReason);
                    } else {
                        message = "Cancel Product.";
                    }

                    // Show result to user
                    JOptionPane.showMessageDialog(this, message);
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Visitor ID not found.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Visitor ID must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Method to handle bill generation
    private void generateBill() {
        String visitorIdStr = visitorIdText.getText().trim();
        if (visitorIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Visitor ID cannot be empty.");
            return;
        }

        int visitorId;
        try {
            visitorId = Integer.parseInt(visitorIdStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Visitor ID must be a number.");
            return;
        }

        // Search for the visitor
        ArtGalleryVisitor visitor = null;
        for (ArtGalleryVisitor v : visitorList) {
            if (v.getVisitorId() == visitorId) {
                visitor = v;
                break;
            }
        }

        if (visitor == null) {
            JOptionPane.showMessageDialog(this, "Visitor ID not found.");
            return;
        }

        if (!visitor.isBought()) {
            JOptionPane.showMessageDialog(this, "No purchase made by this visitor.");
            return;
        }

        // Build bill string

        String bill = 
            "===== ART GALLERY BILL =====\n" +
            "Visitor ID: " + visitor.getVisitorId() + "\n" +
            "Visitor Name: " + visitor.getFullName() + "\n" +
            "Artwork Name: " + visitor.getArtworkName() + "\n" +
            "Artwork Price: " + visitor.getArtworkPrice() + "\n" +
            "Discount Amount: " + visitor.calculateDiscount() + "\n" +
            "Final Price: " + visitor.getFinalPrice() + "\n" +
            "============================\n";

        // Display in GUI
        JTextArea billArea = new JTextArea(bill.toString());
        billArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        JOptionPane.showMessageDialog(this, scrollPane, "Bill Details", JOptionPane.INFORMATION_MESSAGE);

        // Export to .txt file
        try (PrintWriter out = new PrintWriter(visitor.getFullName() + "_Bill.txt")) {
            out.println(bill.toString());
            JOptionPane.showMessageDialog(this, "Bill exported as " + visitor.getFullName() + "_Bill.txt");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error exporting bill: " + ex.getMessage());
        }
    }

  
private void save() {
    if (visitorList.isEmpty()) {  
        JOptionPane.showMessageDialog(this, "No visitor data to save!");
        return;
    }

    // Use absolute path
    File file = new File("C:\\Users\\eijke\\First Semester\\CourseWork\\Coursework\\VisitorDetails.txt");

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        // Header
        bw.write(String.format("%-5s %-20s %-10s %-15s %-15s %-10s %-10s%n",
                "ID", "Name", "Gender", "Contact No", "Registration Date", "Ticket Type", "Ticket Cost"));

        // Visitor data
        for (ArtGalleryVisitor visitor : visitorList) {
            bw.write(String.format("%-5d %-20s %-10s %-15s %-15s %-10s %-10.2f%n",
                    visitor.getVisitorId(),
                    visitor.getFullName(),
                    visitor.getGender(),
                    visitor.getContactNumber(),
                    visitor.getRegistrationDate(),
                    visitor.getTicketType(),
                    visitor.getTicketCost()));
        }

        JOptionPane.showMessageDialog(this, "Visitor details saved successfully!");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage());
    }
}
  
private void read() {
    File file = new File("C:\\Users\\eijke\\First Semester\\CourseWork\\Coursework\\VisitorDetails.txt");

    // Check if file exists and is not empty
    if (!file.exists() || file.length() == 0) {
        JOptionPane.showMessageDialog(this, "No visitor data added yet!");
        return;
    }

    // Read and display file content
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        String line;
        while ((line = br.readLine()) != null) {
            displayArea.append(line + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Visitor Records", JOptionPane.INFORMATION_MESSAGE);

        // Print message to terminal
        System.out.println("Visitor data loaded successfully from VisitorDetails.txt");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        System.out.println("Error reading file: " + e.getMessage());
    }
}
}

