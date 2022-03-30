package com.esark.excavator;

public class IntToChars {
    int integer;
    int returnAddress = 0;
    String[] d1d0 = new String[] {"0", "0"};
    public IntToChars(){
    }
    public String IntToCharsMethod(int integer){
        //int to char code here...
            switch((int)integer%10) {
                case 0 :
                    d0 = "0";
                    break;
                case 1 :
                    d0 = "1";
                    break;
                case 2 :
                    d0 = "2";
                    break;
                case 3 :
                    d0 = "3";
                    break;
                case 4 :
                    d0 = "4";
                    break;
                case 5 :
                    d0 = "5";
                    break;
                case 6 :
                    d0 = "6";
                    break;
                case 7 :
                    d0 = "7";
                    break;
                case 8 :
                    d0 = "8";
                    break;
                case 9 :
                    d0 = "9";
                    break;
                default :
                    d0 = "-";
            }
            integer /= 10;
            switch((int)integer%10) {
                case 0 :
                    d1 = "0";
                    break;
                case 1 :
                    d1 = "1";
                    break;
                case 2 :
                    d1 = "2";
                    break;
                case 3 :
                    d1 = "3";
                    break;
                case 4 :
                    d1 = "4";
                    break;
                case 5 :
                    d1 = "5";
                    break;
                case 6 :
                    d1 = "6";
                    break;
                case 7 :
                    d1 = "7";
                    break;
                case 8 :
                    d1 = "8";
                    break;
                case 9 :
                    d1 = "9";
                    break;
                default :
                    d1 = "-";
            }


        return d1d0;
    }
}

