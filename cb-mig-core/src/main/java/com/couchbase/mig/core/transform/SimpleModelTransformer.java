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

package com.couchbase.mig.core.transform;

import com.couchbase.mig.core.model.document.bucket.CacheBucket;
import com.couchbase.mig.core.model.document.Document;
import com.couchbase.mig.core.model.document.bucket.IBucket;
import com.couchbase.mig.core.model.document.bucket.LogBucket;
import com.couchbase.mig.core.model.document.Value;
import com.couchbase.mig.core.model.jdbc.Column;
import com.couchbase.mig.core.model.jdbc.Database;
import com.couchbase.mig.core.model.jdbc.Schema;
import com.couchbase.mig.core.model.jdbc.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *  A simple transformer which takes the input Database and returns an output Bucket
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class SimpleModelTransformer<T extends IBucket> implements ITransfomer {

    /**
     * The bucket which is used
     */
    private IBucket bucket;
    
    
    public SimpleModelTransformer(T bucket)
    {
        this.bucket = bucket;
    }
    
    private static final Logger LOG = Logger.getLogger(SimpleModelTransformer.class.getName());
    
    @Override
    public IBucket transform(Database db) throws SQLException
    {        
       
        Map<String, Schema> schemas = db.getSchemas();
        
        //Iterate over all schemas
        for (Map.Entry<String, Schema> schemaEntry : schemas.entrySet()) {
            
            Schema schema = schemaEntry.getValue();
             
            //Iterate over all tables
            Map<String, Table> tables = schema.getTables();
            
            for (Map.Entry<String, Table> tableEntry : tables.entrySet()) {
                
                String keyPrefix = "";
                
                Table table = tableEntry.getValue();
                
                 
                //Build the fully qualified name of the table
                String fqn = table.getName();
                if (schema.getName() != null) fqn = schema.getName() + "." + table.getName();
                          
                //Use the table name as part of the key
                keyPrefix = keyPrefix + fqn + "::";
                
                //SELECT all data from the table and transform them to JSON the
                ResultSet rs = db.getCon().createStatement().executeQuery("SELECT * FROM " + fqn);
                
                while (rs.next())
                {   
                    //Init a document 
                    Document doc = new Document();
                    String key = keyPrefix;
                    
                     //Use the primary key column values to build the key
                     List<Column> pks = table.getPk().getKeys();
                     
                     int i = 0;
                     
                     for (Column pk : pks) {
                       
                         if (i != 0) key = key + "::";
                         
                         key = key + TypeConverter.convert( pk.getType(), rs.getObject(pk.getName())).toString();
                          
                         i++;
                     }
                     
                    //Now use the column values as properties
                    Map<String, Column> columns = table.getCols();
                    
                    for (Map.Entry<String, Column> colEntry : columns.entrySet()) {
                        
                        Column column = colEntry.getValue();
                        
                        String colName = column.getName();
                        Object colVal = rs.getObject(column.getName());
                        
                        doc.getProps().put(colName, new Value(TypeConverter.convert(column.getType(), colVal)));
                    }
                    
                    //TODO: Create the references to other documents
                    bucket.set(key, doc);
                   
                }
            }
        }
        
        return bucket;
    }
    
    
    

}
