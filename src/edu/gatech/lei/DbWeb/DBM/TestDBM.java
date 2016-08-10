package edu.gatech.lei.DbWeb.DBM;



import java.util.ArrayList;

import edu.gatech.lei.DbWeb.DataObjs.Student;

public class TestDBM{

    private static DatabaseManager dbm;

    public static void main(String[] args){
       
        dbm = new DatabaseManager();
        dbm.initializeDB();

       
	}
}

