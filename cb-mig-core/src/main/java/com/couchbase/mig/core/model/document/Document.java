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

package com.couchbase.mig.core.model.document;

import java.util.HashMap;
import java.util.Map;

/**
 * This class edescribes a JSON document
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Document {
    
    /**
     * The inner Maps
     */
    private final Map<String, Value> props = new HashMap<>();
    private final Map<String, Document> docs = new HashMap<>();
    private final Map<String, Array> arrays = new HashMap<>();

    //Getters
    public Map<String, Value> getProps() {
        return props;
    }

    public Map<String, Document> getDocs() {
        return docs;
    }

    public Map<String, Array> getArrays() {
        return arrays;
    }

    @Override
    public String toString() {
      
        
        StringBuilder sb = new StringBuilder();
             
        sb.append("{");
        
        if (!props.isEmpty()) {

            int i = 0;
            
            for (Map.Entry<String, Value> propsEntry : props.entrySet()) {

                if (i!=0) sb.append(",");
                
                String key = propsEntry.getKey();
                Value value = propsEntry.getValue();

                sb.append("'" + key + "':");
                sb.append(value.toString());
                
                i++;

            }
        }
        
        if (!docs.isEmpty()) {
            
            sb.append(",");
            
            for (Map.Entry<String, Document> entry : docs.entrySet()) {

                String key = entry.getKey();
                Document doc = entry.getValue();

                sb.append("'" + key + "' :");
                sb.append(doc.toString());

            }
        }
        
        if (!arrays.isEmpty()) {
            
            sb.append(",");
            
            for (Map.Entry<String, Array> entry : arrays.entrySet()) {

                String key = entry.getKey();
                Array arr = entry.getValue();

                sb.append("'" + key + "' :");
                sb.append(arr.toString());

            }

        }
        
        sb.append("}");
        
        return sb.toString();
    } 
}
