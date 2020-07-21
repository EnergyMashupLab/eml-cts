<h1 align="center">
  <br>
  <a href="http://www.theenergymashuplab.org/"><img src="http://static1.squarespace.com/static/53dd102ae4b0474fbf8ce365/t/55da1f5de4b07faafab2518c/1440358237526/EML+Logo+20150816+No+Text.png?format=1500w" alt="Energy Mashup Lab" width="200"></a>
  <br>
  NIST CTS Agents
  <br>
</h1>

<p align="center">
  <a href="#background">Background</a> •
  <a href="#tech-desc">Technical Description</a> •
  <a href="docs/README.md">Documentation</a> •
  <a href="#authors">Authors</a> •
  <a href="#license">License</a>
</p>

We invite participation in an open source project to create Actors[<sup>1</sup>](#fn1) for
edge-based self-optimization of power distribution systems. This project is named
NIST-CTS-Agents because it is an implementation of an agent-based transactive energy 
market using the Common Transactive Services defined during the NIST Transactive 
Energy Challenge. 

<a id="background"></a>Background
----------

Transactive Resource Management (TRM) enables Actors representing systems that
use or supply a resource—any commodity whose value is defined by time and
delivery location— to coordinate behaviors without the need for central control.
TRM-based systems engage Actors in markets to manage supply and demand of a
resource over time. Markets enable emergent behavior—new behavior related to
actors and relationships as actors meet their internal needs.

TRM systems are highly resilient, as Actors can join or leave the system without
additional integration. TRM applications include managing power distribution,
smart power grids, smart water, bandwidth sharing, placement of web and social
media ads, and wastewater management.

When the resource is electric power, TRM is called Transactive Energy (TE).
Transactive Energy is already used to manage the bulk power grid. TE is
considered essential to developing new resilient power grids, to transform
legacy power grids, and to build resource-constrained grids.

Actor-based architectures enable hyper-scalable applications that are easy to
design, build, and maintain. Actor Interactions are limited to defined messages,
so they support diversity of participants and technologies. Market transaction
messages create self-optimizing systems of suppliers, consumers, and
distribution.

This project will develop Transactive Energy User Agents (TEUA) interacting through
Markets. We will define interfaces between an energy system and the Actor (TEUA)
that represents it. TEUAs will interact with a Market Agent/Actor (MA) that
encapsulates market behavior. While the project uses a Bilateral Market model,
the Market Agent will incorporate a Market Modular Interface to support other
market models.

Bilateral Market is a classification; examples of bilateral markets include Double Auction and Order Book.

To see a description of the components that make up this project, look under the 
subfolders of the [source directory](complete/src) as well as the project wiki (in progress) and [documentation folder](complete/docs).

Results
-------

We expect that this project will make it easier for communities, facility
owners, and device makers to apply TE. For example, NIST looks to use these agents and actors in
simulations to model TE for regulators and legislators. A complete
implementation of the Common Transactive Services will be highly visible and
widely used.

Standards Used
--------------

The project uses standards including

-   The TEMIX profile of [OASIS Energy
    Interoperation](https://docs.oasis-open.org/energyinterop/ei/v1.0/os/).
    Energy Interoperation is the profile base of [OpenADR 2] standardized as
    [IEC 62746-10-1] (<https://webstore.iec.ch/publication/26267>)

-   Informative UML models for Energy Interoperation/CTS payloads as shown in
    the EI Standard

-   ISO 17800 Facility Smart Grid Information Model
    (<https://www.iso.org/standard/71547.html> )

-   Adapter methods for integrating with Independent System Operator Wholesale
    Markets and other energy markets are based on [IEC 62746-10-3:2018]
    (<https://webstore.iec.ch/publication/59771>)

<a id="tech-desc"></a>Technical Description
---------------------

The NIST-CTS Project is a standards-based implementation of the Common
Transactive Services using a Transactive Energy User Agenta (TEUA), a Local Market Agent (LMA) that facilitiates interaction between users
and local markets through a Local Market Engine (LME).
The Architecture Drawing shows terminology and relationships within the implementation.![Architecture Drawing](complete/docs/pictures/ArchitectureCts20200720.png) 

The client/building/sc view is shown in the ![CLient View drawing](complete/docs/pictures/ClientViewCts20200720.png)

The project has a number of components and information in a number of subfolders under [../dev](../dev ). We use *ei2j* (Energy Interoperation to Java) as shorthand for the integration function.

-   **Markets** including

    -   The Local Market Engine (LME), the agent that fronts the market implementation including the matching engine that matches buy and sell tenders
    
    -   A bilateral market
    
    -   (future) Additional plug-in markets and documentation

-   **Local Market Agent** (LMA) which interacts with the local market and with Transactive
    Energy User Agents and External Market Adapters using CTS. Functions include    
    -   Market Position Management (see note)
    
    -   The Ledger, the record of cleared (not pending) transactions (see note)
    
    -   Hook points for price Adjustments, enabling market economics experiments
    
    -   Uses CTS to connect to the LME and TEUAs
    
    -   Links to external markets via the External Market Adapter (EMA) which specializes the TEUA
    
-   **External Market Adapter** (EMA), a specialization of the TEUA, interacts with the Local Market Agent and a single external market. 
Functions include
        
    -   Price Adjustment hooks, enabling market economics experiments and presentation of markup on wholesale prices
    
    -  Uses CTS to connect to the LMA and indirectly to other actors

    
-   **Transactive Energy [User] Agent** (TEUA) which interacts with the MA and provides
    integration capabilities for device and facility management
    
    -   Uses ei2j capabilities for CTS connections
    
    -   Integrates with the Supervisory Controller (SC)
    
    -   Maintains the Ledger, the record of cleared (not pending) transactions (see note)
    
    -   Provides information on committed market positions to the SC (see note)

-   **Utilities** include

    -   Logging (traces) and input for live and simulation meter and other data
    
    -   Programs to build configuration files for the market client and system
    
Notes: 
A ledger is a list in time order of committed transactions. A position is cumulative committed transactions. A trace of messages includes transactions proposed but never cleared. Ledgers are saved to a file or possibly sent over a network connection as the design matures.

The Position Manager tracks completed (cleared) transactions (also contained in a ledger) to determine committed market positions.
Market position information is typically needed by the teua (on behalf of the sc).

The TEUA in turn can use market position to determine the difference between committed position and projected needs. The SC may then transact only for what is needed to align current committed position with projected needs, tendering to buy or sell as appropriate.

All transactions and clearing flow through the LMA which updates the Market Position database for use by the TEUA.

Built With
----------

Agile programming and architecture are used.

Source code and builds are managed using Github, Maven, Java 8, JUnit, Apache Log4j2, Spring and Spring Boot.

Building and Running
-------

See the [documentation](docs/README.md) directory and the project wiki (in process) for the tooling and development environment.

<a id="authors"></a>Authors
-------

-   **William Cox** - *Architecture* - [Cox Software Architects
    LLC](http://coxsoftwarearchitects.com/)

-   **Toby Considine –** *Architecture* – [TC9 Inc](http://www.tc9.com/)

See also the list of [contributors] who have contributed to this project.

<a id="license"></a>License
-------

This project is licensed under the Apache 2.0 License, and is Copyright 2019-2020 The Energy Mashup Lab.

For incoming (contributed) licenses see https://github.com/EnergyMashupLab/EML_Licenses

Acknowledgments
---------------

Footnotes
---------------

<a class="anchor" id="fn1">1)</a> The difference between Actors and Agents can be a fine one. The actor model of concurrent computation  treats "actor" as the universal primitive of concurrent computation. An actor is an intelligent resource that has the capacity to initiate, manage, and/or control activities of given types. In response to a message it receives, an actor can: make local decisions, create more actors, send more messages, and determine how to respond to the next message received. An Agent *may* be a particular instantiation of an Actor. Some distinguish the two by whether systems can share direct access to external data--to them, an Agent can and an Actor cannot. Perhaps the Market Matching Engine is an Actor and a component of the the Local Market which is an Agent. Similary, the TEUA may be an Actor which with the SC comprises the User Agent. 
This project does not wish to delve into these semantics, and generally uses the terms interchangeably.

