package suhani;
import java.io.*;
import java.util.Scanner;
abstract class Company {
    int cid;
    String cname;
    String jd;
    String branch;
    String domain;
    double cgpa;
    int back;
    abstract void setEligibility(String branch, int back, double cgpa);

    void writetofile(String filename,int cid,String cname,String jd,String branch,String domain,double cgpa,int back){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            // data format in allDetails file: company id,company name,domain,job description,branch allowed,cgpa,back
            // data of new company in new line
            
            writer.write(cid+","+cname+","+domain+","+jd+","+branch+","+cgpa+","+back);
            writer.newLine();
            writer.close();
        }
        catch(IOException e){
            System.err.println("Error writing to file: " + e.getMessage());

        }
    }
    void writetofile(String filename,int cid,String domain,String branch,double cgpa,int back){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))){
            // data format in setEligibilty file: company id,domain,branch,cgpa,back
            // data of new company in new line
            writer.write(cid+","+domain+","+branch+","+cgpa+","+back);
            writer.newLine();
            writer.close();
        }
        catch(IOException e){
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
class NonTechCompany extends Company {
    void setEligibility(String branch, int back, double cgpa) {
        this.domain = "nontech";
        this.branch = branch;
        this.cgpa = cgpa;
        this.back = back;
        writetofile("suhani\\seteligibility.txt",cid,domain,branch,cgpa,back);
        
    }
}
class TechCompany extends Company {
    void setEligibility(String branch, int back, double cgpa) {
        this.domain = "tech";
        this.branch = branch;
        this.cgpa = cgpa;
        this.back = back;
        writetofile("suhani\\seteligibility.txt",cid,domain,branch,cgpa,back);
        
}}
public class suhani
{
    public void takeInput(){
        Scanner sc = new Scanner(System.in);
        String ans = "yes";

        while (ans.equalsIgnoreCase("yes")) {
            System.out.print("\n\nEnter the domain of your company (nontech/tech): ");
            String dom = sc.nextLine().trim();
            Company company = null;
            if (dom.equalsIgnoreCase("nontech")) {
                company = new NonTechCompany();
            } 
            else if (dom.equalsIgnoreCase("tech")) {
                company = new TechCompany();
            } 
            else {
                System.out.println("Kindly enter a valid domain: (nontech/tech)");
                continue;  
            }

            try {
                System.out.print("Enter company id: ");
                company.cid = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter company name: ");
                company.cname = sc.nextLine();

                System.out.print("Enter company's job description: ");
                company.jd = sc.nextLine();

                System.out.print("Enter allowed branch: ");
                String br = sc.nextLine();

                System.out.print("Enter minimum CGPA to be scored for eligibility: ");
                double cg = sc.nextDouble();

                System.out.print("Enter number of semester backs allowed: ");
                int backs = sc.nextInt();
                sc.nextLine(); 

                company.setEligibility(br, backs, cg);
                company.writetofile("suhani\\alldetails.txt",company.cid,company.cname,company.jd,br,dom,cg,backs);
            } 
            catch (Exception e) {
                System.out.println("Invalid input, please enter the data again.");
                sc.nextLine();  
                continue; 
            }

            System.out.print("\n   Do you want to set criteria for more companies (yes/no): ");
            ans = sc.nextLine().trim();
        }
        sc.close();

    }
    public static void main(String[] args) {
       suhani s= new suhani();
       s.takeInput();
    }
}
