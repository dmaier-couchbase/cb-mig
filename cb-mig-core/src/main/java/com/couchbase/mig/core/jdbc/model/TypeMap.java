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

import java.sql.Types;
import java.util.HashMap;


/**
 * Maps JDBC type codes to Strings
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class TypeMap extends HashMap<Integer , String> {

    /**
     * A single instance of the map
     */
    private static TypeMap instance;
    
    
    public TypeMap() {
        
        this.put(Types.ARRAY,"ARRAY");
        this.put(Types.BIGINT,"BIGINT");
        this.put(Types.BINARY,"BINARY");
        this.put(Types.BIT,"BIT");
        this.put(Types.BLOB,"BLOB");
        this.put(Types.BOOLEAN,"BOOLEAN");
        this.put(Types.CHAR, "CHAR");
        this.put(Types.CLOB, "CLOB");
        this.put(Types.DATALINK, "DATALINK");
        this.put(Types.DATE, "DATE");
        this.put(Types.DECIMAL,"DECIMAL");
        this.put(Types.DISTINCT, "DISTINCT");
        this.put(Types.DOUBLE, "DOUBLE");
        this.put(Types.FLOAT, "FLOAT");
        this.put(Types.INTEGER, "INTEGER");
        this.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
        this.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        this.put(Types.LONGVARBINARY, "LONGVARBINARY");
        this.put(Types.LONGVARCHAR,"LONGVARCHAR");
        this.put(Types.NCHAR,"NCHAR");
        this.put(Types.NCLOB,"NCLOB");
        this.put(Types.NULL,"NULL");
        this.put(Types.NUMERIC,"NUMERIC");
        this.put(Types.NVARCHAR, "NVARCHAR");
        this.put(Types.OTHER,"OTHER");
        this.put(Types.REAL,"REAL");
        this.put(Types.REF,"REF");
        this.put(Types.ROWID,"ROWID");
        this.put(Types.SMALLINT, "SMALLINT");
        this.put(Types.SQLXML,"SQLXML");
        this.put(Types.STRUCT,"STRUCT");
        this.put(Types.TIME,"TIME");
        this.put(Types.TIMESTAMP,"TIMESTAMP");
        this.put(Types.TINYINT,"TINYINT");
        this.put(Types.VARBINARY, "VARBINARY");
        this.put(Types.VARCHAR, "VARCHAR"); 
    }
    
    public static TypeMap getInstance()
    {
        
        if (instance == null) instance = new TypeMap();
        
        return instance;
    }
   
}
