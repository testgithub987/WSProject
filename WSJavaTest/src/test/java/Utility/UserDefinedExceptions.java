package Utility;

@SuppressWarnings("serial")
public class UserDefinedExceptions extends Exception
{
   UserDefinedExceptions(String s) {
            super(s);
        }
    UserDefinedExceptions(Exception  ex) {
        super(ex);
    }
}
