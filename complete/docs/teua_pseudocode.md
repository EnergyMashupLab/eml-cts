Pseudocode for TEUA Main Loop
=======================
Synthetic via RESTful POST and GET

### Logical Description 
<pre><code>
    1. Receive a service request to buy/sell energy (from the SC) and net against position for that time period 
        <i>log in transport message list for debugging</i><br />
    2. Build net CreateTender payload <i>log with all fields of the Tender (party, counterparty, Tender, etc)</i><br />
    3. Send net CreateTender to LMA <i>(log in transport message list)</i><br />
    4. Wait to receive a CreatedTender <i>(log receipt)</i> and later a CreateTransaction <i>(log and ledger updates)</i><br />
    5. Update my position <i>(in LMA)</i><br />
    6. Respond to the CreateTransaction with a CreatedTransaction <i>(log)</i>
</code></pre><br />

### POST Methods 
<pre><code>
    1. POST action CreateTender <i>(request from SC, for full requirements for a time period)</i>
      * Query position
      * Compare, subtract, and POST remaining requirements as CreateTender to LMA <i>(log)</i>
      * Return CreatedTender message or indication to SC <i>(CTS or callback/return on simple method invocation or POST)</i>
    2. POST action CreatedTender <i>(from LMA)</i>
      * Log
      * Respond to SC <i>(callback or POST)</i>
    3. POST action CreateTransaction <i>(request from LMA, CreateTransaction for cleared transaction)</i>
      * Enter in my logical Ledger <i>(correct to place in LMA ledger for query, logically specific to this TEUA)</i>
      * Add to my postion <i>should already be in my position as stored at LMA</i>
      * POST CreatedTransaction to LMA
</code></pre><br />

#### Notes 

    1. The SC might invoke a method OR use a RESTful web service; the latter is possible if in the 
       same code space/JVM as the TEUA.

    2. Position manger is attached to LMA, not to each UA.
    
    3. The position manager can be a seperate RESTful service. It needs to
       e queried by other than the LMA. 
