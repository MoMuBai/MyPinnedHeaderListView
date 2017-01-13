package com.lzw.mypinnedheaderlistview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/13
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  根据 https://github.com/836948082/Pinnedheaderlistview 修改成自己需要的，感谢作者！！！
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class MainActivity extends AppCompatActivity {

        private PinnedHeaderListView pinnedHeaderListView;

        private IndexBar indexBar;

        private TestSectionedAdapter mAdapter;
        private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                        if (null != tvSearch) {
                                tvSearch.setVisibility(View.GONE);
                        }
                }
        };
        private TextView tvSearch;
        private Map<Integer, List<String>> map;
        private List<String> list, hotCityList;
        private String[] selectCityStr = new String[]{"#", "热门", "A", "B", "C", "D", "F", "H", "J", "K", "L", "G"};
        private String[] hotCityStr = new String[]{"热门城市", "A", "B", "C", "D", "F", "H", "J", "K", "L", "G"};
        private String[][] cityStr = new String[][]{{"北京", "上海", "杭州", "广州", "深圳", "厦门"},
                  {"安徽1", "安徽2", "安徽3", "安徽4"}, {"北京1", "北京2", "北京3", "北京4", "北京5",},
                  {"重庆1", "重庆2", "重庆3", "重庆4", "重庆5", "重庆6"}, {"大连1", "大连2", "大连3", "大连4"},
                  {"福州1", "福州2", "福州3", "福州4", "福州5"}, {"哈尔滨1", "哈尔滨2", "哈尔滨3", "哈尔滨4", "哈尔滨5", "哈尔滨6"},
                  {"济南1", "济南2", "济南3", "济南4", "济南5"}, {"郑州1", "郑州2", "郑州3", "郑州4", "郑州5", "郑州6"},
                  {"卡尔1", "卡尔2", "卡尔3", "卡尔4", "卡尔5"}, {"兰亭1", "兰亭2", "兰亭3", "兰亭4", "兰亭5"}};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                initView();
                initData();
        }

        private void initView() {
                tvSearch = (TextView) findViewById(R.id.tv_search);
                pinnedHeaderListView = (PinnedHeaderListView) findViewById(R.id.pinnedListView);
                indexBar = (IndexBar) findViewById(R.id.indexBar);
                View view = LayoutInflater.from(this).inflate(R.layout.head_view, null);
                pinnedHeaderListView.addHeaderView(view);
                map = new HashMap<>();
                list = new ArrayList<>();
                hotCityList = new ArrayList<>();
        }

        private void initData() {
                for (int i = 0; i < cityStr.length; i++) {
                        list.clear();
                        for (int j = 0; j < cityStr[i].length; j++) {
                                list.add(cityStr[i][j]);
                        }
                        map.put(i, list);
                }
                for (int i = 0; i < hotCityStr.length; i++) {
                        hotCityList.add(hotCityStr[i]);
                }
                indexBar.setWords(selectCityStr);
                mAdapter = new TestSectionedAdapter(this, hotCityList, map);
                pinnedHeaderListView.setAdapter(mAdapter);
                indexBar.setOnIndexBarChangeListener(new IndexBar.OnIndexBarChangeListener() {
                        @Override
                        public void onIndexChange(int index, String word) {
                                for (int i = 0; i < hotCityStr.length; i++) {
                                        if (word.endsWith("#")) {
                                                pinnedHeaderListView.setSelection(0);
                                        }
                                        if (word.endsWith("热门")) {
                                                pinnedHeaderListView.setSelection(1);
                                        } else if (word.endsWith(hotCityStr[i])) {
                                                int rightSection = 0;
                                                for (int j = 0; j < index; j++) {
                                                        rightSection += mAdapter.getCountForSection(j) + 1;
                                                }
                                                pinnedHeaderListView.setSelection(rightSection - map.get(i).size());
                                        }
                                        tvSearch.setVisibility(View.VISIBLE);
                                        tvSearch.setText(word);
                                        handler.removeCallbacksAndMessages(null);//发送之前先移除之前所有发送的消息,防止突然莫名其妙消失了
                                        handler.sendEmptyMessageDelayed(1, 1500);//1.5秒后隐藏界面
                                }
                                return;//记得return防止 for 循环继续执行,导致滚动到的不是第一个而是最后一个
                        }
                });
        }
}
