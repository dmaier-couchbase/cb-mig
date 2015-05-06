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

import com.couchbase.mig.core.helper.StringHelper;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class describes a relational database
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Database {
    
    //Constants
    public static final String TABLE_SCHEM = "TABLE_SCHEM";
    public static final String TABLE_CATALOG = "TABLE_CATALOG";
    
    
    /**
     * The name of the datbase
     */
    private final String url;
  
    
    /**
     * The schemas
     */
    private final Map<String, Schema> schemas = new HashMap<>();

 
    /**
     * The JDBC connection to the source databse
     */
    private final Connection con;
    
    
    /**
     * The constructor which takes the name and connection as an argument
     * 
     * @param con 
     * @throws java.sql.SQLException 
     */
    public Database(Connection con) throws SQLException {
        
        this.con = con;
        this.url = con.getMetaData().getURL();
        
        retSchemaMetaData();
    }
    
    /**
     * Retrive the schema information of this database
     * 
     * @throws SQLException 
     */
    private void retSchemaMetaData() throws SQLException
    {
        //Get the schemas
        DatabaseMetaData dbMeta = this.con.getMetaData();
        ResultSet rs = dbMeta.getSchemas();
        
        while (rs.next())
        {
            Schema s = new Schema(this);
            
            String sName = rs.getString(TABLE_SCHEM);
            String sCat = rs.getString(TABLE_CATALOG);
            
            s.setName(sName);
            s.setCatalog(sCat);
            
            String key = sName;
            
            if (StringHelper.isDefined(sCat))
            {
               key = sCat + "." + sName;
            }

            this.schemas.put(key, s);
        }
        
        rs.close();
        
        //If there is no schema then add a default one
        if (this.schemas.isEmpty()) this.schemas.put("default", new Schema(this));

    }
    
    //Getters and Setters
    public Connection getCon() {
        return con;
    }

    public String getUrl() {
        return url;
    }


    public Map<String, Schema> getSchemas() {
        return schemas;
    }

    @Override
    public String toString() {
        
        return this.url;
    }
        
    
    
}
