Pseudocode for LME Main Loop
=======================
Synthetic via RESTful POST and GET

Uses only generated libraries from Parity, specifically parity.libraries.book and .market, and consumes classes from util. 

### Logical Description 
<pre><code>
  1. Receive a CreateTender service request from LMA <i>log in transport message list</i><br />
  2. Respond to the LMA with a CreatedTender <i>log</i><br />
  3. Enter the Tender in the Order Book<br />
  4. When tenders match and clear send LMA <i>a CreateTransaction log and ledger also log in 
      transport message list</i><br />
  5. Receive CreatedTransaction from LMA <i>log and ledger</i><br />
  6. LMA will send CreateTransaction to requestiing UA
</code></pre><br />

### POST Methods 
<pre><code>
  1. POST action CreateTender <i>(request from LMA, tender for UA net requirements for a time period)</i>
    * book.market.add(orderID, details) which adds to bid/ask data structures
      as relevant
    * RETURN/POST CreatedTender messaged to LMA.
  2. Spontaneous calls from internal MarketListener on match
    * Accept callback
    * market.execute(orderID, quantity, price) which clears from bid/ask
      data structures as relevant
    * POST CreateTransaction to LMA 
  3. POST action for CancelTender <i>(request from LMA)</i>
    * market.delete(orderID)
    * RETURN/POST CanceledTender to LMA
  4. POST CreatedTransaction
    * Log
</code></pre><br />

#### Notes

  1. The CTS IDs (inherited from idType in EI) should be used in the OrderBook
     (the Parity ID is a long)

  2. CTS does not rewrite tenders in place, 
     so CancelTender == market.delete (Parity Cancel adjusts quantity)