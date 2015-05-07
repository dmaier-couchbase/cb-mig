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

import com.couchbase.mig.core.model.document.Bucket;
import com.couchbase.mig.core.model.jdbc.Database;
import java.sql.SQLException;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public interface ITransfomer {

    Bucket transform(Database db) throws SQLException;
    
}