package com.esark.excavator;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Input;
import com.esark.framework.Screen;
import static com.esark.framework.AndroidGame.landscape;

import java.util.List;

public class GameScreen extends Screen implements Input {
    Context context = null;
    int xStart = 0, xStop = 0;
    public static double[] A2DVal = new double[420];
    double[] psd = new double[420];
    int xTouch1 = 0;
    int yTouch1 = 0;
    int xTouchLeft = 150;
    int yTouchLeft = 275;
    int xTouchRight = 560;
    int yTouchRight = 275;
    int xR = 0;
    int yR = 0;
    int xL = 0;
    int yL = 0;
    int scaledXR = 0;
    int scaledYR = 0;
    int scaledXL = 0;
    int scaledYL = 0;
    double angleR = 0;
    double angleL = 0;
    public static int c = 0;
    public static int b = 0;
    public static int o = 0;
    public static int s = 0;
    int[] tempCArr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] tempBArr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int tempC = 0;
    int tempB = 0;
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    int tempo = 0;
    int temps = 0;
    int leftCount = 0;
    int rightCount = 0;
    int xRTemp = 0;
    int yRTemp = 0;
    int xTouch2 = 0;
    int yTouch2 = 0;
    int xPrevLeft = 150;
    int yPrevLeft = 275;
    int xPrevRight = 560;
    int yPrevRight = 275;
    int xTrackLeft = 275;
    int yTrackLeft = 150;
    int xTrackPrevLeft = 275;
    int yTrackPrevLeft = 150;
    int xTrackRight = 400;
    int yTrackRight = 150;
    int xTrackPrevRight = 400;
    int yTrackPrevRight = 150;
    int numAvg = 0;
    int pointer = 0;
    int prevX = 0;
    int prevY = 0;
    double x = 0;
    double y = 0;
    int pointerId;
    int ptrIndex = 0;
    int count = 0;
    public static String quadrant = "0";

    private static final int INVALID_POINTER_ID = -1;
    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = INVALID_POINTER_ID;


    //Constructor
    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime, Context context) {
        //framework.input
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        updateRunning(touchEvents, deltaTime, context);
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime, Context context) {
        //updateRunning() contains controller code of our MVC scheme
        Graphics g = game.getGraphics();
        if (count == 0) {
            if (landscape == 1) {
                g.drawLandscapePixmap(Assets.excavatorLandscapeBackground, 0, 0);
            } else {
                g.drawPortraitPixmap(Assets.excavatorPortraitBackground, 0, 0);
            }
            g.drawCircle(140, 275, 45);
            g.drawCircle(560, 275, 45);
            g.drawCircle(290, 100, 45);
            g.drawCircle(425, 100, 45);
        }
        int len = touchEvents.size();
        //Check to see if paused
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            //         if (event.type == TouchEvent.TOUCH_UP){
            //           count = 0;
            //     }
            if (event.type == TouchEvent.TOUCH_UP) {
                g.drawCircle(xPrevLeft, yPrevLeft, 45);
                g.drawLine(150, 275, xPrevLeft, yPrevLeft, 0);
                g.drawCircle(xPrevRight, yPrevRight, 45);
                g.drawLine(560, 275, xPrevRight, yPrevRight, 0);
                g.drawCircle(290, yTrackPrevLeft, 45);
                g.drawCircle(425, yTrackPrevRight, 45);
            }


            if (event.type == TouchEvent.TOUCH_DRAGGED || event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_UP) {
                count = 1;
                if (landscape == 1) {
                    g.drawLandscapePixmap(Assets.excavatorLandscapeBackground, 0, 0);
                } else {
                    g.drawPortraitPixmap(Assets.excavatorPortraitBackground, 0, 0);
                }
                // Save the ID of this pointer
                if (event.x < 120 && event.y < 70) {
                    //Back Button Code Here
                    Intent intent2 = new Intent(context.getApplicationContext(), Excavator.class);
                    context.startActivity(intent2);
                    return;
                }
                mActivePointerId = event.pointer;

                if (mActivePointerId == 0) {
                    Log.d("ADebugTag", "mActivePointerId: " + mActivePointerId);
                    xTouch1 = event.x;
                    yTouch1 = event.y;
                    if (xTouch1 < 350) {
                        xTouchLeft = xTouch1;
                        yTouchLeft = yTouch1;
                        xPrevLeft = xTouchLeft;
                        yPrevLeft = yTouchLeft;
                    }
                    if (xTouch1 >= 350) {
                        xTouchRight = xTouch1;
                        yTouchRight = yTouch1;
                        xPrevRight = xTouchRight;
                        yPrevRight = yTouchRight;
                    }
                    if (xTouch1 > 200 & xTouch1 < 350 & yTouch1 < 210) {
                        xTrackLeft = xTouch1;
                        yTrackLeft = yTouch1;
                        xTrackPrevLeft = xTrackLeft;
                        yTrackPrevLeft = yTrackLeft;
                    }
                    if (xTouch1 >= 350 & yTouch1 < 210 & xTouch1 < 450) {
                        xTrackRight = xTouch1;
                        yTrackRight = yTouch1;
                        xTrackPrevRight = xTrackRight;
                        yTrackPrevRight = yTrackRight;
                    }
                    Log.d("ADebugTag", "xTouch1: " + xTouch1);
                    Log.d("ADebugTag", "yTouch1: " + yTouch1);
                } else if (mActivePointerId == 1) {
                    Log.d("ADebugTag", "mActivePointerId: " + mActivePointerId);
                    xTouch2 = event.x;
                    yTouch2 = event.y;
                    if (xTouch2 < 350) {
                        xTouchLeft = xTouch2;
                        yTouchLeft = yTouch2;
                        xPrevLeft = xTouchLeft;
                        yPrevLeft = yTouchLeft;
                    }
                    if (xTouch2 >= 350) {
                        xTouchRight = xTouch2;
                        yTouchRight = yTouch2;
                        xPrevRight = xTouchRight;
                        yPrevRight = yTouchRight;
                    }
                    if (xTouch2 > 250 & xTouch2 < 350 & yTouch2 < 210) {
                        xTrackLeft = xTouch2;
                        yTrackLeft = yTouch2;
                        xTrackPrevLeft = xTrackLeft;
                        yTrackPrevLeft = yTrackLeft;
                    }
                    if (xTouch2 >= 350 & yTouch2 < 210 & xTouch2 < 400) {
                        xTrackRight = xTouch2;
                        yTrackRight = yTouch2;
                        xTrackPrevRight = xTrackRight;
                        yTrackPrevRight = yTrackRight;
                    }
                    Log.d("ADebugTag", "xTouch2: " + xTouch2);
                    Log.d("ADebugTag", "yTouch2: " + yTouch2);
                }
                if (landscape == 1) {
                    numAvg = 10;
                    xR = xTouchRight - 560;
                    yR = 275 - yTouchRight;
                    if (((int)Math.sqrt(Math.abs((xR*xR + yR*yR)))) > 85) {
                        angleR = Math.atan2((double)yR, (double)xR);
                        scaledXR = (int) (85*Math.cos(angleR));
                        scaledYR = (int) (85*Math.sin(angleR));
                        xPrevRight = 560 + scaledXR;
                        yPrevRight = 275 - scaledYR;
                        g.drawCircle((560 + scaledXR), (275 - scaledYR), 45);
                        g.drawLine(560, 275, (560 + scaledXR), (275 - scaledYR), 0);
                        /*
                        rightCount++;
                        if(rightCount == 10){
                            c = scaledXR;
                            b = scaledYR;
                            rightCount = 0;
                        }

                         */
                    //    c = scaledXR;
                      //  b = scaledYR;
                        for(i = 1; i < numAvg; i++){
                            tempCArr[i - 1] = tempCArr[i];
                            tempBArr[i - 1] = tempBArr[i];
                        }
                        tempCArr[numAvg - 1] = scaledXR;
                        tempBArr[numAvg - 1] = scaledYR;
                        if(rightCount < 5) {
                            rightCount++;
                        }
                        if(rightCount == numAvg){
                            for(j = 0; j < numAvg; j++) {
                                tempC += tempCArr[j];
                                tempB += tempBArr[j];
                            }
                            c = (int)(tempC/numAvg);
                            b = (int)(tempB/numAvg);
                            tempC = 0;
                            tempB = 0;
                        }


                    } else if((((int)Math.sqrt(Math.abs((xR*xR + yR*yR))) <= 85))) {
                        g.drawCircle(xTouchRight, yTouchRight, 45);
                        g.drawLine(560, 275, xTouchRight, yTouchRight, 0);
                        /*
                        rightCount++;
                        if(rightCount == 10){
                            c = xR;
                            b = yR;
                            rightCount = 0;
                        }

                         */
                        //c = xR;
                        //b = yR;

                        for(k = 1; k < numAvg; k++){
                            tempCArr[k - 1] = tempCArr[k];
                            tempBArr[k - 1] = tempBArr[k];
                        }
                        tempCArr[numAvg - 1] = xR;
                        tempBArr[numAvg - 1] = yR;
                        if(rightCount < numAvg) {
                            rightCount++;
                        }
                        if(rightCount == numAvg){
                            for(m = 0; m < numAvg; m++) {
                                tempC += tempCArr[m];
                                tempB += tempBArr[m];
                            }
                            c = (int)(tempC/numAvg);
                            b = (int)(tempB/numAvg);
                            tempC = 0;
                            tempB = 0;
                        }




                    }
                    xL = xTouchLeft - 150;
                    yL = 275 - yTouchLeft;
                    if (((int)Math.sqrt(Math.abs((xL*xL + yL*yL)))) > 85) {
                        angleL = Math.atan2((double)yL, (double)xL);
                        scaledXL = (int) (85*Math.cos(angleL));
                        scaledYL = (int) (85*Math.sin(angleL));
                        xPrevLeft = 150 + scaledXL;
                        yPrevLeft = 275 - scaledYL;
                        g.drawCircle((140 + scaledXL), (275 - scaledYL), 45);
                        g.drawLine(140, 275, (140 + scaledXL), (275 - scaledYL), 0);
                        //o = scaledXL;
                        //s = scaledYL;

                        tempo += scaledXL;
                        temps += scaledYL;
                        leftCount++;
                        if(leftCount >= 10){
                            o = (int)(tempo/(leftCount - 1));
                            s = (int)(temps/(leftCount - 1));
                            leftCount = 0;
                            tempo = 0;
                            temps = 0;
                        }


                    } else if((((int)Math.sqrt(Math.abs((xL*xL + yL*yL))) <= 85))) {
                        g.drawCircle(xTouchLeft, yTouchLeft, 45);
                        g.drawLine(140, 275, xTouchLeft, yTouchLeft, 0);
                        o = xL;
                        s = yL;
                        /*
                        tempo += xL;
                        temps += yL;
                        leftCount++;
                        if(leftCount >= 10){
                            o = (int)(tempo/leftCount);
                            s = (int)(temps/leftCount);
                            leftCount = 0;
                            tempo = 0;
                            temps = 0;
                        }

                         */
                    }
                    g.drawCircle(290, yTrackLeft, 45);
                    g.drawCircle(425, yTrackRight, 45);
                }


                //        x = xTouch - 205;
                //      y = -1*(yTouch - 353);
                /*
                //Trigonometry code to calculate the vector angle and magnitude
                angle = Math.toDegrees(Math.atan(Math.abs(y / x)));
                if (x > 0 && y > 0) {
                    quadrant = "1";
                } else if (x < 0 && y > 0) {
                    quadrant = "2";
                } else if (x < 0 && y < 0) {
                    quadrant = "3";
                } else if (x > 0 && y < 0) {
                    quadrant = "4";
                }
                */

            }
        }
    }
        @Override
        public void present ( float deltaTime){
            Graphics g = game.getGraphics();
        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void dispose () {

        }

    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    @Override
    public int getTouchY(int pointer) {
        return 0;
    }

    @Override
    public float getAccelX() {
        return 0;
    }

    @Override
    public float getAccelY() {
        return 0;
    }

    @Override
    public float getAccelZ() {
        return 0;
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return null;
    }
}
