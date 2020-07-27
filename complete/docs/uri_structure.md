Common Transactive Services URI Structure and Payloads for REST service operations
=====================================
POST operations have a RequestBody (the message that is POSTed to the listed URI) and a ResponseBody (the message body that is returned to the actor doing the POST).
We use this to provide the standard Energy Interoperation messages - for example, for creating a tender (offer to buy or sell) the POST RequestBody contains an EiCreateTender, while the POST ResponseBody contains the correlated EiCreatedTender.

For this project principal authors of the base standards flattened the type hierarchy for only the product (energy) and information elements we use. This approach maintains standards conformance and allows for
* A simpler to use and understand type system 
* Simpler Java class definitions for standard payloads
* A conformance statement at the end of the project

We use JSON rather than XML for message payloads with Jackson serialization and deserialization between Java and JSON.\

#### URI and Operation Tables
Note that the {id} in URI determines the transport end point. ActorId is independent of {id}*.

Certain payloads are created in the market integration code; in the current release they are in parity-client/CtsBridge.java and associated code files.

##### Local Market Agent Services
| URI	| Operation | RequestBody	| ResponseBody	|
| ---		| ---		| ---	| --- |
|/lma/createTender	|	POST|	EiCreateTender|	EiCreatedTender
|/lma/createTransaction	|	POST|	EiCreateTransaction|	EiCreatedTransaction
|/lma/cancelTender	|	POST|	EiCancelTender|	EiCanceledTender
|/lma/party	|GET 		|  |	ActorId

##### Local Market Engine Services
| URI	| Operation | RequestBody	| ResponseBody	|
| ---		| ---		| ---	| --- |
|/lme/createTender	| POST	| EiCreateTender	|	EiCreatedTender
|/lme/cancelTender	| POST	| EiCancelTender	|	EiCanceledTender
|/lme/party		| GET 	|			|	ActorId

##### Transactive Energy User Agent Services
| URI	| Operation | RequestBody	| ResponseBody	|
| ---		| ---		| ---	| --- |
|/teua/{id}/createTransaction	| POST	| EiCreateTransaction	| EiCreatedTransaction
|/teua/{id}/clientCreateTender	| POST	| ClientCreateTender	| ClientCreatedTender
|/teua/{id}/party	| GET	|	| ActorId

##### Client Integration Services
| URI	| Operation | RequestBody	| ResponseBody	|
| ---		| ---		| ---	| --- |
|/client/{id}/clientCreateTransaction	| POST	| ClientCreateTransaction	| CientCreatedTransaction
|/client/{id}/clientCreateTender	| POST	| ClientCreateTender	| ClientCreatedTender

NOTE: */client/{id}/clientCreateTender passes through the client to /teua/{id}/clientCreateTender as a test driver convenience*

Market Integration Services (Payload definition only; sockets used for send and receive)
| URI	| Operation | RequestBody	| ResponseBody	|
| ---		| ---		| ---	| --- |
|Receive match/transaction from Market to LME	| Socket	| MarketCreateTransaction	| MarketCreatedTransaction
|Send tender from LME to Market	| Socket	| MarketCreateTender	| MarketCreatedTender

The [Architecture Diagram](pictures/Architecture20200115.png) shows the relationships of the Actors.

