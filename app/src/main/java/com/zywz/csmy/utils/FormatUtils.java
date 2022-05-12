package com.zywz.csmy.utils;

import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 数字转化工具类
 */
public class FormatUtils {

    private FormatUtils(){}
    private static FormatUtils instance = null;
    public static FormatUtils getInstance() {
        if (instance == null) {
            synchronized(FormatUtils.class) {
                if (instance == null) {
                    instance = new FormatUtils();
                }
            }
        }
        return instance;
    }

    private Random r;

    //第二个参数是设置到小数点后几位 0则不显示 1则显示0.1位
    public String formatNumberWithUnit(String needFormatNumber,int point) {

        String nuit = "",formatNumStr = "";
        StringBuffer sb = new StringBuffer();
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal needNum = new BigDecimal(needFormatNumber);

        if (needNum.compareTo(b1) == -1) {
            sb.append(needNum.toString());
        }
        // 以万为单位处理
        else if ((needNum.compareTo(b1) == 0 || needNum.compareTo(b1) == 1)) {
            formatNumStr = needNum.divide(b1).setScale(point, BigDecimal.ROUND_HALF_UP).toString();
            nuit = "万字";
            sb.append(formatNumStr).append(nuit);
        }

        return sb.toString();
    }

    /**
     * 获取随机子列表
     * @param source 原列表
     * @param limit 子列表长度
     * @param <T> 列表原类型
     * @return 子列表
     */
    public <T> List<T> newRandomList(List<T> source, int limit) {
        if(source == null || source.size() == 0 || source.size() <= limit){
            return source;
        }

        Set<Integer> set = createRandomSet(source.size(), limit);
        Integer[] array = set.toArray(new Integer[0]);
        return new RandomList<>(source, array);
    }

    /**
     * 创建一个随机的有序下标Set
     * @param listSize 原列表长度
     * @param limit 子列表长度
     * @return 随机的下标Set
     */
    private Set<Integer> createRandomSet(int listSize, int limit) {
        Random rnd = r;
        if (rnd == null)
            r = rnd = new Random();

        Set<Integer> set = new HashSet<>(limit);
        for (int i = 0; i < limit; i++) {
            int value = rnd.nextInt(listSize);
            if (!add(set, value, listSize)) {
                return set;
            }
        }
        return set;
    }
    /**
     * 往Set中添加一个随机值，如果有冲突，则取随机值+1
     */
    private boolean add(Set<Integer> set, int value, int size) {
        if (set.size() == size) {
            return false;
        }

        if (!set.contains(value)) {
            return set.add(value);
        }

        int nextValue = value + 1;
        if (nextValue == size) {
            nextValue = 0;
        }
        return add(set, nextValue, size);
    }

    private class RandomList<T> extends AbstractList<T> {
        final List<T> list;
        final Integer[] indexs;

        RandomList(List<T> list, Integer[] indexs) {
            this.list = list;
            this.indexs = indexs;
        }

        @Override
        public T get(int index) {
            if(index < 0 || index >= indexs.length)
                throw new IndexOutOfBoundsException("The start index was out of bounds: "
                        + index + " >= " + indexs.length);
            int start = indexs[index];
            return list.get(start);
        }

        @Override
        public int size() {
            return indexs.length;
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

}
