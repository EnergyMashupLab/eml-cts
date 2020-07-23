CTS Intervals expressed as Parity Instruments 
======================================

## Time and CTS

See [Time in CTS](time.md) for background material.

## Uses of Time in CTS - Instruments and Time Intervals

To integrate CTS with the Parity market, we must use instrument names.

The approach used is to build the requisite 8 ASCII character names from substrings of an ISO 8601 timestamp, as produced in Java by
(e.g.) date.toString() or date formatters.

These timestamps and intervals
need to be interpreted in clock time or simulation time, as appropriate for the deployment of CTS.

The application **cts-create-instruments** can be found in [parity-cts applications](https://github.com/EnergyMashupLab/parity-cts/tree/dev/applications/cts-create-instruments) with documentation.

