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

package com.couchbase.mig.core.jdbc.model;

import com.couchbase.mig.core.helper.StringHelper;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class describes a Table
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Table {
   
    //Constants
    public final static String COLUMN_DEF = "COLUMN_DEF";
    public final static String COLUMN_NAME = "COLUMN_NAME";
    public final static String COLUMN_SIZE = "COLUMN_SIZE";
    public final static String DATA_TYPE = "DATA_TYPE";
    public final static String IS_NULLABLE = "IS_NULLABLE";
    public final static String ORDINAL_POSITION = "ORDINAL_POSITION";
    public final static String REMARKS = "REMARKS";
    public final static String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    public final static String KEY_SEQ = "KEY_SEQ";
    public final static String FKCOLUMN_NAME = "FKCOLUMN_NAME";
    public final static String PKTABLE_CAT = "PKTABLE_CAT";
    public final static String PKTABLE_SCHEM = "PKTABLE_SCHEM";
    public final static String PKTABLE_NAME = "PKTABLE_NAME";
    public final static String PKCOLUMN_NAME = "PKCOLUMN_NAME";
    
    
    /**
     * The underlying database connection
     */
    private final Connection con;
    
    /**
     * The related schema
     */
    private final Schema schema;
    
   
    /**
     * The name of the table
     */
    private String name;
    
    /**
     * The type of the table
     */
    private String type;
    
    /**
     * The remarks of the table
     */
    private String remarks;
    
    /**
     * The columns of the table
     */
    private final Map<String, Column> cols = new HashMap<>();

    
    /**
     * The Primary key of this table
     */
    private final PrimaryKey pk = new PrimaryKey(this);
    
    
    /**
     * The foreign keys of the table
     */
    private final Map<String, ForeignKey> fks = new HashMap<>();
    
    
    /**
     * The default constructor
     * @param name
     * @param schema
     * @throws java.sql.SQLException
     */
    public Table(String name, Schema schema) throws SQLException {
        
        this.name = name;
        this.schema = schema;
        this.con = schema.getDb().getCon();
    
        retColMetaData();
        retPrimaryKeys();
        retForeignKeys();
    }
    
    /**
     * To retrieve the column meta data
     */
    private void retColMetaData() throws SQLException
    {
        
        DatabaseMetaData dbMeta = con.getMetaData();
        
        ResultSet rs = dbMeta.getColumns(this.schema.getCatalog(), this.schema.getName(), this.name, "%");
        
        while (rs.next())
        {
            Column c = new Column(this);
            
            String colName = rs.getString(COLUMN_NAME);
            
            c.setName(colName);
            c.setColDefault(rs.getString(COLUMN_DEF));
            c.setSize(rs.getInt(COLUMN_SIZE));
            c.setType(TypeMap.getInstance().get(rs.getInt(DATA_TYPE)));
            c.setNullable(rs.getString(IS_NULLABLE));
            c.setPos(rs.getInt(ORDINAL_POSITION));
            c.setRemarks(rs.getString(REMARKS));
            c.setDecDigits(rs.getInt(DECIMAL_DIGITS));
            
            cols.put(colName, c);
        }
        
        rs.close();
    }
    
    
    /**
     * To retrieve the primary keys of the table
     */
    private void retPrimaryKeys() throws SQLException
    {
        DatabaseMetaData dbMeta = con.getMetaData();
        
        ResultSet rs = dbMeta.getPrimaryKeys(this.schema.getCatalog(), this.schema.getName(), this.name);
        
        
        while (rs.next())
        {
            Column c = new Column(this);
            c.setName(rs.getString(COLUMN_NAME));
            c.setPos(rs.getInt(KEY_SEQ));
            
            this.pk.addKey(c);
        }
        
        rs.close();
    }
    
    /**
     * To retrieve the foreign keys of the table
     * 
     * @throws SQLException 
     */
    private void retForeignKeys() throws SQLException
    {
        
        DatabaseMetaData dbMeta = con.getMetaData();
        
        ResultSet rs = dbMeta.getImportedKeys(this.schema.getCatalog(), this.schema.getName(), this.name);
        
        
        while (rs.next())
        {
            ForeignKey fk = new ForeignKey(this);
                    
            
            fk.setName(rs.getString(FKCOLUMN_NAME));
            fk.setPos(rs.getInt(KEY_SEQ));
            fk.setRefTableCatalog(rs.getString(PKTABLE_CAT));
            fk.setRefTableSchema(rs.getString(PKTABLE_SCHEM));
            fk.setRefTableName(rs.getString(PKTABLE_NAME));
            fk.setRefColumnName(rs.getString(PKCOLUMN_NAME));
            
            String key = fk.getRefTableName() + "." + fk.getRefColumnName();
            
            if (StringHelper.isDefined(fk.getRefTableSchema()))
            {
                key = fk.getRefTableSchema() + "." + key;
            }
            
            if (StringHelper.isDefined(fk.getRefTableCatalog()))
            {
                key = fk.getRefTableCatalog() + "." + key;
            }
            
            fks.put(key, fk);
        }
        
        rs.close();
    }
    

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRemarks() {
        return remarks;
    }

    public Schema getSchema() {
        return schema;
    }

    public Map<String, Column> getCols() {
        return cols;
    }

    public PrimaryKey getPk() {
        return pk;
    }

    public Map<String, ForeignKey> getFks() {
        return fks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        
        return name;
    }
}
