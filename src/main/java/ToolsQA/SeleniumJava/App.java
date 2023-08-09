package ToolsQA.SeleniumJava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Logger logger =  LogManager.getLogger(App.class);
        
        logger.info("this is a infor");
        logger.error("this is a error");
        
        System.out.println( "finish" );
    }
}
