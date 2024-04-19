package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/* The UML spec assumes that the implementing language can create and use
 * C-style unions, which Java is incapable of implementing. Thus,
 * the implementation of TenderDetail and its child classes are currently unstable until we 
 * can find a way to rectify the gap between the UML spec and Java's
 * capabilities somehow */

/* @JsonTypeInfo is needed to ensure that Jackson can deserialize TenderDetail
 * TenderDetail is an abstract class, so Jackson needs to include type info in the
 * JSON serialization to ensure that it will be able to correctly deserialize it to the
 * correct concrete class (either TenderIntervalDetail or not-as-of-writing-this included TenderStreamDetail
 * 
 *  Note, I copied and paste this from the official Jackson Wiki (https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization#12-per-class-annotations)
 *  so future adjustments could be made */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class TenderDetail {

}
