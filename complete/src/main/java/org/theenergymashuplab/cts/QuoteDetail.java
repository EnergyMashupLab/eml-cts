package org.theenergymashuplab.cts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
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
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuoteIntervalDetail.class, name = "interval"),  // TODO Once QuoteStreamInterval has been added, add its type here
        @JsonSubTypes.Type(value = QuoteStreamDetail.class, name = "stream") //Added type for JSON
})
public abstract class QuoteDetail {

}
