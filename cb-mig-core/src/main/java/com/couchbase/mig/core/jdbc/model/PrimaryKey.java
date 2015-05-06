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

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a Primary Key
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class PrimaryKey  {

    /**
     * The table to which this PK belongs
     */
    private final Table table;
    
    /**
     * The columns of a primary key
     */
    private final List<Column> cols  = new ArrayList<>();
    
    /**
     * The constructor which takes the parent table into account
     * 
     * @param table 
     */
    public PrimaryKey(Table table) {
        
        this.table = table;
    }
    
    /**
     * To a add a key part
     * 
     * @param key 
     */
    public void addKey(Column key)
    {
       this.cols.add(key);
    }
    
    /**
     * To get all key parts
     * 
     * @return 
     */
    public List<Column> getKeys()
    {
        return this.cols;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("[");
        
        int i = 0;
        
        for (Column column : cols) {
            
            if (i != 0) sb.append(",");
            sb.append(column.getName());
            i++;
        }
        
        sb.append("]");
        
        return sb.toString();
        
    }   
    
}
