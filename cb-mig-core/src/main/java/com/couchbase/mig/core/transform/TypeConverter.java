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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class TypeConverter {
    
    private static Logger LOG = Logger.getLogger(TypeConverter.class.getName());
    
    /**
     * Converts a Java Object with a specific SQL type into a JSON supported data type
     * 
     * @param type
     * @param in
     * @return 
     */
    public static Object convert(int type, Object in)
    {

        switch (type) {
            
            //String types
            case Types.CHAR: case Types.VARCHAR: case Types.NCHAR: case Types.NVARCHAR: case Types.LONGVARCHAR: case Types.LONGNVARCHAR: case Types.CLOB: case Types.NCLOB: 
            
                return in.toString();
                
            //Simple numbers    
            case Types.INTEGER: case Types.BIGINT : case Types.TINYINT:
                
                return Long.parseLong(in.toString());
                 
            //Decimals
            case Types.DECIMAL: case Types.FLOAT: case Types.DOUBLE: case Types.NUMERIC:
                
                return Double.parseDouble(in.toString());
                
            //Boolean type    
            case Types.BOOLEAN:
                
                return Boolean.parseBoolean(in.toString());
                
            case Types.BLOB: case Types.BINARY: case Types.VARBINARY: case Types.JAVA_OBJECT:
                
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ObjectOutputStream out;
            
                try {
                    
                    out = new ObjectOutputStream(bytes);
                    out.writeObject(in);
                
                } catch (IOException ex) {
                    
                    LOG.severe("Could not write the binary output stream!");
                }
            
                return Base64.getEncoder().encodeToString(bytes.toByteArray());
                
            case Types.DATE: case Types.TIME: case Types.TIMESTAMP: case Types.TIME_WITH_TIMEZONE:  case Types.TIMESTAMP_WITH_TIMEZONE:
                   
                if (in instanceof Date)
                {   
                    Date d = (Date) in;
                    return d.getTime();
                }   
                    
                if (in instanceof Time)
                {
                    Time t = (Time) in;
                    return t.getTime();
                }
                    
                if (in instanceof Timestamp)
                {
                    Timestamp ts = (Timestamp) in;
                    return ts.getTime();
                }
            
            //All other default to String
            default: 
                
                return in.toString();

        }
                       
    }
}
