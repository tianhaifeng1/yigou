package com.work.doctor.fruits.dialog;

import com.contrarywind.adapter.WheelAdapter;
import com.t.httplib.yigou.bean.BandcardInterface;

import java.util.List;

public class TWheelAdapter<T extends BandcardInterface> implements WheelAdapter {

    private List<T> items;

    public TWheelAdapter(List<T> items) {
        this.items = items;
    }

    @Override
    public int getItemsCount() {
        return items.size();
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).getBankcardBankname();
        }
        return "";
    }

    @Override
    public int indexOf(Object o) {
        return items.indexOf(o);
    }
}
