Pseudocode for LMA Main Loop
=======================

### Logical Description - Parallel Activities
<pre><code>
    1. Receive an EiCreateTender message (from a TEUA)><br />
    2. Respond to the TEUA with EiCreatedTender<br />
    3. Forward a [possibly rewritten] EiCreateTender to LME <br />
    4. When LME matches and clears aynchronously it will send EiCreateTransaction back to the LMA. Add transaction quantity and buy/sell to the positiion for both parties<br />
    5. Respond to LME with EiCreatedTransaction<br />
    6. Send [possibly rewritten] EiCreateTransaction to requesting TEUA (mapping PartyId and CounterPartyId to the correct TEUA id) <br />
    7. Receive EiCreatedTransaction from TEUA and forward to LME
</code></pre><br />

### Details of POST Requests

See [separate URI Structure and Payloads](uri_structure.md). 

