Common Transactive Services URI Structure and Payloads for REST service operations
=====================================
POST operations have a RequestBody (the message that is POSTed to the listed URI) and a ResponseBody (the message body that is returned to the actor doing the POST).
We use this to provide the standard Energy Interoperation messages - for example, for creating a tender (offer to buy or sell) the POST RequestBody contains an EiCreateTender, while the POST ResponseBody contains the correlated EiCreatedTender.

For this project principal authors of the base standards flattened the type hierarchy for only the product (energy) and information elements we use. This approach maintains standards conformance and allows for
* A simpler to use and understand type system 
* Simpler Java class definitions for standard payloads
* A conformance statement at the end of the project

We use JSON rather than XML for message payloads with Jackson serialization and deserialization between Java and JSON.\


#### Terminology 
LMA - Local Market Agent

LME - Local Market Engine 

TEUA - Transactive Energy User Agent 



| *URI Base*	| RequestBody	| ResponseBody	|
/lma
|	/createTender	POST	EiCreateTender		EiCreatedTender
|	/createTransaction	POST	EiCreateTransaction	EiCreatedTransaction
|	/cancelTender	POST	EiCancelTender		EiCanceledTender
|	/party			GET 								ActorId the actor partyId

/lme
|	/createTender	POST	EiCreateTender		EiCreatedTender
|	/cancelTender	POST	EiCancelTender		EiCanceledTender
|	/party			GET 								ActorId the actor partyId

/*teua	{id} in URI determines transport end point. ActorId is independent of {id}*
| 	/{id}/createTransaction	POST	EiCreateTransaction		EiCreatedTransaction
|	/{id}/clientCreateTender	POST	ClientCreateTender	ClientCreatedTender
|	/{id}/party	GET		ActorId the actor partyId

/client	? Client Integration services ? {id} in URI determines transport end point.
|	{id}//clientCreateTransaction	POST	ClientCreateTransaction	CientCreatedTransaction
|	/{id}/clientCreateTender		POST ClientCreateTender	ClientCreatedTender
|		NOTE: passes through to /teua/{id}/clientCreateTender as a test driver convenience





Refer to the [Architecture Diagram](pictures/Architectur20200115.png) shows the relationships of the Actors.
