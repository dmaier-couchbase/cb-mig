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

package com.couchbase.mig.core.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class describes a relational database
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Database {
    
    //Constants
    public static final String SCHEMA_COL = "TABLE_SCHEM";
    
    
    /**
     * The name of the datbase
     */
    private final String name;
  
    
    /**
     * The schemas
     */
    private List<Schema> schemas;

 
    /**
     * The JDBC connection to the source databse
     */
    private final Connection con;
    
    
    /**
     * The constructor which takes the name and connection as an argument
     * 
     * @param name
     * @param con 
     */
    public Database(String name, Connection con) {
        
        this.con = con;
        this.name = name;
    }
    
   
    public void retSchemas() throws SQLException
    {
        DatabaseMetaData dbMetaData = con.getMetaData();
        
        ResultSet rs = dbMetaData.getSchemas();
        
        while (rs.next())
        {
            
            String schemaName = rs.getString(SCHEMA_COL);
            
            //TODO: Create and add new schema to the list
        }
        
    }

    /**
     * Get the connection
     * 
     * @return 
     */
    public Connection getCon() {
        return con;
    }

    /**
     * Get the name of the source database
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
        
        //TODO converts the model into a string tree
        return super.toString();
    }
    
    
    
}
