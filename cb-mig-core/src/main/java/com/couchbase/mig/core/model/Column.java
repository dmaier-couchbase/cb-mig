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

/**
 * This class describes a table column
 *  
 * @author David Maier <david.maier at couchbase.com>
 */
public class Column {
    
    /**
     * The table to which this column belongs
     */
    private final Table table;

    /**
     * The underlying database connection
     */
    private final Connection con;
    
    /**
     * The default value for this column
     */
    private String colDefault;
    
    /**
     * The name of the column
     */
    private String name;
    
    /**
     * The size of the column
     */
    private int size;
    
    /**
     * The number of decimial digits
     */
    private int decDigits;
    
    /**
     * The type of the column, expressed as integer constant
     */
    private String type;
    
    /**
     * The information if NULL values are allowed 
     */
    private String nullable;
    
    /**
     * The position of the column
     */
    private int pos;
    
    /**
     * The column remarks
     */
    private String remarks;
     
    
    /**
     * The constructor
     * 
     * @param table 
     */
    public Column(Table table) {

        this.table = table;
        this.con = this.table.getSchema().getDb().getCon();
    }    

    //Getters and Setters
    public Table getTable() {
        return table;
    }

    public String getColDefault() {
        return colDefault;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getNullable() {
        return nullable;
    }

    public int getPos() {
        return pos;
    }

    public int getDecDigits() {
        return decDigits;
    }
    
    

    public String getRemarks() {
        return remarks;
    }

    public void setColDefault(String colDefault) {
        this.colDefault = colDefault;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    } 

    public void setDecDigits(int decDigits) {
        this.decDigits = decDigits;
    }   

    @Override
    public String toString() {
       
        return this.name + ":" + this.type + "(" + this.size + ")";
    }
    
}
