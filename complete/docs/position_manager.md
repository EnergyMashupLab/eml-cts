Position Manager
======================================

## Position Manager 
See packages org.theenergymashuplab.cts.controller.payloads, .model, .dao, and .repository for details.

The Position Manager uses JPA to store its information. The REST API for the PositionManager RestController is

1. POST: /position/{positionParty}/add adds a position for a particular Party and time interval, creating a database row if not present. 
2. GET: /position/{positionParty}/getPosition retrieves the position for a specific Party and time interval

## Position Manager Model
PositionManagerModel represents the database table.

## Position Repository 
PositionRepository contains all the native SQL queries.

