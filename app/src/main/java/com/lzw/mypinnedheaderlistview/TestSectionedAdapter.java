package com.lzw.mypinnedheaderlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class TestSectionedAdapter extends SectionedBaseAdapter {

        private Context mContext;
        private String[] hotCityStr;
        private String[][] cityStr;

        public TestSectionedAdapter(Context context, String[] hotCityStr, String[][] cityStr) {
                this.mContext = context;
                this.hotCityStr = hotCityStr;
                this.cityStr = cityStr;
        }

        @Override
        public Object getItem(int section, int position) {
                return cityStr[section][position];
        }

        @Override
        public long getItemId(int section, int position) {
                return position;
        }

        @Override
        public int getSectionCount() {
                return hotCityStr.length;
        }

        @Override
        public int getCountForSection(int section) {
                return cityStr[section].length;
        }

        @Override
        public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
                LinearLayout layout;
                if (convertView == null) {
                        LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        layout = (LinearLayout) inflator.inflate(R.layout.list_item, null);
                } else {
                        layout = (LinearLayout) convertView;
                }
                ((TextView) layout.findViewById(R.id.textItem)).setText(cityStr[section][position]);
                layout.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                                Toast.makeText(mContext, cityStr[section][position], Toast.LENGTH_SHORT).show();
                        }
                });
                return layout;
        }

        @Override
        public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
                LinearLayout layout;
                if (convertView == null) {
                        LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
                } else {
                        layout = (LinearLayout) convertView;
                }
                layout.setClickable(false);
                ((TextView) layout.findViewById(R.id.textItem)).setText(hotCityStr[section]);
                return layout;
        }

}
