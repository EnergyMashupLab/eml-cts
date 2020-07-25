Pseudocode for LME Main Loop
=======================

Integration with the [ParityTrading](https://github.com/paritytrading/parity) market is done through hooks inserted in the Parity *Terminal Client*, to which the LME is connected with sockets.

### Logical Description - Parallel Actvities
<pre><code>
  1. Receive  EiCreateTender service request from LMA <br />
  2. Respond to the LMA with EiCreatedTender <br />
  3. Send MarketCreateTender to the Parity Termina Client which converts to a Parity order and enters it in the Order Book<br />
  4. When Parity orders match and clear the LME receives a message, and sends EiCreateTransaction to the LMA<br />
  5. LMA will send CreateTransaction to requestiing TEUA
  6. Receive EiCreatedTransaction from LMA<br />

</code></pre><br />

### Details of POST Requests

See [separate URI Structure and Payloads](uri_structure.md). 
