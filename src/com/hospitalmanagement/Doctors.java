package com.hospitalmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {
    private Connection connection;
    public Doctors(Connection connection){
        this.connection=connection;
    }
    public void viewDoctor(){
        String query="SELECT * FROM doctors";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ResultSet r=ps.executeQuery();
            System.out.println("Doctors");
            System.out.println("____****____");
            System.out.println("|Doctor id |Name |Department");
            while(r.next()){
                int id=r.getInt("id");
                String name=r.getString("name");
                String department=r.getString("dept");
                System.out.printf("| %-10s | %-7s | %-14s|\n",id,name,department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean getDoctorId(int id){
        String query="SELECT * FROM doctors WHERE id=?";
        try {
            PreparedStatement p=connection.prepareStatement(query);
            p.setInt(1,id);
            ResultSet r=p.executeQuery();
            if (r.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
