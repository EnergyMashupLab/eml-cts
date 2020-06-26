# Parity Terminal Client

Parity Terminal Client is a simple console application for entering orders
into the trading system. This version configures a Parity market as needed for this project.

See the uploaded ZIP archive; instructions below.


## Instruments and Setup
The file *hourly-1.conf* in the respective *etc-system* and *etc-client* directories creates one order
book for each **instrument** which represents each one hour period on February 20, 0220-00 through 0220-23 using month-only ISO 8601 dates.

The conf files for the server and client are different but coordinated; the server ignores
requests that use an instrument that is not configured.

The client code is the basis for the RESTcontroller for the NIST-CTS-Agents project, which will use the same entry points for entering orders and getting matches, mapping the internal timestamp IDs to the
CTS transactionIDs.

The product for all instruments is energy, with the time specifying the instrument in Parity.


## Usage

See the file *parity-orders.txt* in the directory *CTSsamples*; output is in *parity-orders.out.txt* in the same directory. Order lines are copied and pasted as shown.

For these specific inputs and outputs start the system server in the background with

````
java -jar parity-system.jar system-etc/hourly-1.conf&
````

Followed by running the client in a terminal window:

````
java -jar parity-client.jar client-etc/hourly-1.conf
````

The general case Parity instructions follow.

Run Parity Terminal Client with Java:

```
java -jar parity-client.jar <configuration-file>
```

The command line arguments are as follows:

- `<configuration-file>`: A configuration file. The configuration file
  specifies how to connect to the trading system.

Once started, the application displays a command prompt:

```
Type 'help' for help.
>
```

You can interact with the application by entering commands into the command
prompt. For example, to enter a buy order, use the `buy` command:

```
> buy 100 AAPL 150.00
```

The command enters a buy order of 100 units of the instrument AAPL at the
limit price of 150.00 into the trading system. Assuming that AAPL can be
traded on the trading system and that there is no matching sell order in
the order book, the order will remain open. To list the open orders, use
the `orders` command:

```
> orders
```

Use the `help` command to see a list of all available commands.

## Configuration

Parity Terminal Client uses a configuration file to specify how to connect to
the trading system.

The following configuration parameters are required:

```
order-entry {

    # The IP address of the trading system.
    address = 127.0.0.1

    # The TCP port for order entry at the trading system.
    port = 4000

    # The order entry username.
    username = parity

    # The order entry password.
    password = parity

}

instruments {

    # The number of digits in the integer part of a price.
    price-integer-digits = 4

    # The number of digits in the integer part of a size.
    size-integer-digits = 7

    AAPL {

        # The number of digits in the fractional part of a price.
        price-fraction-digits = 2

        # The number of digits in the fractional part of a size.
        size-fraction-digits = 0

    }

}
```

See the `etc` directory for an example configuration file.

## License

Released under the Apache License, Version 2.0.

