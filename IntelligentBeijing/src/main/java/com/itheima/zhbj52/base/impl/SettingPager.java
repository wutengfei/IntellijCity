package com.itheima.zhbj52.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhbj52.base.BasePager;

/**
 * 设置页面
 * 
 * @author Kevin
 * 
 */
public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("初始化设置数据....");
		
		tvTitle.setText("设置");
		btnMenu.setVisibility(View.GONE);// 隐藏菜单按钮
		setSlidingMenuEnable(false);// 关闭侧边栏

		TextView text = new TextView(mActivity);
		text.setText("设置");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);

		// 向FrameLayout中动态添加布局
		flContent.addView(text);
	}

}
