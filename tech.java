package shashwat;
import java.io.*;
import java.util.*;
import tanisha.result;

abstract class Eligibility{

   abstract void check_eligibility(String branch, String back, String CGPA);
   abstract void view_JD(String cid);
   abstract  void display(String cid);

}
public class tech extends Eligibility{
String arr[]=new String[100];
    public void check_eligibility( String branch, String back, String CGPA){
        String FILE_PATH="suhani\\seteligibility.txt";
        
        int i=0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
        
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) { // line is not empty
                String[] coDetails = line.split(",");
                if (coDetails.length >= 5) { //array has at least 5 elements
                    String cid= coDetails[0];
                    //String domain= coDetails[1];
                    String br= coDetails[2];
                    String cg= coDetails[3];
                    String ba= coDetails[4];
                    if(back.equalsIgnoreCase(ba)){
                        if(branch.equalsIgnoreCase(br)){
                            if(Double.parseDouble(CGPA.trim())>=Double.parseDouble(cg.trim())){
                                
                                arr[i]=cid;
                                i++;
                            }
                        }
                    }
                    if (Integer.parseInt(back.trim())>=2){
                        System.out.println("You are not elegible to take part in placement activities");

                    }

                    
                }
            }
        }
        for(int j=0;j<i;j++) {
            display(arr[j]);
        }
        Scanner sc=new Scanner(System.in);
        System.out.print("\nTo view complete details of the companies enter yes otherwise no: ");
        String ans=sc.nextLine();
        if(ans.equalsIgnoreCase("yes")){
            for(int j=0;j<i;j++) {
                view_JD(arr[j]);
            }
        }
        finalcall(arr);
        sc.close();
        //else{
        //result()
        //}

        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void finalcall(String arr[]){
        while (true){
        System.out.print("Enter ID of company you want to apply in: ");
        Scanner sc=new Scanner(System.in);
        String ID=sc.nextLine();
        int c=0;
        for(int k=0;k<arr.length;k++){
            if(ID.equalsIgnoreCase(arr[k])){
                c++;
            }
        }
        if(c!=0){
            System.out.println("success");
            result rr=new result();
            rr.showCompany(ID);
            break;
        }
        else{
            System.out.println("Enter valid company id.\nDo you want to try again? (yes/no) ");
            String a=sc.nextLine();
            sc.close();

            if(a.equalsIgnoreCase("yes")){
                continue;
            }
            else{
                System.out.println("\n Thankyou");
                break;
            }
        }
    
    }
    
    }

    void view_JD(String id){
        //System.out.println ("frgneg");
        String FILE_PATH="suhani\\alldetails.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
        
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) { // line is not empty
                String[] comDetails = line.split(",");
                if (comDetails.length >= 5) { //array has at least 5 elements
                    String cid= comDetails[0];
                    String name= comDetails[1];
                    
                    if (id.equalsIgnoreCase(cid)) {
                        System.out.println("company id: "+id);
                        System.out.println("company name: "+name);
                        System.out.println("domain: "+comDetails[2]);
                        System.out.println("job description: "+comDetails[3]);
                        System.out.println("\n");

                    }                  
                }
            }
        }
        
        }
        catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

        void display(String id){
        String FILE_PATH="suhani\\alldetails.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
        
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) { // line is not empty
                String[] comDetails = line.split(",");
                if (comDetails.length >= 5) { //array has at least 5 elements
                    String cid= comDetails[0];
                    String name= comDetails[1];
                    
                    if (id.equalsIgnoreCase(cid)) {
                        System.out.println("company : "+cid+" "+name);
                    }                  
                }
            }
        } 
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        tech t=new tech();
        t.check_eligibility("CSE","0", "7.7");
        //Main m=new Main();
       // t.check_eligibility("IT",0, 8.7);
      //  t.check_eligibility("CSE",0, 6.7);
    }
}