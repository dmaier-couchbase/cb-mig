package com.couchbase.mig.core.model.jdbc;

/*
 * Copyright 2015 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class ModelTest {
    
    static Connection con;
    
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        con =  DriverManager.getConnection("jdbc:mysql://192.168.7.162/employees?user=testuser&password=couchbase");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPrintModel() throws SQLException {
     
         Database db = new Database(con);
         
         System.out.println("+- " + db.toString());
         
         Map<String, Schema> schemas = db.getSchemas();
         
         for (Map.Entry<String, Schema> entry : schemas.entrySet()) {

             Schema schema = entry.getValue();
             
             System.out.println("+-- " + schema);
             
             Map<String, Table> tables = schema.getTables();
             
             for (Map.Entry<String, Table> entry1 : tables.entrySet()) {

                 Table table = entry1.getValue();
              
                 System.out.println("+--- " + table);
                 
                 
                 PrimaryKey pk = table.getPk();
                 
                 System.out.println("+---- " + pk);
                 
                 Map<String, ForeignKey> fks = table.getFks();
                 
                 for (Map.Entry<String, ForeignKey> entry2 : fks.entrySet()) {
                     
                     ForeignKey foreignKey = entry2.getValue();
                     
                     System.out.println("+---- " + foreignKey);
                 }
     
                 
                 Map<String, Column> cols = table.getCols();
                 
                 for (Map.Entry<String, Column> entry2 : cols.entrySet()) {
                  
                     Column column = entry2.getValue();
                     
                     System.out.println("+---- " + column);
                 }
             }
         }
         
     
     }
}
