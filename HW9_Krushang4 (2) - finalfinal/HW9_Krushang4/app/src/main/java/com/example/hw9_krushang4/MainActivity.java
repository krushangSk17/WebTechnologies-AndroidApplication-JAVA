package com.example.hw9_krushang4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import android.Manifest;
import android.widget.Switch;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 90009) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
            } else {
                // Location permission denied
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume called");

        // Check for location permission and request if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 90009);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//        viewPager = findViewById(R.id.viewPager);
//        tabLayout = findViewById(R.id.tabLayout);
//        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new Tab1_search());
//        fragmentList.add(new Tab2_favorites());
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        FragmentStateAdapter fragmentStateAdapter = new FragmentStateAdapter(fragmentManager,getLifecycle()) {
//            @NonNull
//            @Override
//            public Fragment createFragment(int position) {
//                return fragmentList.get(position);
//            }
//
//            @Override
//            public int getItemCount() {
//                return fragmentList.size();
//            }
//        };
//        viewPager.setAdapter(fragmentStateAdapter);
//
//        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
//            switch (position) {
//                case 0:
//                    tab.setText("Events");
//                    break;
//                case 1:
//                    tab.setText("Favorites");
//                    break;
//            }
//        });
//        tabLayoutMediator.attach();


//        ????????????????????????????????????????????????????????????????
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        Fragment[] fragments = new Fragment[] { new Tab1_search(), new Tab2_favorites() };

        MyAdapter adapter = new MyAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("SEARCH");
                        break;
                    case 1:
                        tab.setText("FAVORITES");
                        break;
                }
            }
        };


        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy);
        tabLayoutMediator.attach();
    }

    public static class MyAdapter extends FragmentStateAdapter {
        private Fragment[] fragments;

        public MyAdapter(@NonNull FragmentActivity fragmentActivity, Fragment[] fragments) {
            super(fragmentActivity);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments[position];
        }

        @Override
        public int getItemCount() {
            return fragments.length;
        }
    }

    public void goBackSearch(View view){
        // Reverse the FragmentTransaction and navigate back to the previous fragment
        getSupportFragmentManager().popBackStack();

        // Make the CardView visible again
        findViewById(R.id.cardView).setVisibility(View.VISIBLE);
        findViewById(R.id.frameLayout2).setVisibility(View.GONE);

    }
}
