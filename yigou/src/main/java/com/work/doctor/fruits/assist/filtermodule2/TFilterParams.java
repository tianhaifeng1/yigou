package com.work.doctor.fruits.assist.filtermodule2;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.work.doctor.fruits.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：小童
 * 创建时间：2019/8/22 15:44
 */
public class TFilterParams implements BaseQuickAdapter.OnItemClickListener {

    public static final String FILTER_ITEM_ONE = "item1";
    public static final String FILTER_ITEM_TWO = "item2";
    public static final String FILTER_ITEM_THREE = "item3";
    public static final String FILTER_ITEM_FOUR = "item4";
    public static final String FILTER_ITEM_FIVE = "item5";


    //背景色
    //第一行的背景色
//    public int filterTabBg;
//    //第二行的整体背景色
//    public int filterAllBg;
//    //第二行的列表项背景色
//    public int filterItemBg;
//    public int filterItemSelectBg;


    //字体
//    字体大小
    public int filterTabTextSize;
    public int filterItemTextSize;


    //    字体颜色
//    public int filterTabTextColor;
//    public int filterItemTextColor;
//    public int filterItemSelectTextColor;


    private Context context;

    //    第一行的数量（2~5）
//    @IntRange(from = 2, to = 5)
//    public int filterTabNumber;

    // 筛选栏的数据
    public List<TFilterTabInfo> tabInfoBeanList;

    //    选择列表的数据
    public Map<String, List<TFilterItemInfo>> itemInfoBeanList;
    //    选择列表的布局高度
    public Map<String, Integer> itemLayoutHeightList;
    //    选择列表的数据的默认选择项
    public Map<String, Integer> itemSelectIndexList;

    // 是否显示条目的选中标识
    public boolean showTag;

    //    监听
    public OnTFilterItemClickListener listener;


    public void init(Context context) {
        this.context = context;
//        filterTabBg = Color.parseColor("#ffffff");
//        filterAllBg = Color.parseColor("#40666666");
//        filterItemBg = Color.parseColor("#ffffff");
//        filterItemSelectBg = Color.parseColor("#dddddd");

        filterTabTextSize = 14;
        filterItemTextSize = 12;

//        filterTabNumber = 2;

        tabInfoBeanList = new ArrayList<>();
        itemInfoBeanList = new HashMap<>();
        itemLayoutHeightList = new HashMap<>();
        itemSelectIndexList = new HashMap<>();


//        context.getResources().getDisplayMetrics().density
//        filterTabTextSize = context.getResources().getDimensionPixelOffset(filterTabTextSize);

    }


    private int tabIndex = 0;

    private ViewHolder viewHolder;

    protected TFilterTabAdapter tabAdapter;
    private TFilterItemAdapter itemAdapter;

    public void initView(View rootView) {

        viewHolder = new ViewHolder(rootView);

        viewHolder.mLayoutFilterTabRv.setLayoutManager(new GridLayoutManager(context, tabInfoBeanList.size()) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });

        tabAdapter = new TFilterTabAdapter(tabInfoBeanList);
        viewHolder.mLayoutFilterTabRv.setAdapter(tabAdapter);

        tabAdapter.setOnItemClickListener((adapter, view1, position) -> {

            switch (position) {
                case 0:
                    loadItemData(position, itemInfoBeanList.get(FILTER_ITEM_ONE));
                    changeItemLayoutHeight(position, itemLayoutHeightList.get(FILTER_ITEM_ONE));
                    break;
                case 1:
                    loadItemData(position, itemInfoBeanList.get(FILTER_ITEM_TWO));
                    changeItemLayoutHeight(position, itemLayoutHeightList.get(FILTER_ITEM_TWO));
                    break;
                case 2:
                    loadItemData(position, itemInfoBeanList.get(FILTER_ITEM_THREE));
                    changeItemLayoutHeight(position, itemLayoutHeightList.get(FILTER_ITEM_THREE));
                    break;
                case 3:
                    loadItemData(position, itemInfoBeanList.get(FILTER_ITEM_FOUR));
                    changeItemLayoutHeight(position, itemLayoutHeightList.get(FILTER_ITEM_FOUR));
                    break;
                case 4:
                    loadItemData(position, itemInfoBeanList.get(FILTER_ITEM_FIVE));
                    changeItemLayoutHeight(position, itemLayoutHeightList.get(FILTER_ITEM_FIVE));
                    break;
            }
        });

        viewHolder.mLayoutFilterItemRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        itemAdapter = new TFilterItemAdapter(null);
        itemAdapter.setTag(showTag);
        viewHolder.mLayoutFilterItemRecyclerview.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(this);
//        itemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil2.showToast(context, "tabIndex , pos = " + tabIndex + " , " + position);
//            }
//        });

        viewHolder.mLayoutFilterItemRl.setOnClickListener(v -> {
            changeTabState(tabIndex, false);
            toggleShowItemLayout();
        });
        viewHolder.mLayoutFilterItemResetTv.setOnClickListener(v -> {

            if (tabIndex == 3) {
                int pos = itemSelectIndexList.get(FILTER_ITEM_FOUR);
                if (pos > -1) {
                    List<TFilterItemInfo> filterItemInfoList = itemInfoBeanList.get(FILTER_ITEM_FOUR);
                    TFilterItemInfo filterItemInfo2 = filterItemInfoList.get(pos);
                    filterItemInfo2.selectSelelct(false);
                    itemAdapter.notifyItemChanged(pos);
                    itemSelectIndexList.put(FILTER_ITEM_FOUR, -1);
                }
            }


        });
        viewHolder.mLayoutFilterItemAffirmTv.setOnClickListener(v -> {
            changeTabState(tabIndex, false);
            toggleShowItemLayout();

            if (tabIndex == 3) {
                int pos = itemSelectIndexList.get(FILTER_ITEM_FOUR);
                if (listener != null) {
                    listener.onFilterItem(tabIndex, pos, true);
                }
            }


        });

        itemAdapter.setNewData(itemInfoBeanList.get(FILTER_ITEM_ONE));
        itemAdapter.loadMoreEnd(true);

    }

    private void changeItemLayoutHeight(int position, Integer height) {
        if (height == null || height == 0) {
            height = 200;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.mLayoutFilterItemRecyclerview.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) (context.getResources().getDisplayMetrics().density * height);
        viewHolder.mLayoutFilterItemRecyclerview.setLayoutParams(layoutParams);
    }

    private boolean isShowItemLayout;

    private void showItemLayout(boolean isShowItem) {
        isShowItemLayout = isShowItem;
        if (isShowItem) {
            viewHolder.mLayoutFilterItemRl.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mLayoutFilterItemRl.setVisibility(View.GONE);
        }
    }

    /**
     * 控制 列表布局的 开关
     */
    public void toggleShowItemLayout() {
        showItemLayout(!isShowItemLayout);
    }

    private void loadItemData(int position, List<TFilterItemInfo> infoList) {

        //tab 的显示
        if (position == tabIndex) {
            changeTabState(position, false);
        } else {
            changeTabState(position, true);
        }

        //item 的显示
        if (infoList == null) {
            //没有列表项
            showItemLayout(false);

            if (listener != null) {
                TFilterTabInfo tabInfo = tabInfoBeanList.get(position);
                int state = tabInfo.getFilterTabSelectState();
                listener.onFilterItem(position, state, false);
            }

        } else {
//            有列表项
            if (tabIndex == position) {
                toggleShowItemLayout();
            } else {
                if (position == 0) {
                    viewHolder.mLayoutFilterItemRecyclerview.setLayoutManager(new LinearLayoutManager(context));
                    viewHolder.mLayoutFilterItemBottomLl.setVisibility(View.GONE);
                } else if (position == 3) {
                    viewHolder.mLayoutFilterItemRecyclerview.setLayoutManager(new GridLayoutManager(context, 2));
                    viewHolder.mLayoutFilterItemBottomLl.setVisibility(View.VISIBLE);
                }

                showItemLayout(true);
                itemAdapter.setNewData(infoList);
                itemAdapter.loadMoreEnd(true);
            }
        }

        tabIndex = position;
    }

    /**
     * 改变Tab的值
     *
     * @param pos             当前tab的位置
     * @param isClickOtherTab 是否是点击了其它的Tab
     */
    private void changeTabState(int pos, boolean isClickOtherTab) {

        if (isClickOtherTab) {
            if (pos == tabInfoBeanList.size() - 1) {
                tabInfoBeanList.get(pos).setFilterTabSelectState(2);
                if (tabIndex == 0) {
                    tabInfoBeanList.get(0).setFilterTabSelectState(1);
                }
            } else {
                for (int i = 0; i < tabInfoBeanList.size(); i++) {
                    TFilterTabInfo tabInfo = tabInfoBeanList.get(i);
                    if (i == tabInfoBeanList.size() - 1) {
                        int state = tabInfo.getFilterTabSelectState();
                        if (state > 0) {
                            tabInfo.setFilterTabSelectState(1);
                        }
                    } else if (i == pos) {
                        int state = tabInfo.getFilterTabSelectState();
                        if (state == 1) {
                            tabInfo.setFilterTabSelectState(2);
                        } else {
                            tabInfo.setFilterTabSelectState(1);
                        }
                    } else {
                        tabInfo.setFilterTabSelectState(0);
                    }
                }
                if (pos == 0) {
                    tabInfoBeanList.get(pos).setFilterTabSelectState(2);
                    tabInfoBeanList.get(tabInfoBeanList.size() - 1).setFilterTabSelectState(1);
                }
            }
        } else {
            //            点击当前的Tab：针对有多种状态的情况
            TFilterTabInfo tabInfo = tabInfoBeanList.get(pos);
            int state = tabInfo.getFilterTabSelectState();
            if (state == 1) {
                tabInfo.setFilterTabSelectState(2);
            } else {
                tabInfo.setFilterTabSelectState(1);
            }
        }
        tabAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        String key = "";
        if (tabIndex == 0) {
            //综合
            key = FILTER_ITEM_ONE;
        } else if (tabIndex == 3) {
            //品牌
            key = FILTER_ITEM_FOUR;
        }
        int pos = itemSelectIndexList.get(key);
        String tabName = "";
        if (pos == position) {
            //重复点击：根据需求
            // 针对第一个筛选项做隐藏处理
            if(tabIndex == 0){
                changeTabState(tabIndex, false);
                toggleShowItemLayout();
                if (listener != null) {
                    listener.onFilterItem(tabIndex, position, true);
                }
            }

        } else {
            List<TFilterItemInfo> filterItemInfoList = itemInfoBeanList.get(key);
            if (pos > -1) {
                TFilterItemInfo filterItemInfo = filterItemInfoList.get(pos);
                filterItemInfo.selectSelelct(false);
                itemAdapter.notifyItemChanged(pos);
            }
            TFilterItemInfo filterItemInfo2 = filterItemInfoList.get(position);
            filterItemInfo2.selectSelelct(true);
            tabName = filterItemInfo2.getFilterItemName();
            itemAdapter.notifyItemChanged(position);
            itemSelectIndexList.put(key, position);


            if (tabIndex == 0) {
                //改变状态
                changeTabState(tabIndex, false);
                toggleShowItemLayout();
                if (listener != null) {
                    listener.onFilterItem(tabIndex, position, true);
                }

            } else if (tabIndex == 3) {
                itemSelectIndexList.put(key, position);
            }

        }


    }

    static class ViewHolder {

        View view;
        private RecyclerView mLayoutFilterTabRv;
        private RecyclerView mLayoutFilterItemRecyclerview;
        private RelativeLayout mLayoutFilterItemRl;
        private LinearLayout mLayoutFilterItemBottomLl;
        private TextView mLayoutFilterItemResetTv;
        private TextView mLayoutFilterItemAffirmTv;

        public ViewHolder(View view) {
            this.view = view;
            mLayoutFilterTabRv = view.findViewById(R.id.layout_filter_tab_rv);
            mLayoutFilterItemRecyclerview = view.findViewById(R.id.layout_filter_item_recyclerview);
            mLayoutFilterItemRl = view.findViewById(R.id.layout_filter_item_rl);
            mLayoutFilterItemBottomLl = view.findViewById(R.id.layout_filter_item_bottom_ll);
            mLayoutFilterItemResetTv = view.findViewById(R.id.layout_filter_item_reset_tv);
            mLayoutFilterItemAffirmTv = view.findViewById(R.id.layout_filter_item_affirm_tv);
        }
    }


}
