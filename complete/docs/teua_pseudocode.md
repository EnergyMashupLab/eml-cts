Pseudocode for TEUA Main Loop
=======================

### Logical Description - Parallel Activites - User Agent for Client/Building/SC

<pre><code>
    1. Receive a service request *ClientCreateTender* to buy/sell energy (from the Client) and optionally net against position for that time period<br />
    2. Build *EiCreateTender* payload <br />
    3. Send *EiCreateTender* to LMA<br />
    4. Wait to receive *EiCreatedTender* <br />
    5. Receive *EiCreateTransaction* from LME, construct *ClientCreateTransaction* and send to Client<br />
    6. Respond to the *EiCreateTransaction* with *EiCreatedTransaction*
</code></pre><br />

### Details of POST Requests

See [separate URI Structure and Payloads](uri_structure.md). 
