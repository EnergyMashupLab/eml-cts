FROM java:8
WORKDIR .
ADD parity-client.jar parity-client.jar
ADD client0620.conf client0620.conf
EXPOSE 39401
EXPOSE 39402
CMD java -jar parity-client.jar 192.168.1.52 client0620.conf

