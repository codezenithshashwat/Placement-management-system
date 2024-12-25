package tanisha;
import java.util.Scanner;
import java.io.*;
//import shashwat.Main;
import suhani.suhani;
import shashwat.tech;
//import suhani.NonTechCompany;

class User{
    Scanner sc=new Scanner(System.in);
    String Username;
    String Password;
    int c=0;
    private static final String FILE_PATH="tanisha\\users.txt";
    public void getDetails(){
        System.out.print("Enter Username: ");
        this.Username=sc.nextLine();
        System.out.print("Enter Password: ");
        this.Password=sc.nextLine();
        System.out.println(" ");
    }

    public void registration(Scanner sc) {
        System.out.print("Enter a username: ");
        this.Username = sc.nextLine();
        System.out.print("Enter a password: ");
        this.Password = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_PATH, true)) { // Append mode
            writer.write(this.Username + "," + this.Password + "\n");
            System.out.println("Registration successful!");
        } 
        catch (IOException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        }
    }

    public void login(Scanner sc) {
        System.out.print("Enter your username: ");
        this.Username = sc.nextLine();
        System.out.print("Enter your password: ");
        this.Password = sc.nextLine();

        boolean loginSuccess = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
        
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) { // line is not empty
                String[] userDetails = line.split(",");
                if (userDetails.length >= 2) { //array has at least 2 elements
                    String storedUsername = userDetails[0];
                    String storedPassword = userDetails[1];

                    if (storedUsername.equals(this.Username) && storedPassword.equals(this.Password)) {
                        loginSuccess = true;
                        break;
                    }
                } else {
                    System.out.println("Invalid user details format in file: " + line);
                }
            }
        }
        } 
        catch (Exception e) {
            System.out.println("An error occurred during login.");
            e.printStackTrace();
        }

        if (loginSuccess) {
            System.out.println("Login successful!");
            Register ra=new Register();
            ra.register(Username);
            ra.writetofile("tanisha\\studentDetail.txt",Username,ra.name,ra.branch,ra.cgpa,ra.back);
            tech t=new tech();
            t.check_eligibility(ra.branch,ra.back,ra.cgpa);
        } 
        else {
            System.out.println("Invalid username or password.\nDo you want to try again? (yes/no)");
            String answer=sc.nextLine();
            if(answer.equalsIgnoreCase("yes")){
                login(sc);
            }
            else{
                System.out.println("\nThankyou");
            }

        }
    }
}
class Register extends User{
    String name;
    String branch;
    String cgpa;
    String back;
    //String Username;

    void register(String Username){
      //Scanner sc=new Scanner(System.in);
      this.Username=Username;
      System.out.println("\n\n        REGISTER FOR PLACEMENT PROCESS 2024-25 ");
      System.out.print("Enter your Name: ");
      this.name=sc.nextLine();
      System.out.print("Enter your Branch: ");
      this.branch=sc.nextLine();
      System.out.print("Enter number of backs:  ");
      this.back=sc.nextLine();
      System.out.print("Enter your CGPA: ");
      this.cgpa=sc.nextLine();
    }
    public void writetofile(String filename,String Username,String name,String branch,String cgpa,String back){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            writer.write("Username: "+this.Username);
            writer.newLine();
            writer.write("Name "+name);
            writer.newLine();
            writer.write("Branch: "+branch);
            writer.newLine();
            writer.write("CGPA: "+cgpa);
            writer.newLine();
            writer.write("Number of backs: "+back);
            writer.newLine();
            writer.write("\n");
            //writer.write(this.Username+","+name+","+branch+","+cgpa+","+back);
            //writer.newLine();
            writer.close();
        }
        catch(IOException e){
            System.err.println("Error writing to file: "+e.getMessage());
        }
    }
}
class Admin extends User{
    public void checkCredentials(){
        if((Username.equalsIgnoreCase("Admin1234")) &&(Password.equals("AdminGs"))){
            System.out.println("Successfull Login");
            suhani s=new suhani();
            s.takeInput();
        }
        else{
            System.out.println("Invalid Credentials");
            getDetails();
            checkCredentials();
        }
    }
}
public class project{
    public void selectUser(){
        Scanner sc=new Scanner(System.in);
        String user;
        System.out.print("Enter User type (Student/Admin): ");
        user=sc.nextLine();
        Admin ad=new Admin();
        Register r=new Register();
        if(user.equalsIgnoreCase("Student")){
            System.out.println("Choose an option: \n 1 for register\n 2 for login.");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:{r.registration(sc);
                    System.out.println("\nEnter details to login further.");
                    r.login(sc);
                }
                break;
                case 2:r.login(sc);
                break;
            }
            //  r.getDetails();
            // r.checkCredentials();
            // r.register(r.Username);
            // r.writetofile("studentDetail.txt",r.Username,r.name,r.branch,r.cgpa,r.back);
            //check_eligibility(r.branch,r.back,r.cgpa);
        }

        else if(user.equalsIgnoreCase("Admin")){
            ad.getDetails();
            ad.checkCredentials();
        }
        else{
            System.out.println("Invalid User, Kindly select a valid user!");
            selectUser();
        }
    }
    public static void main(String[]args) {
        project p=new project();
        p.selectUser();
    }
}