package com.company;

import java.sql.*;

public class DbWorker {
    private final String url = "jdbc:postgresql://localhost:5432/sports";
    private final String user = "postgres";
    private final String password = "postgres";
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void writeInColumn(String a, int maxLen){
        int b=maxLen+10-a.length();
        System.out.print(a);
        for (int i=0; i<b; i++) {
            System.out.print(" ");
        }
    }

    public int maxLen(String table, String row){
        int maxlen=0;
        try (Connection co=connect();){
            String sql="SELECT max(length("+row+")) FROM "+table+";";
            Statement st=co.createStatement();
            ResultSet rs=st.executeQuery(sql);
            rs.next();
            maxlen=rs.getInt(1);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return maxlen;
    }

    public int maxLenInt(String table, String row){
        int maxlen=0;
        try (Connection co=connect();){
            String sql="SELECT max("+row+") FROM "+table+";";
            Statement st=co.createStatement();
            ResultSet rs=st.executeQuery(sql);
            rs.next();
            maxlen=String.valueOf(rs.getInt(1)).length();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return maxlen;
    }

    public void selectSports(){
        try (Connection co=connect();){
            writeInColumn("Id_sport", maxLenInt("sports", "id_sport"));
            writeInColumn("Sport_type",maxLen("sports", "sport_type"));
            writeInColumn("Salary", maxLenInt("sports", "salary"));
            System.out.println();
            writeInColumn("--------", maxLenInt("sports", "id_sport"));
            writeInColumn("----------", maxLen("sports", "sport_type"));
            writeInColumn("------", maxLenInt("sports", "salary"));
            System.out.println();
            String sql="SELECT * FROM sports;";
            Statement st=co.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                writeInColumn(String.valueOf(rs.getInt(1)), maxLenInt("sports", "id_sport"));
                writeInColumn(rs.getString(2), maxLen("sports", "sport_type"));
                writeInColumn(String.valueOf(rs.getInt(3)), maxLenInt("sports", "salary"));
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void selectTrainers(){
        try (Connection co=connect();){
            writeInColumn("Id", maxLenInt("trainers", "trainer_id"));
            writeInColumn("Name", maxLen("trainers", "trainer_full_name"));
            writeInColumn("Sport_type", maxLen("trainers", "sport_type"));
            writeInColumn("Phone", maxLenInt("trainers", "phone_number"));
            writeInColumn("INN", maxLenInt("trainers", "inn"));
            writeInColumn("id_sport", maxLenInt("trainers", "id_sport"));
            System.out.println();
            writeInColumn("--", maxLenInt("trainers", "trainer_id"));
            writeInColumn("----", maxLen("trainers", "trainer_full_name"));
            writeInColumn("----------", maxLen("trainers", "sport_type"));
            writeInColumn("-----", maxLenInt("trainers", "phone_number"));
            writeInColumn("---", maxLenInt("trainers", "inn"));
            writeInColumn("--------", maxLenInt("trainers", "id_sport"));
            System.out.println();
            String sql="SELECT * FROM trainers;";
            Statement st=co.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                writeInColumn(String.valueOf(rs.getInt(1)), maxLenInt("trainers", "trainer_id"));
                writeInColumn(rs.getString(2), maxLen("trainers", "trainer_full_name"));
                writeInColumn(rs.getString(3), maxLen("trainers", "sport_type"));
                writeInColumn(String.valueOf(rs.getInt(4)), maxLenInt("trainers", "phone_number"));
                writeInColumn(String.valueOf(rs.getInt(5)), maxLenInt("trainers", "inn"));
                writeInColumn(String.valueOf(rs.getInt(6)), maxLenInt("trainers", "id_sport"));
                System.out.println();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
