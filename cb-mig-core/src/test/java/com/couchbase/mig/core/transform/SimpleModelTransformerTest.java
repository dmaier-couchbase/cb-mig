package com.couchbase.mig.core.transform;

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



import com.couchbase.mig.core.model.document.bucket.IBucket;
import com.couchbase.mig.core.model.document.bucket.LogBucket;
import com.couchbase.mig.core.model.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class SimpleModelTransformerTest {
    
    static Connection con;
    
    
    public SimpleModelTransformerTest() {
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
    public void testTransformModel() throws SQLException {
     
         Database db = new Database(con);
         
         SimpleModelTransformer transformer = new SimpleModelTransformer(new LogBucket());
         
         
         IBucket b = transformer.transform(db);
     
         System.out.println("");
     }
}
