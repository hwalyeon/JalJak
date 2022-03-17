package kr.co.seculink.domain;

import org.apache.commons.collections4.map.ListOrderedMap;

import com.google.common.base.CaseFormat;

public class MybatisMap<K, V> extends ListOrderedMap<String, V> {
    private static final long serialVersionUID = 6723434363565852261L;

    @Override
    public V put(String key, V value) {
        return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key), value);
    }
}