/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.couchbase.mig.cli;

import com.couchbase.mig.core.con.jdbc.MySQL;
import com.couchbase.mig.core.model.document.bucket.LogBucket;
import com.couchbase.mig.core.model.jdbc.Database;
import com.couchbase.mig.core.transform.ITransfomer;
import com.couchbase.mig.core.transform.SimpleModelTransformer;
import java.sql.Connection;


/**
 * The command line for the migration tool
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {

    public static void main(String[] args) throws Exception {
   
        if ( args != null && args.length >= 2)
        {
            System.out.println("Starting the migration ...");
            
            String sourceUrl = args[0];
            String transformerName = args[1];
            String targetUrl = "";
            
            if (args.length >= 3) targetUrl = args[2];
         
            
            Connection con = MySQL.getCon(sourceUrl);
            Database db = new Database(con);
            
            ITransfomer transformer = null;
            
            if (transformerName.equals("simple-log"))
            {
                
                transformer = new SimpleModelTransformer(new LogBucket());
                transformer.transform(db);
            }
            
            con.close();
            
        }
        else
        {
            usage();
        }
        
    }
       
    
    private static void usage()
    {
        System.out.println("Use: java -jar cb-mig-cli.jar ${sourceUrl} ${transformerName} [${targetUrl}]");
        System.out.println("");
        System.out.println("Examples:");
        System.out.println("* sourceUrl = jdbc:mysql://192.168.7.162/employees?user=testuser&password=couchbase");
        System.out.println("* transformerName = simple-log");
        System.out.println("* targetUrl = couchbase://192.168.7.162:8091");
    }
    
}
