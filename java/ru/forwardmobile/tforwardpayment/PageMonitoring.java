package ru.forwardmobile.tforwardpayment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by PiskunovI on 28.08.2014.
 */
public class PageMonitoring extends ActionBarActivity {

    final static String LOG_TAG = "TFORWARD.PageMonitoring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitoring);

        Log.d(LOG_TAG, "Initialize PageMonitoring");

        //applyFonts( findViewById(R.id.some_id) ,null);

    }

    protected  void applyFonts(final View v, Typeface fontToSet)
    {
        if(fontToSet == null)
            fontToSet = Typeface.createFromAsset(getAssets(), "meVe0se2.ttf");

        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    applyFonts(child, fontToSet);
                }
            } else if (v instanceof TextView) {
                ((TextView)v).setTypeface(fontToSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }

}