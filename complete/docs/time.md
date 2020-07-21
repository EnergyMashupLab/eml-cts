Time in CTS
======================================

## Simulated *versus* Clock Time

The first deployment of this implementation of The Energy Mashup Lab's Common Transactive Services is in a simulation. Simulated time
generally moves faster than clock time. This means that coding time-specific waiting (e.g. a java *sleep()* invocation) must pay careful
attention.

This note discusses some of the time-related issues that users of eml-cts need to take into account.

## Uses of Time in CTS - expirationTime for Tenders

The first use of time in cts is the **expirationTime** for tenders. The value is a *java.time* Instant, which references clock time - for esample, a tender for Noon to 1pm tomorrow may be set to expire today at 5pm local time.

Since one may simulate a time period disconnected from present clock time, the expirationTime must be interpreted as an instant in
simulation time - an explicit sleep or wakeup in seven hours (as of this writing) is incorrect if one is simulating past or future time,
and since simulated time runs at a different rate, it's not correct even if one is simulating today.

## Uses of Time in CTS - Thread Sleep

When setting up (say) a socket connection there may be an explicit sleep to ensure that the socket server is open. This is an example
of thread sleep to allow other parts of the code to complete work. This is typically something like *sleep(10 seconds)* or *sleep until
woken up*.

These sleep method invocations are clock time based, or based on other activity in progress. No adjustments are needed to accommodate simulated time.

## Uses of Time in CTS - Time Stamps

At this writing there are no explicit time stamps in CTS. The Parity market engine uses clock time (time stamps) to group and describe
Orders placed in the market, but this can be viewed as ordering. Likewise, the number attached to a Parity message is used to ensure
in-order delivery and replay.

It has been proposed for the LMA to add a timestamp when it forwards an EiCreateTender, as the TEUA may be part of the Client address
space and security perimeter, as a timestamp should be reliable. The reliabiity of a timestamp, however, depends on trust for the stamper
as well as when (in real or simulated time) the action occurs.

## Uses of Time in CTS - Instants and Time Intervals

We use the classes *BridgeInterval* and *BridgeInstant* to express Intervals as a text time stamp conforming with ISO 8601.
(This was related to JSON serialization issues earlier in the development). The Instant is in so-called "Z Time" or UTC.

Developers may want to enhance these times to use *java.time.LocalDateTime*, as this is frequently preferred for forensic and accounting
purposes - UTC is sometimes thought to be precisely equivalent, but the mapping is not monotonic increasing due to daylight savings or summer time, creating significant issues with
manual-in-the-program mapping.

## Uses of Time in CTS - Instruments and Time Intervals

To integrate CTS with the Parity market, we must use instrument names.

The approach used is to build the requisite 8 ASCII character names from substrings of an ISO 8601 timestamp, as produced in Java by
(e.g.) date.toString() or date formatters.

These timestamps need to be interpreted in clock time or simulation time, as appropriate for the deployment of CTS.

