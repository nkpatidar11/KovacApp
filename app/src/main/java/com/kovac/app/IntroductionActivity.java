package com.kovac.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kovac.app.utils.IntroManager;

public class IntroductionActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private IntroManager introManager;
    private int[] layouts;

    private TextView[] textViewDots;
    private LinearLayout linearLayoutDots;

    //private Button next, skip;
    private Button getStarted;

    private ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introManager = new IntroManager(this);

        if (!introManager.checkFirst()){
            introManager.setFirst(false);

            Intent intent = new Intent(IntroductionActivity.this, SplashScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        setContentView(R.layout.activity_introduction);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        linearLayoutDots = (LinearLayout) findViewById(R.id.layoutDots);

       /* next = (Button) findViewById(R.id.btn_next);
        skip = (Button) findViewById(R.id.btn_skip);*/

        getStarted = (Button) findViewById(R.id.btn_getStarted);

        layouts = new int[] {R.layout.activity_screen_1, R.layout.activity_screen_2, R.layout.activity_screen_3, R.layout.activity_screen_4, R.layout.activity_screen_5};

        addBottomDots(0);
        changeStatusBarColor();

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

       /* skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                introManager.setFirst(false);
                startActivity(new Intent(IntroductionActivity.this, SplashScreen.class));
                finish();
            }
        });*/

        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    introManager.setFirst(false);
                    startActivity(new Intent(IntroductionActivity.this, SplashScreen.class));
                    finish();
                }
            }
        });*/

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    introManager.setFirst(false);
                    Intent intent = new Intent(IntroductionActivity.this, SplashScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void addBottomDots(int currentPage){

        textViewDots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        linearLayoutDots.removeAllViews();

        for (int i = 0; i < textViewDots.length; i++){
            textViewDots[i] = new TextView(this);
            textViewDots[i].setText(Html.fromHtml("&#8226;"));
            textViewDots[i].setTextSize(35);
            textViewDots[i].setTextColor(colorInactive[3]);
            linearLayoutDots.addView(textViewDots[i]);
        }

        if(textViewDots.length > 0)
            textViewDots[currentPage].setTextColor(colorActive[3]);

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == (layouts.length - 1)){
                getStarted.setVisibility(View.VISIBLE);
                /*next.setText("Proceed");
                skip.setVisibility(View.GONE);*/
            }else {
                /*next.setText("Next");
                skip.setVisibility(View.VISIBLE);*/
                getStarted.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!introManager.checkFirst()){
            introManager.setFirst(false);

            Intent intent = new Intent(IntroductionActivity.this, SplashScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
