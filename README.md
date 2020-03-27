# RestTestbed
Testbed for ensuring that CTS RESTful interactions are complete and correct before integrating into the [NIST Common Transactive Services Agents Project](https://github.com/EnergyMashupLab/NIST-CTS-Agents).

Types and type names (Java class declarations and names) do not precisely match those in NIST-CTS-Agents as they were developed in parallel, although both are derived from the standards described below.
The rationalizing of types is an active task in the NIST-CTS-Agents project.

The code can be exercised using tools such as cURL and [Postman](https://www.postman.com/). Documentation of the RESTful APIs is pending.

Based On
------------

The code skeleton for a simple Greeting RESTful service was taken from [The Spring Guides Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) and its [code repository](https://github.com/spring-guides/gs-rest-service). The code is provided under the Apache 2.0 License.

Standards Used
--------------

The Common Transactive Services Project uses standards including

-   The [Common Transactive Services](https://github.com/EnergyMashupLab/TransactiveEnergyChallenge) - See CommonTransactiveServices
    in that repository. These services were defined in the NIST Transactive Energy Challenge as a universal means of interacting
    with markets, and specifically markets for energy. The Common Transactive Services are shown in that report to communicate
    both ways with every known energy market implementation.

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

License
---------
Provided under the Apache 2.0 License; please see LICENSE in top level for details.
