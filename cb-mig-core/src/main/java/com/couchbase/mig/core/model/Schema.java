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

import java.util.List;

/**
 *
 *  This class describes a relational database schema
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Schema {
    
    /**
     * The database of the schema
     */
    private Database db;
    
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
    private List<Table> table;

    /**
     * The constructor of a schema
     * 
     * @param name
     * @param db 
     */
    public Schema(String name, Database db) {
 
        this.db = db;
        this.name = name;
        
        //TODO: Get the schema operations
        //-- retTables( ... )
 
    }
    
    
    
    
    
}
