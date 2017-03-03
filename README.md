
# Java-Sockets
Software educativo per la comunicazione server-client, tramite l'utilizzo di sockets 
```
Funzionamento del software: inviare linee di testo da un programma (Client)... -->
                         --> ad un altro (Server) anche eventualmente remoto.
```
**Nota:** 
Visiona le [Slides sul concetto di comunicazione server-client](https://docs.google.com/presentation/d/19vhath-GbtUO7ofpukJE4Opi16aTfrnurJ2lUizB0iE/edit?usp=sharing)

Visiona le [Slides di presentazione Progetto](https://docs.google.com/a/jcmaxwell.it/presentation/d/1y14rz0iCm6O_igFDaniIv5s_kccipSwN_iw5h48k9bE/edit?usp=sharing)

### Prerequisiti
Java SDK (Software Development Kit) - programma per la compilazione (javac) da codice in Java a ByteCode. L'installazione comprende anche Java JRE (Java Runtime Envirorment) che fornisce la Virtual Machine (VM) su cui far eseguire il ByteCode.
```
E' suggerito anche l'utilizzo di netBeans IDE (Integrated Development Envirorment)
```

### Installazione
Scarica i files del progetto cliccando "Clone/Download"
* in NetBeans crea due nuovi progetti "Java-Sockets-Client" e "Java-Sockets-Server"

* estrai i files e spostali nella sub-directory "src" dei rispettivi progetti (es. Documents\NetBeansProjects\Java-Sockets-Server\src)

* compila i file scaricati attraverso i seguenti comandi(è richiesto l'inserimento del .exe di java nelle variabili d'ambiente)

Per i file Client:

```
cd Documents\NetBeansProjects\Java-Sockets-Client\src
javac listener.java
javac ClientTesto.java
```

Per i file Server:

```
cd Documents\NetBeansProjects\Java-Sockets-Server\src
javac User.java
javac socketWorker.java
javac ServerTestoMultiThreaded.java
```

## Uso
Dal terminale del Server:
```
java ServerTestoMultiThreaded <server port>
```
Da un terminale Client:
```
java clientTesto <host> <server port>
dove:
* host puo' essere espresso sia in forma numerica (es. 127.0.0.1) che in forma alfanumerica (es. www.nomeSito.com)
```
NOTA: posso collegarmi al server con quanti Clients desidero, sia su stesso computer che da terminali su computer diversi.

### Esempio usando stesso computer sia per eseguire Server che multipli Clients

Da finestra di comando esguire il Server:
```
cd Documents\NetBeansProjects\Java-Sockets-Server\src
java ServerTestoMultiThreaded 1234
```
Da nuova finestra di comando esguire il ```primo``` Client
```
cd Documents\NetBeansProjects\Java-Sockets-Client\src
java ClientTesto localhost 1234
```
Da nuova finestra di comando esguire il ```secondo``` Client
```
cd Documents\NetBeansProjects\Java-Sockets-Client\src
java ClientTesto localhost 1234
```

### Per vedere i comandi accessibili scrivere ```!Help``` durante la connessione o visita la [wiki](https://github.com/Casetta/Java-Sockets/wiki/Commands#comandi-programma)

## Licenza
Open Source nel modo piu' completo del termine :) senza alcuna restrizione! Editabile e riadattabile

## Ringraziamenti
Raquaza98(Gasperini) per lo sviluppo del software e Casetta per il supporto morale/conforto nel momento del bisogno di Raquaza98! 
