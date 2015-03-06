/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
