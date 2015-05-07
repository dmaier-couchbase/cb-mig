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

/**
 * A JSON value
 * 
 * Only Numbers, Decimals, Booleans, Strings and Nulls are supported
 * 
 * @author David Maier <david.maier at couchbase.com>
 * @param <T>
 */
public class Value<T> {

    private T inner = null;
    
    public Value(T val) {
    
        if ( val instanceof Long || val instanceof Double || val instanceof Boolean || val instanceof String || val instanceof Null)
        {
           this.inner = val;    
        }
        else
        {
            throw new IllegalArgumentException("This type is not supported:" + val.getClass().getName());
        }
    }   
    
    public T get()
    {
        return inner;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        if (inner instanceof String) sb.append("'");
        
        sb.append(inner.toString());
        
        if (inner instanceof String) sb.append("'");
        
        return sb.toString();
    }
}
