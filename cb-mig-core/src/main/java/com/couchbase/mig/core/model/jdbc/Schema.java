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

package com.couchbase.mig.core.model.jdbc;

import com.couchbase.mig.core.helper.StringHelper;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *  This class describes a relational database schema
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Schema {
    
    //Constants
    public static final String TABLE_NAME = "TABLE_NAME";
    public static final String TABLE_TYPE = "TABLE_TYPE";
    public static final String REMARKS = "REMARKS";
    public static final String TYPE_VIEW="VIEW";
    public static final String TYPE_TABLE="TABLE";
    public static final String TYPE_SYSTABLE="SYSTEM TABLE";
    
    /**
     * The JDBC connection to the source databse
     */
    private final Connection con;
    
    /**
     * The database of the schema
     */
    private final Database db;
    
    /**
     * The name of the schema
     */
    private String name;
    
    /**
     * The catalog which is related to this schema
     */
    private String catalog;
    
   
    /**
     * The associated tables
     */
    private final Map<String, Table> tables = new HashMap<>();
    private final Map<String, Table> sysTables = new HashMap<>();
    private final Map<String, Table> views = new HashMap<>();

    /**
     * The constructor of a schema
     * 
     * @param db 
     * @throws java.sql.SQLException 
     */
    public Schema(Database db) throws SQLException {
 
        this.db = db;
        this.con = db.getCon();
        
        retTableMetaData();
        
    }

    /**
     * Retrieve the table meta data
     */
    private void retTableMetaData() throws SQLException
    {
        DatabaseMetaData dbMeta = con.getMetaData();
        
        ResultSet rs = dbMeta.getTables(this.catalog, this.name, "%", null);
        
        while (rs.next())
        {
            String tableName = rs.getString(TABLE_NAME);
            
            Table t = new Table(tableName, this);
            
            String type = rs.getString(TABLE_TYPE);
            
            
            t.setRemarks(rs.getString(REMARKS));
            t.setName(tableName);
            t.setType(type);
            
            if (type.equals(TYPE_TABLE))
            {
                tables.put(tableName, t);
            }
            
            if (type.equals(TYPE_SYSTABLE))
            {
                sysTables.put(tableName, t);
            }
            
            if (type.equals(TYPE_VIEW))
            {
                views.put(tableName, t);
            }
            
        }
        
        rs.close();
    }
    
    
    //Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public String getCatalog() {
        return catalog;
    }    

    public Database getDb() {
        return db;
    }
    public Map<String, Table> getTables() {
        return tables;
    }

    public Map<String, Table> getSysTables() {
        return sysTables;
    }

    public Map<String, Table> getViews() {
        return views;
    }

    @Override
    public String toString() {
    
        
        String fqn = StringHelper.createFqn(catalog, name);
        
        if (!StringHelper.isDefined(fqn)) fqn = "default";
        
        return fqn;
    }
    
    
}
