package com.itheima.zhbj52.base.menudetail;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhbj52.MainActivity;
import com.itheima.zhbj52.R;
import com.itheima.zhbj52.base.BaseMenuDetailPager;
import com.itheima.zhbj52.base.TabDetailPager;
import com.itheima.zhbj52.domain.NewsData.NewsTabData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 菜单详情页-新闻
 * 
 * @author Kevin
 * 
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements
		OnPageChangeListener {

	private ViewPager mViewPager;

	private ArrayList<TabDetailPager> mPagerList;

	private ArrayList<NewsTabData> mNewsTabData;// 页签网络数据

	private TabPageIndicator mIndicator;

	public NewsMenuDetailPager(Activity activity,
			ArrayList<NewsTabData> children) {
		super(activity);

		mNewsTabData = children;
	}

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);

		ViewUtils.inject(this, view);

		mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);

		// mViewPager.setOnPageChangeListener(this);//注意:当viewpager和Indicator绑定时,
		// 滑动监听需要设置给Indicator而不是viewpager
		mIndicator.setOnPageChangeListener(this);

		return view;
	}

	@Override
	public void initData() {
		mPagerList = new ArrayList<TabDetailPager>();

		// 初始化页签数据
		for (int i = 0; i < mNewsTabData.size(); i++) {
			TabDetailPager pager = new TabDetailPager(mActivity,
					mNewsTabData.get(i));
			mPagerList.add(pager);
		}

		mViewPager.setAdapter(new MenuDetailAdapter());
		mIndicator.setViewPager(mViewPager);// 将viewpager和mIndicator关联起来,必须在viewpager设置完adapter后才能调用
	}

	// 跳转下一个页面
	@OnClick(R.id.btn_next)
	public void nextPage(View view) {
		int currentItem = mViewPager.getCurrentItem();
		mViewPager.setCurrentItem(++currentItem);
	}

	class MenuDetailAdapter extends PagerAdapter {

		/**
		 * 重写此方法,返回页面标题,用于viewpagerIndicator的页签显示
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return mNewsTabData.get(position).title;
		}

		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPager pager = mPagerList.get(position);
			container.addView(pager.mRootView);
			pager.initData();
			return pager.mRootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		System.out.println("onPageSelected:" + arg0);

		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();

		if (arg0 == 0) {//只有在第一个页面(北京), 侧边栏才允许出来
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

}
