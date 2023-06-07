Fiz um programa em Python para ler de uma porta serial e escrever na outra. 
A funcionalidade de pegar de uma porta e escrever na outra funciona bem, mas eu não consigo printar a informação lida, tão pouco consigo fazer alguma análise de tal informação no programa Python.

Para escrever na serial criada num terminal do Linux, é possível fazer em uma das duas formas abaixo:
1)$ echo "Hello Floripa" | socat - /dev/pts/7
2)$ echo "Hello Manchester" > /dev/pts/7

A seguir estão as instruções sobre o que tem que ser instalado e o que precisa executar para que funcione o terminal serial virtual.

--------------

First, you need to install both programs using the package manager of your Linux distribution. For example, in Ubuntu, you can install socat and minicom by running the following command in the terminal:
$ sudo apt-get install socat minicom

Once both programs are installed, follow these steps to emulate serial communication:

Open two terminal windows.
In the first terminal window, run the following command to start socat:
$ socat -d -d pty,raw,echo=0 pty,raw,echo=0

This command creates two virtual serial ports (e.g., /dev/pts/2 and /dev/pts/3) and prints their names to the console.

In the second terminal window, run the following command to start minicom:
$ sudo minicom -s

This command opens the minicom configuration menu.

In the minicom configuration menu, select "Serial port setup" and configure the serial port settings as follows:
Serial Device: /dev/pts/2 (or the first virtual serial port created by socat)
Bps/Par/Bits: 9600 8N1
Hardware Flow Control: No
Exit the minicom configuration menu and select "Save setup as dfl" to save the configuration as the default.

Press Ctrl-A and then Z to enter the minicom command menu.

Select "Serial port setup" again and configure the second virtual serial port created by socat as follows:

Serial Device: /dev/pts/3 (or the second virtual serial port created by socat)
Bps/Par/Bits: 9600 8N1
Hardware Flow Control: No
Exit the minicom command menu and select "Exit" to start minicom.

Now you can emulate serial communication between the two virtual serial ports by typing in one terminal window and observing the output in the other terminal window.


