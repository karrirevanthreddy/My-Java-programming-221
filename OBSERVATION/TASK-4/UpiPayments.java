package observation;

//==========================================
// 1. Custom Exceptions
//==========================================
class InsufficientBalanceException extends Exception {
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidUPIException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidUPIException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidAmountException(String message) {
        super(message);
    }
}

//==========================================
// 2. PaymentService Interface
//==========================================
interface PaymentService {
    void pay(String receiverUpiId, double amount) 
        throws InvalidUPIException, InvalidAmountException, InsufficientBalanceException;
    void checkBalance();
}

//==========================================
// 3. Wallet Class (Encapsulated)
//==========================================
class Wallet {
    private String userName;
    private String mobileNumber;
    private String upiId;
    private double balance;

    public Wallet(String userName, String mobileNumber, String upiId, double initialBalance) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.upiId = upiId;
        this.balance = Math.max(0, initialBalance);
    }

    public String getUserName() { return userName; }
    public String getMobileNumber() { return mobileNumber; }
    public String getUpiId() { return upiId; }
    public double getBalance() { return balance; }

    public void addMoney(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to add must be greater than zero.");
        }
        this.balance += amount;
        System.out.println("Successfully added ₹" + amount + " to wallet.");
    }

    public void deductMoney(double amount) {
        this.balance -= amount;
    }

    public void displayWalletDetails() {
        System.out.println("\n----- Wallet Details -----");
        System.out.println("User Name     : " + userName);
        System.out.println("Mobile Number : " + mobileNumber);
        System.out.println("UPI ID        : " + upiId);
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("--------------------------");
    }
}

//==========================================
// 4. Payment Implementation
//==========================================
class UpiPaymentService implements PaymentService {
    private Wallet wallet;

    public UpiPaymentService(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public void pay(String receiverUpiId, double amount) 
            throws InvalidUPIException, InvalidAmountException, InsufficientBalanceException {
        
        // 1. Validate UPI ID format safely
        if (receiverUpiId == null || !receiverUpiId.contains("@")) {
            throw new InvalidUPIException("Invalid UPI ID format: '" + receiverUpiId + "'.");
        }
        
        int atIndex = receiverUpiId.indexOf("@");
        if (atIndex == receiverUpiId.length() - 1 || !receiverUpiId.substring(atIndex).contains(".")) {
            throw new InvalidUPIException("Invalid UPI ID format: '" + receiverUpiId + "'.");
        }

        // 2. Validate payment amount
        if (amount <= 0) {
            throw new InvalidAmountException("Payment amount must be greater than zero.");
        }

        // 3. Check for sufficient balance
        if (wallet.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance! Requested: ₹" + amount + ", Available: ₹" + wallet.getBalance());
        }

        // Deduct money if all validations pass
        wallet.deductMoney(amount);
        System.out.println("Payment of ₹" + amount + " to " + receiverUpiId + " was successful!");
    }

    @Override
    public void checkBalance() {
        System.out.println("Available Balance: ₹" + wallet.getBalance());
    }
}

//==========================================
// 5. Main Execution Class (File Name: UpiPayments.java)
//==========================================
public class UpiPayments {
    public static void main(String[] args) {
        // Step 1: Create a wallet
        Wallet userWallet = new Wallet("REVANTH REDDY", "9652569999", "krevanthreddy2@upi", 1000.0);
        UpiPaymentService upiSystem = new UpiPaymentService(userWallet);

        userWallet.displayWalletDetails();

        // Step 2: Add money to wallet
        try {
            System.out.println("\nAdding ₹500 to wallet...");
            userWallet.addMoney(500.0);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Step 3: Test valid transaction using try-catch-finally
        executeTransaction(upiSystem, "merchant@okaxis", 300.0);

        // Step 4: Test Invalid UPI ID Exception
        executeTransaction(upiSystem, "invalidUpiFormat", 200.0);

        // Step 5: Test Negative / Zero Amount Exception
        executeTransaction(upiSystem, "friend@okicici", -50.0);

        // Step 6: Test Insufficient Balance Exception
        executeTransaction(upiSystem, "friend@okicici", 5000.0);

        // Final Wallet Details
        userWallet.displayWalletDetails();
    }

    // Helper method to demonstrate try-catch-finally block
    private static void executeTransaction(UpiPaymentService system, String receiverUpi, double amount) {
        System.out.println("\nAttempting transaction to " + receiverUpi + " of amount ₹" + amount + "...");
        try {
            system.pay(receiverUpi, amount);
        } catch (InvalidUPIException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        } finally {
            System.out.print("[Transaction Log Status] ");
            system.checkBalance();
        }
    }
}