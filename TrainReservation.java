
package consoleapp;
import java.util.*;
public class TrainReservation {
    static class Train{
        int seats;
        String name;
        int number;
        Train(int number,String name,int seats){
        this.number=number;
        this.name=name;
        this.seats=seats;
        }
    }
    static class Ticket{
        String passenger;
        int trainnumber;
        int id;
        static int counter;
        Ticket(String name,int number){
            this.passenger=name;
            this.trainnumber=number;
            this.id=counter++;
        }
    }
 static Scanner sc=new Scanner(System.in);
 static List<Ticket> tickets=new ArrayList<>();
 static List<Train> trains=new ArrayList<>();
    public static void main(String[] args) {
        boolean option=true;
       
        while(option){
              System.out.println("Ticket Booking Application");
              System.out.println("==========================");
              System.out.println("1.Book Ticket");
              System.out.println("2.Add Train");
              System.out.println("3.View Train");
              System.out.println("4.Cancel Ticket");
              System.out.println("5.View Booked Tickets");
              System.out.println("6.Exit");
              int opt=sc.nextInt();
              switch(opt){
                  case 1:
                      bookTicket();
                      break;
                  case 2:
                      addTrain();
                      break;
                  case 3:
                      viewTrain();
                      break;
                  case 4:
                      cancelTicket();
                      break;
                  case 5:
                      viewTicket();
                      break;
                  case 6:
                      option=false;
                      break;
                  default:
                          System.out.println("Enter valid option");
              }
        }
    }
    static void viewTrain(){
        if(trains.size()==0) System.out.print("No Train Available");
        for(Train t: trains){
            System.out.println("Train name:"+t.name+" train no:"+t.number+"");
        }
    }
    static void addTrain(){
        sc.nextLine();
         System.out.println("Enter Train name:");
         String name=sc.nextLine();
         System.out.println("Enter train Number:");
         int number=sc.nextInt();
         System.out.println("Enter No of seats Available:");
         int seats=sc.nextInt();
         trains.add(new Train(number,name,seats)); 
}
    static void bookTicket(){
        sc.nextLine();
        System.out.println("Enter your name:");
        String name=sc.nextLine();
        System.out.println("Enter Train Number:");
        int number=sc.nextInt();
        for(Train t:trains){
            if(t.number==number){
                if(t.seats>0){
                    t.seats--;
                    tickets.add(new Ticket(name,number));
                    System.out.println("Ticket Booked Successfully!");
                }
                else System.out.println("Ticket Unavailable :(");
            return;
            }
        }
        System.out.print("Train Not Found :(");
    }
    static void cancelTicket(){
        System.out.println("Enter Ticket Id:");
        int id=sc.nextInt();
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i).id==id){
                int trainnumber=tickets.get(i).trainnumber;
                tickets.remove(i);
                for(Train t:trains){
                    if(t.number==trainnumber){
                        t.seats++;
                        break;
                    }
                }
                System.out.println("Ticket Cancelled");
                return;
            }
        }
        System.out.println("Ticket Not Found :(");
    }
    static void viewTicket(){
        if(tickets.size()==0) System.out.print("No Ticket Booked");
        for(Ticket t: tickets){
            System.out.println("Train name:"+t.passenger+" train no:"+t.trainnumber+" Passenger Id:"+t.id);
        }
    }
}
