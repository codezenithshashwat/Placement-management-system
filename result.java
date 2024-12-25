package tanisha;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class result{
    String id;
    String name;
    public void showCompany(String cID){
        String FILE_PATH="suhani\\alldetails.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] companyDetails = line.split(",");
            this.id = companyDetails[0];
            this.name = companyDetails[1];

            if (id.equals(cID)) {
                System.out.println("company id: "+this.id);
                System.out.println("company name: "+this.name);
                System.out.println("domain: "+companyDetails[2]);
                System.out.println("job Description: "+companyDetails[3]);
                //matchSuccess=true;
                submit(this.name);
                break;
            }
        }

    } 
    catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    // if (matchSuccess==false) {
    //     System.out.println("Enter valid Company ID\n");
    //     showCompany();
    // } 
    
    }
    void submit(String name){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDo you want to apply in "+name+" ? (yes/no)");
        String ans=sc.nextLine();
        if(ans.equalsIgnoreCase("yes")){
            //Google form link to enter further details will be provided
            System.out.println("Kindly fill the Google form for further process: ");
            System.out.println("https://docs.google.com/forms/d/e/1FAIpQLSci1GCarmLEjGxYwWLzGd9Jn4mO9VIh02qisac7d");
        }
        else {
            System.out.println("\nThankyou");
        }
        sc.close();
    }
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        // System.out.print("Enter the Company ID in which you want to apply: ");
        // int cID = sc.nextInt();
        //result r=new result();
        //r.showCompany();
        
    }
}

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

