package ru.forwardmobile.tforwardpayment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

import android.support.v4.app.Fragment;


public class SliderFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;
    int backColor;

    static SliderFragment newInstance(int page) {
        SliderFragment pageFragment = new SliderFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page_slider_fragment, null);

        TextView fDate = (TextView) view.findViewById(R.id.fDate);
        TextView fDescription = (TextView) view.findViewById(R.id.fDescription);

        fDate.setText("Дата " + pageNumber);
        fDate.setTextColor(Color.parseColor("#000000"));
        fDate.setTextSize(11);
        //fDate.setBackgroundColor(backColor);

        fDescription.setText("Уважаемые агенты! Вашему вниманию предоставляется слайдер, в котором размещено информационное сообщение под номером  " + pageNumber);
        fDescription.setTextColor(Color.parseColor("#000000"));
        fDescription.setTextSize(11);
        //fDescription.setBackgroundColor(backColor);

        /*
        RelativeLayout sliderPages = (RelativeLayout) view.findViewById(R.id.sliderPages);
        sliderPages.setBackgroundColor(backColor);

        if (pageNumber == 0){
            view = ClearDots(view);
            LinearLayout firstDot = (LinearLayout) view.findViewById(R.id.firstDot);
            firstDot.setBackgroundColor(Color.parseColor("#3498db"));
        }
        else if(pageNumber == 1)
        {
            view = ClearDots(view);
            LinearLayout secondDot = (LinearLayout) view.findViewById(R.id.secondDot);
            secondDot.setBackgroundColor(Color.parseColor("#3498db"));
        }
        else{
            view = ClearDots(view);
            LinearLayout thirdDot = (LinearLayout) view.findViewById(R.id.thirdDot);
            thirdDot.setBackgroundColor(Color.parseColor("#3498db"));
        }
*/
        return view;
    }
}