package com.apollo.cms.demo.service;

import com.apollo.cms.demo.domain.Item;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {
    private static final ArrayList<Item> ITEMS = new ArrayList<>();

    static{
        ITEMS.add(new Item("Mon",12));
        ITEMS.add(new Item("Tue",12));
        ITEMS.add(new Item("Wed",12));
        ITEMS.add(new Item("Thu",12));
        ITEMS.add(new Item("Fri",12));
        ITEMS.add(new Item("Sat",12));
        ITEMS.add(new Item("Sun",12));
    }

    public Map<String, ?> toData() {
        ITEMS.forEach(e -> e.setNum(e.getNum() + (int) (Math.random() * 5 + 1)));

        HashMap<String, Object> map = new HashMap<>();
        map.put("xAxisData", EntityUtils.toList(ITEMS, Item::getName));
        map.put("yAxisData", EntityUtils.toList(ITEMS, Item::getNum));
        return map;
    }
}
