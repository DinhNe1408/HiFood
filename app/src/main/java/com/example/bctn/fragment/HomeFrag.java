package com.example.bctn.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bctn.R;
import com.example.bctn.adapter.MainAdap;
import com.example.bctn.adapter.Slide_TC;
import com.example.bctn.adapter.TheLoaiAdap;
import com.example.bctn.domain.slide_tc;
import com.example.bctn.domain.theloai;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFrag extends Fragment{

    private View mView;
    private TabLayout tab_home_1;
    private ViewPager2 viewPage2_Slide, viewPage2_home_2;
    private CircleIndicator3 circleIndicator3;
    private Slide_TC slide_TC;
    private List<slide_tc> mListSlide;
    private List<theloai> mListTL;
    private RecyclerView recV_TheLoai_home;
    private MainAdap mainAdap;
    // Autorun Slide
    private Handler mHandler =  new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (viewPage2_Slide.getCurrentItem() == mListSlide.size() - 1 ) {
                viewPage2_Slide.setCurrentItem(0);
            } else {
                viewPage2_Slide.setCurrentItem(viewPage2_Slide.getCurrentItem() + 1);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_home,container,false);

        AnhXa();
        mListSlide = getListSlide();

        slide_TC = new Slide_TC(mView.getContext(), mListSlide);
        viewPage2_Slide.setClipToOutline(true);
        viewPage2_Slide.setAdapter(slide_TC);
        circleIndicator3.setViewPager(viewPage2_Slide);

        viewPage2_Slide.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,3000);
            }
        });

        mListTL = getListTL();
        TheLoaiAdap theLoaiAdap = new TheLoaiAdap(mView.getContext(),mListTL );
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mView.getContext(), 2,RecyclerView.HORIZONTAL,false);
        recV_TheLoai_home.setLayoutManager(gridLayoutManager);
        recV_TheLoai_home.setAdapter(theLoaiAdap);

        mainAdap = new MainAdap(getActivity());
        viewPage2_home_2.setAdapter(mainAdap);


        new TabLayoutMediator(tab_home_1, viewPage2_home_2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Gần bạn");
                    break;
                case 1:
                    tab.setText("Bán chạy");
                    break;
                case 2:
                    tab.setText("Phổ biến");
                    break;
                case 3:
                    tab.setText("Khuyến mãi");
                    break;
                default:
                    tab.setText("Gần bạn");
            }
        }).attach();

        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }

    private void AnhXa() {
        viewPage2_Slide = mView.findViewById(R.id.viewPage2_Slide);
        circleIndicator3 = mView.findViewById(R.id.Indicator3_Slide);
        recV_TheLoai_home = mView.findViewById(R.id.recV_TheLoai_home);

        viewPage2_home_2 = mView.findViewById(R.id.viewPage2_home_2);
        tab_home_1 = mView.findViewById(R.id.tab_home_1);
    }

    private List<slide_tc> getListSlide() {
        List<slide_tc> mList = new ArrayList<>();
        mList.add(new slide_tc(R.drawable.t1));
        mList.add(new slide_tc(R.drawable.t1));
        mList.add(new slide_tc(R.drawable.t1));
        mList.add(new slide_tc(R.drawable.t1));
        return mList;
    }

    private List<theloai> getListTL() {
        List<theloai> mList = new ArrayList<>();
        mList.add(new theloai(1,R.drawable.sale,"Nước"));
        mList.add(new theloai(2,R.drawable.sale,"Nước"));
        mList.add(new theloai(3,R.drawable.sale,"Nước"));
        mList.add(new theloai(4,R.drawable.sale,"Nước"));

        mList.add(new theloai(5,R.drawable.sale,"Nước"));
        mList.add(new theloai(6,R.drawable.sale,"Nước"));
        mList.add(new theloai(7,R.drawable.sale,"Nước"));
        mList.add(new theloai(8,R.drawable.sale,"Nước"));

        mList.add(new theloai(5,R.drawable.sale,"Nước"));
        mList.add(new theloai(6,R.drawable.sale,"Nước"));
        mList.add(new theloai(7,R.drawable.sale,"Nước"));
        mList.add(new theloai(8,R.drawable.sale,"Nước"));

        mList.add(new theloai(5,R.drawable.sale,"Nước"));
        mList.add(new theloai(6,R.drawable.sale,"Nước"));
        mList.add(new theloai(7,R.drawable.sale,"Nước"));
        mList.add(new theloai(8,R.drawable.sale,"Nước"));
        return mList;
    }
}
