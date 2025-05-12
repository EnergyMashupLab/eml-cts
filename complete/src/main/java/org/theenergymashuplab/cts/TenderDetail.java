/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/* The UML spec assumes that the implementing language can create and use
 * C-style unions, which Java is incapable of implementing. Thus,
 * the implementation of TenderDetail and its child classes are currently unstable until we 
 * can find a way to rectify the gap between the UML spec and Java's
 * capabilities somehow */

/* @JsonTypeInfo is needed to ensure that Jackson can deserialize TenderDetail
 * TenderDetail is an abstract class, so Jackson needs to include type info in the
 * JSON serialization to ensure that it will be able to correctly deserialize it to the
 * correct concrete class (either TenderIntervalDetail or TenderStreamDetail (which has not been added yet))
 * 
 *  You can learn more about it from the official Jackson Wiki (https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization#12-per-class-annotations)
 *  As well as from here: https://www.baeldung.com/jackson-inheritance */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        // TODO Once TenderStreamInterval has been added, add its type here
        @Type(value = TenderIntervalDetail.class, name = "interval"),
        @Type(value = TenderStreamDetail.class, name = "stream") // Added type for JSON
})
public abstract class TenderDetail {

}
