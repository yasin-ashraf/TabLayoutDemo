package com.yasin.tablayoutdemo;

import android.os.Bundle;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.yasin.tablayoutdemo.actionCards.ActionCard1;
import com.yasin.tablayoutdemo.actionCards.ActionCard2;
import com.yasin.tablayoutdemo.committee.committee1;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout bottomNavigation;
    private TabLayout homeFeedTabs;
    private TabLayout actionCardSlider;
    private ViewPager2 actionCardsViewPager;
    private ViewPager2 committeeViewPager;
    private ViewSwitcher viewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        homeFeedTabs = findViewById(R.id.home_feed_tabs);
        actionCardSlider = findViewById(R.id.action_slider);
        actionCardsViewPager = findViewById(R.id.viewpager_action_cards);
        committeeViewPager = findViewById(R.id.viewPager_committee);
        viewSwitcher = findViewById(R.id.viewSwitcher);

//        Toolbar mToolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(mToolbar);

        bottomNavigation.addTab(bottomNavigation.newTab().setIcon(R.drawable.bottom_nav_home_selector));
        bottomNavigation.addTab(bottomNavigation.newTab().setIcon(R.drawable.bottom_nav_chat_selector));
        bottomNavigation.addTab(bottomNavigation.newTab().setIcon(R.drawable.bottom_nav_search_selector));
        bottomNavigation.addTab(bottomNavigation.newTab().setIcon(R.drawable.bottom_nav_va_selector));

        homeFeedTabs.addTab(homeFeedTabs.newTab().setText("Home"));
        homeFeedTabs.addTab(homeFeedTabs.newTab().setText("Feed"));

//        bottomNavigation.addOnTabSelectedListener(this);
        homeFeedTabs.addOnTabSelectedListener(this);

        //action cards

        ActionCardAdapter actionCardAdapter = new ActionCardAdapter(getSupportFragmentManager());

        actionCardAdapter.addFragment(new ActionCard1());
        actionCardAdapter.addFragment(new ActionCard2());

        actionCardsViewPager.setPageTransformer(new PageMarginTransformer(60));
        actionCardsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        actionCardsViewPager.setAdapter(actionCardAdapter);

        //committee cards

        CommiteeCardAdapter commiteeCardAdapter = new CommiteeCardAdapter(getSupportFragmentManager());

        commiteeCardAdapter.addFragment(new committee1());
        commiteeCardAdapter.addFragment(new committee1());

        committeeViewPager.setPageTransformer(new PageMarginTransformer(60));
        committeeViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        committeeViewPager.setAdapter(commiteeCardAdapter);

        //setup slider
//        actionCardSlider.setupWithViewPager(actionCardsViewPager, true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getText().toString()){
            case "Home":
                viewSwitcher.setDisplayedChild(0);
                break;

            case "Feed":
                viewSwitcher.setDisplayedChild(1);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
