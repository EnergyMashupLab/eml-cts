Pseudocode for LMA Main Loop
=======================
Synthetic via RESTful POST and GET

### Logical Description 
<pre><code>
    1. Receive CreateTender service request (from a TEUA) <i>log in transport message list</i><br />
    2. Respond to the TEUA with a CreatedTender <i>log</i><br />
    3. Adapt and send a [rewritten] CreateTender to LME <i>log in transport message list</i><br />
    4. When LME matches and clears it will send LMA back a CreateTransaction <i>log and ledger, 
        also log in transport message list</i><br />
    5. Send CreatedTransaction back to LME <i>log and ledger</i><br />
    6. Send [rewritten] CreateTransaction to requestion UA <i>log and ledger for UA</i><br />
    7. Receive CreatedTransaction from TEUA <i>log and ledger; update in ledger to note acknowledgment</i>
</code></pre><br />

### POST Methods 
<pre><code>
    1. Post action <i>(request from SC, CreateTender for full requirements for a time period)</i>
      * Query position
      * Compare, subtract, and POST remaining requirements as CreateTender to LMA. <i>log</i>
      * RETURN/POST CreatedTender message to SC
    2. POST action <i>(request from LMA, CreatedTender)</i>
      * Log
      * Inform SC <i>(callback or POST)</i>
    3. POST action <i> (request from LMA, CreateTransaction for cleared transaction)</i>
      * Enter in my Ledger
      * Add to my position <i> should already be in my position stored at LMA</i>
      * RETURN/POST CreatedTransaction to LMA 
</code></pre><br />

#### Notes 

    1. Hook in LMA POST method for possible rewrite. Only the hook so can still experiment with rewrite rules 


