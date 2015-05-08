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

import java.util.ArrayList;
import java.util.List;

/**
 * Describes a JSON array
 * 
 * @author David Maier <david.maier at couchbase.com>
 * @param <T>
 */
public class Array<T> {
        
    //The inner arrays
    private final List<Value<T>> inner = new ArrayList<>();
    
    public void add(Value<T> val)
    {
        inner.add(val);
    }

    public List<Value<T>> get() {
        return inner;
    }
    
    public Value<T> get(int idx)
    {
        return inner.get(idx);
    }

    @Override
    public String toString() {
        
        return inner.toString();
    }
    
    public boolean contains(Value<T> v)
    {
        for (Value<T> value : inner) {
            
            if (value.toString().equals(v.toString())) return true;
        }
        
        return false;
    }
  
}
