import os
import select
import serial
import time

SERIAL_PORT_1 = '/dev/pts/8'
SERIAL_PORT_2 = '/dev/pts/7'
BAUD_RATE = 9600

print("Program started")
# Open the virtual serial ports
ser1 = serial.Serial(SERIAL_PORT_1, BAUD_RATE, timeout=10)
ser2 = serial.Serial(SERIAL_PORT_2, BAUD_RATE, timeout=10)

# Wait for the serial ports to initialize
time.sleep(1)

# Read from the first virtual serial port and write to the second virtual serial port
while True:
#    rlist, _, _ = select.select([ser1], [], [], 0)
#    if rlist:
    data = ser1.read(256)
    print(data.decode(), end='')
    print(' ')
    ser2.write(data)

# Close the virtual serial ports
ser1.close()
ser2.close()
print("Program finished")
