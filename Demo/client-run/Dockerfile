FROM java:8
WORKDIR /home/dockerubuntu/Desktop/client-run/
ADD parity-client.jar parity-client.jar
ADD client0620.conf client0620.conf
EXPOSE 39401
EXPOSE 39402
CMD java -jar parity-client.jar 10.0.2.15 client0620.conf

