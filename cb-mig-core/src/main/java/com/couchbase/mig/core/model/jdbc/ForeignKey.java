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

/**
 * Describes a Foreign Key
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class ForeignKey extends Column {
 
       
    /**
     * The referenced table catalog
     */
    private String refTableCatalog;
    
    /**
     * The referenced table schema
     */
    private String refTableSchema;
        
    /**
     * The refernced table name
     */
    private String refTableName;
    
    /**
     * The referenced column name
     */
    private String refColumnName;

    
    
    public ForeignKey(Table table) {
        super(table);
    }
    
    //Getters and Setters
    public String getRefTableCatalog() {
        return refTableCatalog;
    }

    public String getRefTableSchema() {
        return refTableSchema;
    }

    public String getRefTableName() {
        return refTableName;
    }

    public String getRefColumnName() {
        return refColumnName;
    }

    public void setRefTableCatalog(String refTableCatalog) {
        this.refTableCatalog = refTableCatalog;
    }

    public void setRefTableSchema(String refTableSchema) {
        this.refTableSchema = refTableSchema;
    }

    public void setRefTableName(String refTableName) {
        this.refTableName = refTableName;
    }

    public void setRefColumnName(String refColumnName) {
        this.refColumnName = refColumnName;
    }

    @Override
    public String toString() {
   
        String fqn = this.refTableName + "." + this.refColumnName;
        
        if (StringHelper.isDefined(refTableSchema))
        {
            fqn = refTableSchema + "." + fqn;
            if (StringHelper.isDefined(refTableCatalog)) fqn = refTableCatalog + "." + fqn;
        }
        
        return this.getName() + " -> " + fqn;
    }
   
    
}
