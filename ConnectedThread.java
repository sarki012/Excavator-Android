package com.esark.excavator;


import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.SystemClock;

import com.esark.framework.AndroidGame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.esark.excavator.GameScreen.angle;
import static com.esark.excavator.GameScreen.quadrant;
import static com.esark.excavator.GameScreen.vector;


public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private final Handler mHandler;
    String ad0, ad1, ad2;
    String vd0, vd1, vd2;


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
            switch((int)angle%10) {
                case 0 :
                    ad0 = "0";
                    break;
                case 1 :
                    ad0 = "1";
                    break;
                case 2 :
                    ad0 = "2";
                    break;
                case 3 :
                    ad0 = "3";
                    break;
                case 4 :
                    ad0 = "4";
                    break;
                case 5 :
                    ad0 = "5";
                    break;
                case 6 :
                    ad0 = "6";
                    break;
                case 7 :
                    ad0 = "7";
                    break;
                case 8 :
                    ad0 = "8";
                    break;
                case 9 :
                    ad0 = "9";
                    break;
                default :
                    ad0 = "-";
            }
            angle /= 10;
            switch((int)angle%10) {
                case 0 :
                    ad1 = "0";
                    break;
                case 1 :
                    ad1 = "1";
                    break;
                case 2 :
                    ad1 = "2";
                    break;
                case 3 :
                    ad1 = "3";
                    break;
                case 4 :
                    ad1 = "4";
                    break;
                case 5 :
                    ad1 = "5";
                    break;
                case 6 :
                    ad1 = "6";
                    break;
                case 7 :
                    ad1 = "7";
                    break;
                case 8 :
                    ad1 = "8";
                    break;
                case 9 :
                    ad1 = "9";
                    break;
                default :
                    ad1 = "-";
            }

            angle /= 10;

            switch((int)angle%10) {
                case 0 :
                    ad2 = "0";
                    break;
                case 1 :
                    ad2 = "1";
                    break;
                case 2 :
                    ad2 = "2";
                    break;
                case 3 :
                    ad2 = "3";
                    break;
                case 4 :
                    ad2 = "4";
                    break;
                case 5 :
                    ad2 = "5";
                    break;
                case 6 :
                    ad2 = "6";
                    break;
                case 7 :
                    ad2 = "7";
                    break;
                case 8 :
                    ad2 = "8";
                    break;
                case 9 :
                    ad2 = "9";
                    break;
                default :
                    ad2 = "-";
            }
            /*
            switch((int)vector%10) {
                case 0 :
                    vd0 = "0";
                    break;
                case 1 :
                    vd0 = "1";
                    break;
                case 2 :
                    vd0 = "2";
                    break;
                case 3 :
                    vd0 = "3";
                    break;
                case 4 :
                    vd0 = "4";
                    break;
                case 5 :
                    vd0 = "5";
                    break;
                case 6 :
                    vd0 = "6";
                    break;
                case 7 :
                    vd0 = "7";
                    break;
                case 8 :
                    vd0 = "8";
                    break;
                case 9 :
                    vd0 = "9";
                    break;
                default :
                    vd0 = "-";
            }
            vector /= 10;
            switch((int)vector%10) {
                case 0 :
                    vd1 = "0";
                    break;
                case 1 :
                    vd1 = "1";
                    break;
                case 2 :
                    vd1 = "2";
                    break;
                case 3 :
                    vd1 = "3";
                    break;
                case 4 :
                    vd1 = "4";
                    break;
                case 5 :
                    vd1 = "5";
                    break;
                case 6 :
                    vd1 = "6";
                    break;
                case 7 :
                    vd1 = "7";
                    break;
                case 8 :
                    vd1 = "8";
                    break;
                case 9 :
                    vd1 = "9";
                    break;
                default :
                    vd1 = "-";
            }

            vector /= 10;

            switch((int)vector%10) {
                case 0 :
                    vd2 = "0";
                    break;
                case 1 :
                    vd2 = "1";
                    break;
                case 2 :
                    vd2 = "2";
                    break;
                case 3 :
                    vd2 = "3";
                    break;
                case 4 :
                    vd2 = "4";
                    break;
                case 5 :
                    vd2 = "5";
                    break;
                case 6 :
                    vd2 = "6";
                    break;
                case 7 :
                    vd2 = "7";
                    break;
                case 8 :
                    vd2 = "8";
                    break;
                case 9 :
                    vd2 = "9";
                    break;
                default :
                    vd2 = "-";
            }*/
            if(!(ad2 == "0" && ad1 == "0" && ad0 == "0")) {
                write("A");
                write(ad2);
                write(ad1);
                write(ad0);
                write("Q");
                write(quadrant);
            }
            /*
            if(!(vd2 == "0" && vd1 == "0" && vd0 == "0")) {
                write("V");
                write(vd2);
                write(vd1);
                write(vd0);
                write("*");
            }
            */

      //      if(startChar == 1);
        //    startChar = 0;


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