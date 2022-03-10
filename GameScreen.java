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
    int yTouchLeft = 300;
    int xTouchRight = 550;
    int yTouchRight = 300;
    int xTouch2 = 0;
    int yTouch2 = 0;
    int xPrevLeft = 150;
    int yPrevLeft = 300;
    int xPrevRight = 550;
    int yPrevRight = 300;
    int xTrackLeft = 300;
    int yTrackLeft = 150;
    int xTrackPrevLeft = 300;
    int yTrackPrevLeft = 150;
    int xTrackRight = 400;
    int yTrackRight = 150;
    int xTrackPrevRight = 400;
    int yTrackPrevRight = 150;
    int pointer = 0;
    int prevX = 0;
    int prevY = 0;
    double x = 0;
    double y = 0;
    int pointerId;
    int ptrIndex = 0;
    int count = 0;
    public static double angle = 0;
    public static double vector = 0;
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
        if(count == 0) {
            if(landscape == 1) {
                g.drawLandscapePixmap(Assets.excavatorLandscapeBackground, 0, 0);
            }
            else{
                g.drawPortraitPixmap(Assets.excavatorPortraitBackground, 0, 0);
            }
            g.drawCircle(150, 300, 45);
            g.drawCircle(550, 300, 45);
            g.drawCircle(290, 150, 45);
            g.drawCircle(425, 150, 45);
        }
        int len = touchEvents.size();
        //Check to see if paused
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
   //         if (event.type == TouchEvent.TOUCH_UP){
     //           count = 0;
       //     }
            if(event.type == TouchEvent.TOUCH_UP) {
                g.drawCircle(xPrevLeft, yPrevLeft, 45);
                g.drawLine(150, 300, xPrevLeft, yPrevLeft, 0);
                g.drawCircle(xPrevRight, yPrevRight, 45);
                g.drawLine(550, 300, xPrevRight, yPrevRight, 0);
                g.drawCircle(290, yTrackPrevLeft, 45);
                g.drawCircle(425, yTrackPrevRight, 45);
            }


            if(event.type == TouchEvent.TOUCH_DRAGGED || event.type == TouchEvent.TOUCH_DOWN) {
                count = 1;
                if(landscape == 1) {
                    g.drawLandscapePixmap(Assets.excavatorLandscapeBackground, 0, 0);
                }
                else{
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

                if(mActivePointerId == 0){
                    Log.d("ADebugTag", "mActivePointerId: " + mActivePointerId);
                    xTouch1 = event.x;
                    yTouch1 = event.y;
                    if(xTouch1 < 350 & yTouch1 > 210){
                        xTouchLeft = xTouch1;
                        yTouchLeft = yTouch1;
                        xPrevLeft = xTouchLeft;
                        yPrevLeft = yTouchLeft;
                    }
                    if(xTouch1 >= 350 & yTouch1 > 210){
                        xTouchRight = xTouch1;
                        yTouchRight = yTouch1;
                        xPrevRight = xTouchRight;
                        yPrevRight = yTouchRight;
                    }
                    if(xTouch1 > 200 & xTouch1 < 350 & yTouch1 < 210){
                        xTrackLeft = xTouch1;
                        yTrackLeft = yTouch1;
                        xTrackPrevLeft = xTrackLeft;
                        yTrackPrevLeft = yTrackLeft;
                    }
                    if(xTouch1 >= 350 & yTouch1 < 210){
                        xTrackRight = xTouch1;
                        yTrackRight = yTouch1;
                        xTrackPrevRight = xTrackRight;
                        yTrackPrevRight = yTrackRight;
                    }
                    Log.d("ADebugTag", "xTouch1: " + xTouch1);
                    Log.d("ADebugTag", "yTouch1: " + yTouch1);
                }
                else if(mActivePointerId == 1){
                    Log.d("ADebugTag", "mActivePointerId: " + mActivePointerId);
                    xTouch2 = event.x;
                    yTouch2 = event.y;
                    if(xTouch2 < 350 & yTouch2 > 210){
                        xTouchLeft = xTouch2;
                        yTouchLeft = yTouch2;
                        xPrevLeft = xTouchLeft;
                        yPrevLeft = yTouchLeft;
                    }
                    if(xTouch2 >= 350 & yTouch2 > 210){
                        xTouchRight = xTouch2;
                        yTouchRight = yTouch2;
                        xPrevRight = xTouchRight;
                        yPrevRight = yTouchRight;
                    }
                    if(xTouch2 > 200 & xTouch2 < 350 & yTouch2 < 210){
                        xTrackLeft = xTouch2;
                        yTrackLeft = yTouch2;
                        xTrackPrevLeft = xTrackLeft;
                        yTrackPrevLeft = yTrackLeft;
                    }
                    if(xTouch2 >= 350 & yTouch2 < 210){
                        xTrackRight = xTouch2;
                        yTrackRight = yTouch2;
                        xTrackPrevRight = xTrackRight;
                        yTrackPrevRight = yTrackRight;
                    }
                    Log.d("ADebugTag", "xTouch2: " + xTouch2);
                    Log.d("ADebugTag", "yTouch2: " + yTouch2);
                }
                if(landscape == 1) {
                    g.drawCircle(xTouchLeft, yTouchLeft, 45);
                    g.drawLine(150, 300, xTouchLeft, yTouchLeft, 0);
                    g.drawCircle(xTouchRight, yTouchRight, 45);
                    g.drawLine(550, 300, xTouchRight, yTouchRight, 0);
                    g.drawCircle(290, yTrackLeft, 45);
                    g.drawCircle(425, yTrackRight, 45);
                }

        //        x = xTouch - 205;
          //      y = -1*(yTouch - 353);
                //Trigonometry code to calculate the vector angle and magnitude
                angle = Math.toDegrees(Math.atan(Math.abs(y/x)));
                if(x > 0 && y > 0){
                    quadrant = "1";
                }
                else if(x < 0 && y > 0){
                    quadrant = "2";
                }
                else if(x < 0 && y < 0){
                    quadrant = "3";
                }
                else if(x > 0 && y < 0){
                    quadrant = "4";
                }

                vector = Math.sqrt(x*x + y*y);
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
