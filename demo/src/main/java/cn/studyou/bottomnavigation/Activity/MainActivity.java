package cn.studyou.bottomnavigation.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.studyou.bottomnavigation.Fragment.HomeFragment;
import cn.studyou.bottomnavigation.Fragment.MeFragment;
import cn.studyou.bottomnavigation.Fragment.MessageFragment;
import cn.studyou.bottomnavigation.R;
import cn.studyou.navigationviewlibrary.BottomNavigationItem;
import cn.studyou.navigationviewlibrary.BottomNavigationView;

public class MainActivity extends FragmentActivity {
    BottomNavigationView bottomNavigationView;
    private Fragment homeFragment;
    private Fragment meFragment;
    private Fragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(true);
            bottomNavigationView.isColoredBackground(true);
            //bottomNavigationView.disableShadow();
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(getResources().getColor(R.color.fourthColor));
        }

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                (getString(R.string.home), getResources().getColor(R.color.firstColor), R.drawable.ic_home_24dp);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                (getString(R.string.message), getResources().getColor(R.color.secondColor), R.drawable.ic_markunread_24dp);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                (getString(R.string.me), getResources().getColor(R.color.fourthColor), R.drawable.ic_timer_auto_24dp);

        selectedImages(0);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.setOnBottomNavigationItemClickListener(new BottomNavigationView.OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        selectedImages(0);
                        break;
                    case 1:
                        selectedImages(1);
                        break;
                    case 2:
                        selectedImages(2);
                        break;
                }
            }
        });
    }

    /**
     * 设置选中
     *
     * @param i
     */
    private void selectedImages(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fragment_navigation, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.fragment_navigation, messageFragment);

                } else {
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.fragment_navigation, meFragment);
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
            default:
                break;

        }
        fragmentTransaction.commit();
    }

    /**
     * 初始化隐藏所有Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (messageFragment != null) {
            fragmentTransaction.hide(messageFragment);
        }
        if (meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }

    }
}
