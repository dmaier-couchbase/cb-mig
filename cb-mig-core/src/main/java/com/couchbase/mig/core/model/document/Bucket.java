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
 * This class describes a Bucket
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Bucket {
    
    /**
     * The name of the bucket
     */
    private final String name;

     /**
     * The inner map
     */
    private final Map<String, Document> inner = new HashMap<>();
    
    
    /**
     * The constructor
     * @param name
     */
    public Bucket(String name) {
        
        this.name = name;
    }
    
    
    public void set(String key, Document value)
    {
        this.inner.put(key, value);
    }
    
    public Document get(String key)
    {
        return this.inner.get(key);
    }

    public String getName() {
        return name;
    }
   
    
    public Map<String, Document> getAll()
    {
        return this.inner;
    }

    @Override
    public String toString() {
        
        return this.name;
    }
}
