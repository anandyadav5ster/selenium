package com.selenium.reports;

import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;

public class read_excel_data{
@Test
public static void readExcel() throws FilloException{
    Fillo fillo = new Fillo();
    // Connection connection = fillo.getConnection("C:\\Users\\Admin\\OneDrive\\Desktop\\Automation\\selenium\\TestData\\testdata.xls");
    Connection connection = fillo.getConnection("C:\\Users\\Admin\\OneDrive\\Desktop\\Automation\\selenium\\TestData\\TestData.xlsx");

    String query = "SELECT * FROM Sheet1 WHERE Run='Yes'";

    Recordset recordset = connection.executeQuery(query);
    while(recordset.next()){
        System.out.println(recordset.getField("username"));
        System.out.println(recordset.getField("password"));
    }
 recordset.close();
        connection.close();
}
}
