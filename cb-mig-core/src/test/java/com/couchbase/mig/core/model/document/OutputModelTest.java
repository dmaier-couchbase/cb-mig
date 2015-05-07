package com.couchbase.mig.core.model.document;

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
public class OutputModelTest {
    
    public OutputModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
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
    public void testCreateModel() throws Exception {
     
        Bucket b = new Bucket("test");
         
        Document doc = new Document();
        doc.getProps().put("uid", new Value("dmaier"));
        doc.getProps().put("first_name", new Value("David"));
        doc.getProps().put("last_name", new Value("Maier"));
        doc.getProps().put("email", new Value("david.maier@couchbase.com"));
        doc.getProps().put("bday", new Value(123L));
        doc.getProps().put("distance", new Value(1.13456));
        doc.getProps().put("none", new Value(new Null()));
        
        
        Array<String> companies = new Array<>();
        companies.add(new Value("comp::couchbase.com"));
        companies.add(new Value("comp::nosqlgeek.org"));
        
        doc.getArrays().put("companies", companies);
        
      
        b.set("user::dmaier", doc);
     
        System.out.println("+-" + b.getName());
        
        Map<String, Document> all = b.getAll();
  
        for (Map.Entry<String, Document> entry : all.entrySet()) {
            
            String key = entry.getKey();
            Document document = entry.getValue();
            
            System.out.println("+--" + key + " : ");
            
            System.out.println("+--" + document.toString());
        }
        
     }
}
