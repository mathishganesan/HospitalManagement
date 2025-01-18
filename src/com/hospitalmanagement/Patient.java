package com.hospitalmanagement;
import java.sql.*;
import java.util.Scanner;
public class Patient {
        private Connection connection;
        private Scanner scn;
        public Patient(Connection connection,Scanner scn) {
            this.connection=connection;
            this.scn=scn;
        }
        public void addPatient()
        {
            System.out.println("Enter the Patient Details ");
            System.out.println("Enter Patient Name : ");
            String Name=scn.next();
            System.out.println("Enter Patient Age : ");
            int age=scn.nextInt();
            System.out.println("Enter Patient Gender : ");
            String gender=scn.next();
            try {
                String query="INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1,Name);
                ps.setInt(2,age);
                ps.setString(3,gender);
                int affectedrows=ps.executeUpdate();
                if(affectedrows>0)
                {
                    System.out.println("Patient Added");
                }
                else
                {
                    System.out.println("Failed to Add");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void viewpatient(){
            String query="SELECT * from patients";
            try{
                PreparedStatement ps=connection.prepareStatement(query);
                ResultSet r=ps.executeQuery();
                System.out.println("Patients");
                System.out.println("______*****_____");
                System.out.println("|Patient ID|  Name  | Age | Gender |");
                while(r.next())
                {
                    int id=r.getInt("id");
                    String name=r.getString("name");
                    int age=r.getInt("age");
                    String gender=r.getString("gender");
                    System.out.printf("|%-10s|%-8s|%-8s|%-10s|\n",id,name,age,gender);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public boolean getPatientId(int id){
            String query="SELECT * FROM patients where id=?";

            try {
                PreparedStatement ps= connection.prepareStatement(query);
                ps.setInt(1,id);
                ResultSet r=ps.executeQuery();
                if(r.next()){
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }

}
