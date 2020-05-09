package testers;

import student.honig.roey.RailwayStation;
import student.honig.roey.Time1;
import student.honig.roey.Train;

/**
 * 2020b - 06/03/2020
 */
public class Maman13StudentTester
{
    public static void main(String []args)
    {

        /*******************************  RailwayStation CLASS TESTER *******************************/
        /***********************************************************************************/

        //Check constructor
        RailwayStation rs = new RailwayStation();

        //AddTrain
        Train f1 = new Train("Haifa",12,0,210,250,250,55);
        Train f2 = new Train("Jerusalem",10,50,210,250,250,40);
        rs.addTrain(f1);
        rs.addTrain(f2);
        System.out.println(rs);

        //RemoveTrain
        rs.removeTrain(f1);
        System.out.println(rs);

        //First Train to Destination
        Train f3 = new Train("Tel-Aviv",11,35,180,100,200,35);
        rs.addTrain(f3);
        Train f3a = new Train("Tel-Aviv",7,15,180,200,200,35);
        rs.addTrain(f3a);
        
        System.out.println(rs);
        Time1 t1 = rs.firstDepartureToDestination("Tel-Aviv");
        System.out.println("The first train to Tel-Aviv departs at "+t1);

        //HowMany Full Trains
        int x = rs.howManyFullTrains();
        System.out.println("Full Train - " + x);

        //Most Popular Destination
        String mostPopular = rs.mostPopularDestination();
        System.out.println("Most Popular Destination - " + mostPopular);

        //Most Expensive Ticket
        Train mostExpensive = rs.mostExpensiveTicket();
        System.out.println("Most Expensive Ticket - " + mostExpensive);

        //Longest Train
        Train longestTrain = rs.longestTrain();
        System.out.println("Longest Train - " + longestTrain);

    }
}