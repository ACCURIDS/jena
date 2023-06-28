/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.mem2.store.fast;

import org.apache.jena.graph.Triple;
import org.apache.jena.mem2.collection.JenaMapSetCommon;
import org.apache.jena.mem2.collection.JenaSetHashOptimized;

import java.util.function.Predicate;

/**
 * A bunch of triples - a stripped-down set with specialized methods. A
 * bunch is expected to store triples that share some useful property
 * (such as having the same subject or predicate).
 */
public interface FastTripleBunch extends JenaSetHashOptimized<Triple> {
    /**
     * Answer true iff this bunch is implemented as an array.
     * This field is used to optimize some operations by avoiding the need for instanceOf tests.
     *
     * @return true iff this bunch is implemented as an arrays
     */
    boolean isArray();

    /**
     * This method is used to optimize _PO match operations.
     * The {@link JenaMapSetCommon#anyMatch(Predicate)} method is faster if there are only a few matches.
     * This method is faster if there are many matches and the set is ordered in an unfavorable way.
     * _PO matches usually fall into this category.
     *
     * @param predicate the predicate to match
     * @return true if any triple in the bunch matches the predicate
     */
    boolean anyMatchRandomOrder(Predicate<Triple> predicate);
}
