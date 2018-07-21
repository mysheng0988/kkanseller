package com.mysheng.office.kkanseller.util;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by mysheng on 2017/12/1.
 */

public class ReadLoginData {
    private  static SharedPreferences spf;
    private  static SharedPreferences.Editor editor;

    public static String getLoginDate(Context context,String string){
        spf=context.getSharedPreferences("loginXml", Context.MODE_PRIVATE);
        editor=spf.edit();
        return spf.getString(string,"");
    }
    public static void  setLoginData(Context context,String name,String value){
        spf=context.getSharedPreferences("loginXml", Context.MODE_PRIVATE);
        editor=spf.edit();
        editor.putString(name,value);
        editor.commit();
    }
    public static void exitLoginDate(Context context){
        spf=context.getSharedPreferences("loginXml", Context.MODE_PRIVATE);
        spf.edit().clear().commit();

    }
    public static int getContNum(Context context,String string){
        spf=context.getSharedPreferences("loginXml2", Context.MODE_PRIVATE);
        editor=spf.edit();
        return spf.getInt(string,0);
    }
    public static void  setContNum(Context context,String name,int value){
        spf=context.getSharedPreferences("loginXml2", Context.MODE_PRIVATE);
        editor=spf.edit();
        editor.putInt(name,value);
        editor.commit();
    }

}
