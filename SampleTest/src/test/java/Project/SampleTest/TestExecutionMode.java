package Project.SampleTest;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import jxl.Sheet;

public class TestExecutionMode 
{
    public List<String> getExecutionType(Sheet worksheetObj, Integer requestRowCount, String executeMode) throws IOException 
    {
        List<String> testCasesToExecuteList = new ArrayList<String>();
        // Get all the test cases that are good to execute and add to List
        switch (executeMode) {
            case "ALL":
                for (int rowCount = 1; rowCount < requestRowCount; rowCount++) {
                    testCasesToExecuteList.add(worksheetObj.getCell(0, rowCount).getContents());
                }
                System.out.println("list with TC ids" + testCasesToExecuteList);
                break; // optional
            case "YES":
                for (int rowCount = 1; rowCount < requestRowCount; rowCount++) {
                    String executeFlag = worksheetObj.getCell(1, rowCount).getContents();
                    System.out.println(executeFlag);
                    if (executeFlag.toUpperCase().equals("YES")) {
                        testCasesToExecuteList.add(worksheetObj.getCell(0, rowCount).getContents());
                    }
                }
                System.out.println("list with TC ids" + testCasesToExecuteList);
                break;                
            case "NO":
                for (int rowCount = 1; rowCount < requestRowCount; rowCount++) {
                    String executeFlag = worksheetObj.getCell(1, rowCount).getContents();
                    System.out.println(executeFlag);
                    if (executeFlag.toUpperCase().equals("NO")) {
                        testCasesToExecuteList.add(worksheetObj.getCell(0, rowCount).getContents());
                    }
                }
                System.out.println("list with TC ids" + testCasesToExecuteList);
                break;                 
            // You can have any number of case statements.
            default: // Optional
            {
                System.out.println("Execution Criteria with selected option does not exist.Please enter proper criteria in Config.properites file");
               /* throw new UserDefinedExceptions("Wrong input parameteres");*/
            }
        }  //end of switch
        return testCasesToExecuteList;
    }
}
