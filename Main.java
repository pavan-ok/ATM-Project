import java.util.*;
import java.util.regex.Pattern;
class Account extends ATM {      //Account class which stores details of a user
    Queue<String> q1=new LinkedList<>();
    Queue<String> q = new LinkedList<>();       // This Queue helps us to store transactions made by a user
    Account(int accNO,String pwd,String name,String phNumber){
        this.accountNo=accNO;
        this.Password=pwd;
        this.name=name;
        this.PhNo=phNumber;
    }
    Account(){}
    public void setQ(String s) {        //this method makes the queue to store the last three transactions made
        // by the user
        q.add(s);
        q1.add(s);
        while (q.size() > 3) {
            q.remove();
        }
    }
    public void printQ(int d){  // this method prints whole the transactions done by the user till now
        int n=q1.size();
        while (n != 0) {
            n--;
            String p = q1.element();
//            System.out.println(p);
            if (q1.element().startsWith("+")) {
                System.out.println("Credited by Amount           :" + q1.element().substring(1));
            }
            else {
                System.out.println("Debited by Amount            :" + q1.element().substring(1));
            }
            q1.remove();
            q1.add(p);
        }
        n=q.size();
        while(n!=0){
            n--;
            String p=q.element();
            q.remove();
            q.add(p);
        }
    }
    public void printQ() {          //this method prints the last three transactions made by the user
        int n = q.size();
        System.out.println("Your last 3 transactions are :");
        while (n != 0) {
            n--;
            String p = q.element();
            if (q.element().startsWith("+")) {
                System.out.println("Credited by Amount           :" + q.element().substring(1));
            } else {
                System.out.println("Debited by Amount            :" + q.element().substring(1));
            }
            q.remove();
            q.add(p);
        }
        System.out.println("Balance                      :" + balance);
    }
    int chances = 3;        //this variable manages maximum number of times a user can enter a wrong password
    private int accountNo;  //a private variable that stores the account No of a particular user
    private String Password;    // a private variable that stores the password of the user
    double balance;             // a private variable that manages the balance  of the user in that account
    private String name;// a private variable (as user details are important) that stores the name of the user
    private String PhNo;// a private variable (as user details are important) that stores the Phone number of the user

    public int getAccountNo() {
        return accountNo;       //a Getter that returns the account number of the user
    }
    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;// a setter that sets the account number of the user
    }

    public String getPassword() {
        //a getter that gets the password set by the user .this is useful to know whether the
        //user is owner of the account or not
        return Password;
    }
    public void setPassword(String password) {
        Password = password;        //a setter that sets the password of the account in our database.
        // It is helpful when we create a new account for a user
    }

    public String getName() {
        return name;                //a getter that returns the name of the accountant
    }

    public void setName(String name) {
        this.name = name;           // a setter that sets the name for the new account created
    }

    public String getPhNo() {
        return PhNo;                //a getter that returns the phone no.
    }

    public void setPhNo(String phNo) {
        PhNo = phNo;                //a setter that sets phone number of the user to the account .
        // helpful when a new account is created
    }

    public void setBalance(double balance) {
        this.balance = balance;         //a setter that sets the balance of the user in our database
    }
}

interface Services {
    final String Password = "12345";      // password for the admin which is must to access the admin privileges

    //these methods in a banking system are very important thus they require data hiding (abstraction)
    boolean IsPossible(int n); //checks whether the user and atm have sufficient money to make the transaction

    String getpassword();// a method that returns password so that we can verify that the user is admin or not

    int addAmt();               // a special method used for adding money by the admin

    void print();

    // a method that prints the amount in the atm (highly important to maintain money in atm by (admin/ Bank manager)
    void remove(int one, int two, int five, int ten, int twenty, int hundred, int five100, int two1000);
    //this method helps in removing the money denominations so that our atm has accurate data
}

class ATM implements Services {
    int one = 100, two = 100, five = 100, ten = 100, twenty = 100, hundred = 100, five100 = 100, twoThousand = 100;

    //initially our atm is filled with 100 each 1,2,5(coins) : 10,20,100,500,2000(notes)
    public String getpassword() {
        return Password;
    }//this method returns password to check whether admin inputs password correct or not

    public boolean IsPossible(int n) { //checks whether the user and atm have sufficient money to make the transaction
        int c1, c2, c3, c4, c5, c6, c7, c8;
        c1 = n / 2000;
        if (c1 != 0) {
            if (twoThousand >= c1) {
                n -= c1 * 2000;//checks max number of 2000 notes one can get from the amount he asked
            } else {
                n -= twoThousand * 2000;
                c1 = twoThousand;
            }
        }
        c2 = n / 500;///similarly checks for 500
        if (c2 != 0) {
            if (five100 >= c2) {
                n -= (c2) * 500;
            } else {
                n -= five100 * 500;
                c2 = five100;
            }
        }
        c3 = n / 100;//checks for 100
        if (c3 != 0) {
            if (hundred >= c3) {
                n -= c3 * 100;
            } else {
                n -= this.hundred * 500;
                c3 = this.hundred;
            }
        }
        c4 = n / 20;//checks for 20
        if (c4 != 0) {
            if (twenty >= c4) {
                n -= c4 * 20;
            } else {
                n -= twenty * 20;
                c4 = twenty;
            }
        }
        c5 = n / 10;//checks for 10
        if (c5 != 0) {
            if (ten >= c5) {
                n -= c5 * 10;
            } else {
                n -= ten * 10;
                c5 = ten;
            }
        }
        c6 = n / 5;//checks for 5 coins
        if (c6 != 0) {
            if (five >= c6) {
                n -= c6 * 5;
            } else {
                n -= five * 5;
                c6 = five;
            }
        }
        c7 = n / 2;//checks for 2 coins
        if (c7 != 0) {
            if (two >= c7) {
                n -= c7 * 2;
            } else {
                n -= two * 2;
                c7 = two;
            }
        }
        c8 = n;//checks for 1 rupee coins
        if (c8 != 0) {
            if (one >= c8) {
                n -= c8 ;
            } else {
                n -= one;
                c8 = one;
            }
        }
        if (n == 0) {//if n is 0 this mean a combination is possible with the notes in the atm and thus the transaction proceeds forward
            remove(c8, c7, c6, c5, c4, c3, c2, c1);
            return true;
        } else {
            return false;//else transaction isnt done and asks the user to ask for another combination of money
        }
    }
    public void print() {//a method that prints the money available in the bank so that admin can check and add money to atm time to time
        System.out.println("1       :" + one);
        System.out.println("2       :" + two);
        System.out.println("5       :" + five);
        System.out.println("10      :" + ten);
        System.out.println("20      :" + twenty);
        System.out.println("100     :" + hundred);
        System.out.println("500     :" + five100);
        System.out.println("2000    :" + twoThousand);
    }
    public int addAmt() {
        Scanner sc = new Scanner(System.in);
        //method that adds money into atm from user and adds balance for his account
        int amt = 0;
        System.out.println("Please Enter the Number of Notes You Gonna add respectively");
        System.out.print("One Coins     :");
        int ont = sc.nextInt();
        amt += ont;//counts one rupee coins from user
        one += ont;
        System.out.print("Two Coins     :");
        int Two = sc.nextInt();
        amt += 2 * Two;// counts two rupee coins from user
        two += Two;
        System.out.print("Five Coins    :");
        int Five = sc.nextInt();// similarly counts other denominations as well
        amt += 5 * Five;
        five += Five;
        System.out.print("Ten Notes     :");
        int Ten = sc.nextInt();
        amt += 10 * Ten;
        ten += Ten;
        System.out.print("Twenty Notes  :");
        int Twenty = sc.nextInt();
        amt += 20 * Twenty;
        twenty += Twenty;
        System.out.print("Hundred Notes :");
        int Hundred = sc.nextInt();
        amt += 100 * Hundred;
        hundred += Hundred;
        System.out.print("500 Notes     :");
        int Five100 = sc.nextInt();
        amt += 500 * Five100;
        five100 += Five100;
        System.out.print("2000 Notes    :");
        int TwoThousand = sc.nextInt();
        amt += TwoThousand * 2000;
        twoThousand += TwoThousand;
        return amt;
    }
    // a special method used for adding money by the admin
    public void remove(int one, int two, int five, int ten, int twenty, int hundred, int five100, int two1000) {
        this.one -= one;
        this.two -= two;
        this.five -= five;
        this.ten -= ten;
        this.twenty -= twenty;
        this.hundred -= hundred;//removes amount available in atm and helps atm to give to the user
        this.five100 -= five100;
        this.twoThousand -= two1000;
    }
    //this method helps in removing the money denominations so that our atm has accurate data
}

public class Main {
    static Services at = new ATM();       //creates an object using dynamic memory dispatch concept
    static Vector<Account> v = new Vector<Account>();

    // A vector of class accounts are made so that we can store users in our database
    static void TEST() throws InterruptedException {
        Scanner sc = new Scanner(System.in);// A scanner is an inbuilt class that helps in taking input from user
        while (true) {//a while loop so that our program runs forever
            System.out.println("Welcome !!!");//welcome message
            System.out.println("1.Create Account\n2.Existing User\n3.Admin");
            //asks the user to choose an option from the following
            int k = sc.nextInt();
            if (k == 1) {
                System.out.println("Please Note Down Your Account No");
                System.out.format("%05d\n", (v.size() + 10001));//10001 is added so that account number starts with 1
                Account a = new Account();
                a.setAccountNo(v.size() + 1);
                System.out.println("Please enter a Strong Password of length more than 5 (integers only)");
                while (true) {
                    //used regex to make the constraints to be satisfied like it should have atleast 5 digits and must be digits
                    String s = sc.next();
                    if (Pattern.matches("[0-9]*", s) && !s.startsWith("0") && s.length() >= 5) {
                        a.setPassword(s);
                        break;
                    } else {
                        System.out.println("Please enter digits only [0-9] \nSize should be greater than 4 \nIt should not start with 0\nPlease re-enter the Password!!!");
                    }
                }
                System.out.println("Please Enter Your Name");
                String s;
                while (true) {
                    //used regex to make sure that the name contains only a-z and A-Z alphabets Only!!!!!!
                    s = sc.next();
                    if (Pattern.matches("[a-z A-Z]*", s)) {
                        a.setName(s);
                        break;
                    } else {
                        System.out.println("Please enter Alphabets Only(Excluding Symbols and Digits)");
                    }
                }
                System.out.println("Please Enter Your Phone Number");
                s = sc.next();
                while (true) {
                    //again used regex to make sure that phone number contains only + and digits
                    //I haven't fixed the number of digits for a Phone number ,as country code and digits vary from country to country
                    if (Pattern.matches("[0-9 + ]*", s)) {
                        a.setPhNo(s);
                        break;
                    } else {
                        System.out.println("Please enter digits only [0-9] \n");
                        s = sc.next();
                    }
                }
                System.out.println("Please add a minimum amount to your account (500rs) At Cash Dispenser");//user pays a 500 rs for opening a new account in out bank
                System.out.println("Please Check Your Details");//here user can see his account details
                System.out.println("Account Number      : " + (a.getAccountNo() + 10000));
                System.out.println("Name                : " + a.getName());
                System.out.println("Phone Number        : " + a.getPhNo());
                Thread.sleep(2000);
                System.out.println("Thank You");//thank you message
                a.balance = 500;
                a.setQ("+500");
                v.add(a);
            }
            if (k == 2) {
                System.out.println("Please Enter Your 5 digit Account Number");//taking account number as input from user
                int ac = sc.nextInt();
                ac -= 10000;//as our vector stored the account number ,as its index we have to remove 10000 from the account number from user input
                int p = 0;
                boolean b = false;
                for (int i = 0; i < v.size(); i++) {
                    if (v.get(i).getAccountNo() == ac) {
                        b = true;
                        p = i;          //a linear search is made in vector to search the account is available in database or not
                        break;
                    }
                }
                if (b) {
                    System.out.println("Please Enter Your Password");
                    String s = sc.next();//if user data is found we will ask the user to type password so that we can verify him
                    //if user inputs straight 3 wrong passwords his account will be blocked and can be unlocked using him mobile number
                    if (v.get(p).chances != 0 && v.get(p).getPassword().equals(s)) {
                        while (true) {
                            System.out.println("Welcome " + v.get(p).getName());
                            System.out.println("Functionalities");
                            System.out.println("1.Balance Enquiry\n2.Deposit Money\n3.Cash Withdrawal\n4.Last Three Transactions\n5.All Transaction Till Now\n6.Change Credentials\n7.Exit");
                            int x = sc.nextInt();//basic functionalities available in our atm
                            if (x == 1) {
                                System.out.println("Your Balance is " + v.get(p).balance);//prints balance
                            }
                            if (x == 2) {
                                double amt = 0;
                                double curBal = v.get(p).balance;
                                amt += at.addAmt();//calling the function and depositing the money into atm
                                System.out.println("You have deposited " + amt + " Successfully");
                                v.get(p).setBalance(curBal + amt);
                                System.out.println("Your Current Balance is : " + v.get(p).balance);
                                //prints current balance of the user
                                String l = "+";
                                l += amt;       //this makes a string for this transaction and adds to the queue in the user
                                // so that we can keep a track on the user transactions
                                v.get(p).setQ(l);
                            }
                            if (x == 3) {
                                System.out.println("Please Enter the amount You require");
                                int r = sc.nextInt();
                                double y = v.get(p).balance - r;
                                if (y >= 0) {
                                    String l = "-";
                                    l += r;
                                    v.get(p).setQ(l);
                                    if (at.IsPossible(r)) {//checks whether the amount asked by the user is available in our atm
                                        //,and he has enough balance in his account
                                        v.get(p).balance = y;
                                    } else {
                                        System.out.println("Money isnt available in ATM !!!\n" +
                                                "Please Enter Some good Combination");
                                    }
                                } else {
                                    System.out.println("Insufficient Balance");//if user doesn't have enough amount we inform he doesn't have enough balance
                                }
                                System.out.println("Your Current Balance is " + v.get(p).balance);//we print the balance of the user after the transaction
                            }
                            if (x == 4) {
                                v.get(p).printQ();
                                Thread.sleep(3000);//as the user asks to print the last three transactions
                                // we gave him 3000 milliseconds to analyse the 3 transactions made by him
                            }
                            if(x==5){
                                v.get(p).printQ(1);
                                Thread.sleep(3000);//as the user asks to print the last three transactions
                                // we gave him 3000 milliseconds to analyse the 3 transactions made by him
                            }
                            if (x == 6) {
                                //this helps user to change the credentials if the given data is wrong upto some extent
                                System.out.println("Please Re-Enter the old PassWord");
                                String ans = sc.next();
                                if (v.get(p).chances != 0 && v.get(p).getPassword().equals(ans)) {
                                    while (true) {
                                        System.out.println("Please Select the Data You want to Modify");
                                        System.out.println("1.Name\n2.Phone No.\n3.Password\n4.Exit");
                                        int y = sc.nextInt();
                                        if (y == 1) {
                                            System.out.println("Please Enter Your Full name");//this changes the name of the user in our database
                                            String pav = sc.next();
                                            v.get(p).setName(pav);
                                        }
                                        if (y == 2) {
                                            System.out.println("Please Enter your Phone No without +91");
                                            String pav = sc.next();//it changes the phone number of the user in our database
                                            v.get(p).setPhNo(pav);
                                        }
                                        if (y == 3) {
                                            System.out.println("Please Enter Your new password");
                                            String pav = sc.next();//this changes the password of the user in our database
                                            v.get(p).setPassword(pav);
                                        }
                                        if (y == 4) {
                                            break;//this ends the loop
                                        }
                                    }
                                }
                            }
                            if (x == 7) {
                                break;//this ends the loop
                            }
                            v.get(p).chances = 3;//refilling the chances for the user as 3
                            Thread.sleep(1000);
                        }
                    } else {
                        System.out.println("Please Enter a Valid Password");//if user input us wrong data we reduce the chances he can still try
                        v.get(p).chances--;
                        if (v.get(p).chances == 0) {
                            System.out.println("You have entered Wrong password 3 times....Please Enter Your Phone Number");
                            s = sc.next();//if he enters wrong 3 straight times we will ask him his phone number(maybe aadhar card number is a better option instead of phone number)
                            // and reveal his password
                            if (v.get(p).getPhNo().equals(s)) {
                                System.out.println(" Your Password");
                                v.get(p).chances = 3;
                                System.out.println(v.get(p).getPassword());
                            }
                        }
                    }
                } else {
                    System.out.println("We cant Find Your Account No in our DataBase...please Try again");//if account number isn't available in database it prints this statement
                }
            }
            if (k == 3) {
                System.out.println("Please Enter the Password");
                String pwd = sc.next();//if admin option is chosen we ask him for the password opted
                if (at.getpassword().equals(pwd)) {//it compares the password if yes it proceeds
                    while (true) {
                        System.out.println("Select One of the Option");
                        System.out.println("1.Add Money\n2.keep Maintenance\n3.Amount In ATM\n4.Exit");
                        int o = sc.nextInt();//he has some special functions like adding money to atm when money in atm comes to a minimum amount
                        if (o == 1) {
                            int amt = 0;
                            amt += at.addAmt();
                            System.out.println("You have deposited " + amt + " Successfully");
                        }
                        if (o == 2) {//he can even keep the atm under maintenance when the hardware has some issues so that the users don't face any issues
                            System.out.println("Enter time in milli Seconds");
                            int p = sc.nextInt();
                            System.out.println("ATM is Under Maintenance!!!Please come later");
                            Thread.sleep(p);
                        }
                        if (o == 3) {//he can see the money denomination in the atm
                            at.print();
                        }
                        if (o == 4) {//admin logouts from the atm and allow user to continue
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            TEST();         //Here We Used Exception handling so that our code works even user input format
        } catch (Exception e) {
            System.out.println("Please Enter in Right Format");
            //here if exception is found we tell the user to enter in right format and run the code as usually
            main(args);
        }
    }
}