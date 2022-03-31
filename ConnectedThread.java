package com.esark.excavator;


import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import android.os.SystemClock;
import android.util.Log;

import com.esark.framework.AndroidGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.esark.excavator.GameScreen.quadrant;
import static com.esark.excavator.GameScreen.c;
import static com.esark.excavator.GameScreen.b;


public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private final Handler mHandler;
   // public IntToChars mIntToChars;
    public String[] returnArray = new String[] {"0", "0", "0"};
    IntToChars mIntToChars = new IntToChars();

    public ConnectedThread(BluetoothSocket socket, Handler handler) {
        mmSocket = socket;
        mHandler = handler;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    @Override
    public void run() {
        int bytes; // bytes returned from read()
        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.available();
                byte[] buffer = new byte[60];
                if (bytes != 0) {
                    //SystemClock.sleep(100); //pause and wait for rest of data. Adjust this depending on your sending speed. Originally 100
                    bytes = mmInStream.read(buffer, 0, 50); // record how many bytes we actually read
                    mHandler.obtainMessage(AndroidGame.MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget(); // Send the obtained bytes to the UI activity

                }
            } catch (IOException e) {
                e.printStackTrace();

                break;
            }

            //Try delays between the characters to make it not jump SystemClock.sleep
            /////////////////Bucket Curl//////////////////////
            returnArray = mIntToChars.IntToCharsMethod(c);
            write("c");
            write(returnArray[2]);          //d2 (+/-)
            write(returnArray[1]);          //d1 (Left digit)
            write(returnArray[0]);          //d2 (Right Digit)
            ////////////////Boom//////////////////////////////
            returnArray = mIntToChars.IntToCharsMethod(b);
            write("b");
            write(returnArray[2]);          //d2 (+/-)
            write(returnArray[1]);          //d1 (Left digit)
            write(returnArray[0]);          //d2 (Right Digit)
            SystemClock.sleep(50);


        }
    }

    /* Call this from the main activity to send data to the remote device
    * So I'm now sending a dummy command to the device every 1 second (kind of "keep-alive" command) and now Android is happy because it's not a one-way communication anymore
    *  */
    public void write(String input) {
        byte[] bytes = input.getBytes();           //converts entered String into bytes
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}