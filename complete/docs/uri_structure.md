URI Structure for REST service operations
=====================================
POST operations have a RequestBody (the message that is POSTed to the listed URI) and a ResponseBody (the message body that is returned to the actor doing the POST).
This provides the standard Energy Interoperation messages - a POST RequestBody contains an EiCreateTender, while the POST ResponseBody contains the correlated EiCreatedTender.

For this project the principal authors of the base standards flattened the type hierarchy for only the product (energy) and information elements we use. This approach maintains standards conformance and allows for
* A simpler to use and understand type system 
* Simpler Java class definitions for standard payloads
* A conformance statement at the end of the project

NIST-CTS-Agents uses JSON rather than XML for message payloads. The project uses Jefferson serialization and deserialization between Java and JSON.

### LMA 
````/lma
	/createTender           POST	@ResponseBody is an EiCreatedTender object
	/createTransaction      POST	@ResponseBody is an EiCreatedTransaction object
	/cancelTender           POST	@ResponseBody is an EiCanceledTender object
	/party	                GET 	@ResponseBody is an ActorId containing actor’s partyId
````
### LME 
````/lme
	/createTender		POST	@ResponseBody is an EiCreatedTender object
	/cancelTender		POST	@ResponseBody is an EiCanceledTender object
	/party			GET 	@ResponseBody is an ActorId containing actor’s partyId
````

### TEUA and EMA
````
/teua — {id - sequential integer assigned on creation}
 	/{id}/CreateTransaction	        POST	@ResponseBody is an EiCreatedTransaction object
	/{id}/CreateTender		POST	@ResponseBody is an EiCreatedTender object. For user entity integration
	/{id}/party			GET 	@ResponseBody is an ActorId containing actor’s partyId
````
#### Terminology 
LMA - Local Market Agent

LME - Local Market Engine 

TEUA - Transactive Energy User Agent 

Refer to the [Architecture Diagram](https://github.com/EnergyMashupLab/NIST-CTS-Agents/blob/master/Architecture.png) for more detail on the RESTcontrollers.
