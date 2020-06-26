Position Manager
======================================

## Position Manager Payload
Stored in org.theenergymashuplab.cts.controller.payloads package, in this class all the rest call to access position manager is stored. 

Following describes all its api.
1. createPosition : Will add position into the table.
2. getPositionHistoryToId : Fetch position from table with respect to the sellerId.
3. getPositionHistoryFromId : Fetch position from table with respect to the buyerId.
4. getPositionHistory : Fetch position from table with respect to the count.
5. getStatus : Fetch status from table given the id of respective position id.

## Position Manager Model
Stored in org.theenergymashuplab.cts.model package, it represents the database table.

## Position Repository 
Stored in org.theenergymashuplab.cts.repository package, in this class all the native queries are stored.


