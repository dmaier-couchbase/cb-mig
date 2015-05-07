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

package com.couchbase.mig.core.model.document.bucket;

import com.couchbase.mig.core.model.document.Document;
import java.util.Map;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class LogBucket implements IBucket {

    @Override
    public Document get(String key) {
        throw new UnsupportedOperationException("Log entries are write only.");
    }

    @Override
    public Map<String, Document> getAll() {
        throw new UnsupportedOperationException("Log entries are write only.");
    }

    @Override
    public String getName() {
        
        return "log";
    }

    @Override
    public void set(String key, Document value) {
        
        System.out.println(key + " : " + value);
    }
    
}
