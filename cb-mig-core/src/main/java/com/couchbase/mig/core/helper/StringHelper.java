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

package com.couchbase.mig.core.helper;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class StringHelper {
    
    
    /**
     * Checks if a String is defined
     * 
     * @param str
     * @return 
     */
    public static boolean isDefined(String str)
    {
       return str != null && ! "".equals(str);
    }
    
    /**
     * Create a fully qualified name 
     * 
     * @param domain
     * @param name
     * @return 
     */
    public static String createFqn(String domain, String name)
    {
        String result = "";
        
        if (isDefined(domain)) result = result + domain + ".";
        if (isDefined(name)) result = result + name;
        
        return result;
    }
    
    /**
     * Create a fully qualified name
     * 
     * @param domain
     * @param subdomain
     * @param name
     * @return 
     */
    public static String createFqn(String domain, String subdomain, String name)
    {
        String result = "";
        
        if (isDefined(domain)) result = result + domain + ".";
        if (isDefined(subdomain)) result = result + subdomain + ".";
        if (isDefined(name)) result = result + name;
        
        return result;
    }
}
