package com.example.cherry.fullplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cherry.fullplayer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangfei
 * @date 2017/12/20
 */
public class MyAdapter extends Adapter implements View.OnClickListener {
    private int add = 0;
    private Map<String, Integer> map = new HashMap<>();
    private Context mContext;
    private List<City> cities;
    private int choosePosition = -1;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int postion);
    }

    public static final String[] mCitiesStrings = {
            "#", "北京市", "上海市", "广州市", "深圳市", "杭州市",
            "A", "阿坝", "阿拉善", "阿里", "安康", "安庆", "鞍山", "安顺", "安阳", "澳门",
            "B", "北京", "白银", "保定", "宝鸡", "保山", "包头", "巴中", "北海", "蚌埠", "本溪", "毕节", "滨州", "百色", "亳州",
            "C", "重庆", "成都", "长沙", "长春", "沧州", "常德", "昌都", "长治", "常州", "巢湖", "潮州", "承德", "郴州", "赤峰", "池州", "崇左", "楚雄", "滁州", "朝阳",
            "D", "大连", "东莞", "大理", "丹东", "大庆", "大同", "大兴安岭", "德宏", "德阳", "德州", "定西", "迪庆", "东营",
            "E", "鄂尔多斯", "恩施", "鄂州",
            "F", "福州", "防城港", "佛山", "抚顺", "抚州", "阜新", "阜阳",
            "G", "广州", "桂林", "贵阳", "甘南", "赣州", "甘孜", "广安", "广元", "贵港", "果洛",
            "H", "杭州", "哈尔滨", "合肥", "海口", "呼和浩特", "海北", "海东", "海南", "海西", "邯郸", "汉中", "鹤壁", "河池", "鹤岗", "黑河", "衡水", "衡阳", "河源", "贺州", "红河", "淮安", "淮北", "怀化", "淮南", "黄冈", "黄南", "黄山", "黄石", "惠州", "葫芦岛", "呼伦贝尔", "湖州", "菏泽",
            "J", "济南", "佳木斯", "吉安", "江门", "焦作", "嘉兴", "嘉峪关", "揭阳", "吉林", "金昌", "晋城", "景德镇", "荆门", "荆州", "金华", "济宁", "晋中", "锦州", "九江", "酒泉",
            "K", "昆明", "开封",
            "L", "兰州", "拉萨", "来宾", "莱芜", "廊坊", "乐山", "凉山", "连云港", "聊城", "辽阳", "辽源", "丽江", "临沧", "临汾", "临夏", "临沂", "林芝", "丽水", "六安", "六盘水", "柳州", "陇南", "龙岩", "娄底", "漯河", "洛阳", "泸州", "吕梁",
            "M", "马鞍山", "茂名", "眉山", "梅州", "绵阳", "牡丹江",
            "N", "南京", "南昌", "南宁", "宁波", "南充", "南平", "南通", "南阳", "那曲", "内江", "宁德", "怒江",
            "P", "盘锦", "攀枝花", "平顶山", "平凉", "萍乡", "莆田", "濮阳",
            "Q", "青岛", "黔东南", "黔南", "黔西南", "庆阳", "清远", "秦皇岛", "钦州", "齐齐哈尔", "泉州", "曲靖", "衢州",
            "R", "日喀则", "日照",
            "S", "上海", "深圳", "苏州", "沈阳", "石家庄", "三门峡", "三明", "三亚", "商洛", "商丘", "上饶", "山南", "汕头", "汕尾", "韶关", "绍兴", "邵阳", "十堰", "朔州", "四平", "绥化", "遂宁", "随州", "宿迁", "宿州",
            "T", "天津", "太原", "泰安", "泰州", "台州", "唐山", "天水", "铁岭", "铜川", "通化", "通辽", "铜陵", "铜仁", "台湾",
            "W", "武汉", "乌鲁木齐", "无锡", "威海", "潍坊", "文山", "温州", "乌海", "芜湖", "乌兰察布", "武威", "梧州",
            "X", "厦门", "西安", "西宁", "襄樊", "湘潭", "湘西", "咸宁", "咸阳", "孝感", "邢台", "新乡", "信阳", "新余", "忻州", "西双版纳", "宣城", "许昌", "徐州", "香港", "锡林郭勒", "兴安",
            "Y", "银川", "雅安", "延安", "延边", "盐城", "阳江", "阳泉", "扬州", "烟台", "宜宾", "宜昌", "宜春", "营口", "益阳", "永州", "岳阳", "榆林", "运城", "云浮", "玉树", "玉溪", "玉林",
            "Z", "杂多县", "赞皇县",
            "枣强县", "枣阳市", "枣庄", "泽库县", "增城市", "曾都区", "泽普县", "泽州县", "札达县", "扎赉特旗",
            "扎兰屯市", "扎鲁特旗", "扎囊县", "张北县", "张店区", "章贡区", "张家港", "张家界", "张家口", "漳平市",
            "漳浦县", "章丘市", "樟树市", "张湾区", "彰武县", "漳县", "张掖", "漳州", "长子县", "湛河区", "湛江",
            "站前区", "沾益县", "诏安县", "召陵区", "昭平县", "肇庆", "昭通", "赵县", "昭阳区", "招远市", "肇源县",
            "肇州县", "柞水县", "柘城县", "浙江", "镇安县", "振安区", "镇巴县", "正安县", "正定县", "正定新区",
            "正蓝旗", "正宁县", "蒸湘区", "正镶白旗", "正阳县", "郑州", "镇海区", "镇江", "浈江区", "镇康县",
            "镇赉县", "镇平县", "振兴区", "镇雄县", "镇原县", "志丹县", "治多县", "芝罘区", "枝江市",
            "芷江侗族自治县", "织金县", "中方县", "中江县", "钟楼区", "中牟县", "中宁县", "中山", "中山区",
            "钟山区", "钟山县", "中卫", "钟祥市", "中阳县", "中原区", "周村区", "周口", "周宁县", "舟曲县", "舟山",
            "周至县", "庄河市", "诸城市", "珠海", "珠晖区", "诸暨市", "驻马店", "准格尔旗", "涿鹿县", "卓尼",
            "涿州市", "卓资县", "珠山区", "竹山县", "竹溪县", "株洲", "株洲县", "淄博", "子长县", "淄川区", "自贡",
            "秭归县", "紫金县", "自流井区", "资溪县", "资兴市", "资阳"
    };

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        cities = new ArrayList<>();
        for(int i=0;i<mCitiesStrings.length;i++){
            String s = mCitiesStrings[i];
            if("#".equals(s)){
                map.put(s,cities.size());
                cities.add(new City("热门城市",0));
            }else if("A".equals(s)){
                cities.add(new City("全部城市",0));
                map.put(s,cities.size());
            }else {
                if(s.length()>1){
                    cities.add(new City(s,1));
                }else {
                    map.put(s,cities.size());
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            view.setOnClickListener(this);
            return new CityViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        City city = cities.get(position);
        if(holder instanceof TitleViewHolder){
            ((TitleViewHolder) holder).setTitle(city.name);
        }else {
            holder.itemView.setTag(position);
            ((CityViewHolder) holder).setTitle(city.name);
            if(position==cities.size()-1||cities.get(position+1).type==0){
                ((CityViewHolder) holder).setBottomVisible(false);
            }else {
                ((CityViewHolder) holder).setBottomVisible(true);
            }
            if(choosePosition == position){
                ((CityViewHolder) holder).setClicked(true);
            }else {
                ((CityViewHolder) holder).setClicked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public int getItemViewType(int position) {
       return cities.get(position).type;
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick((Integer) v.getTag());
        }
        if(choosePosition!=-1){
            int p = choosePosition;
            choosePosition = (int) v.getTag();
            notifyItemChanged(p);
            notifyItemChanged(choosePosition);
        }else {
            choosePosition = (int) v.getTag();
            notifyItemChanged(choosePosition);
        }
    }

    public int getLetterPosition(String letter){
        Log.d("huangfei","-----------"+letter);
        return map.get(letter);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TitleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setTitle(String title) {
            textView.setText(title);
        }
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private View bottom;

        public CityViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_city);
            imageView = (ImageView) itemView.findViewById(R.id.img_choosed);
            bottom = itemView.findViewById(R.id.view_bottom);
        }

        public void setTitle(String city) {
            textView.setText(city);
        }

        public void setClicked(boolean bool) {
            if (bool) {
                textView.setTextColor(mContext.getResources().getColor(R.color.button_color));
                imageView.setVisibility(View.VISIBLE);
            } else {
                textView.setTextColor(mContext.getResources().getColor(R.color.city_black));
                imageView.setVisibility(View.INVISIBLE);
            }
        }

        public void setBottomVisible(boolean visible) {
            if (visible) {
                bottom.setVisibility(View.VISIBLE);
            } else {
                bottom.setVisibility(View.GONE);
            }
        }
    }

    private class City {
        private String name;
        private int type;

        public City(String name, int type) {
            this.name = name;
            this.type = type;
        }
    }

}