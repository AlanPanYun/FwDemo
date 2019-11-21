
package com.example.fwdemo;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<String>> descartes(List<List<String>> dimValue) {
        List<List<String>> res = new ArrayList<>();
        if (dimValue == null || dimValue.size() == 0)
            return res;
        backtrace(dimValue, 0, res, new ArrayList<String>());
        return res;

    }

    /**
     * 递归回溯法求解
     *
     * @param dimValue 原始数据集合
     * @param index 当前执行的集合索引
     * @param result 结果集合
     * @param curList 当前的单个结果集
     */
    private void backtrace(List<List<String>> dimValue, int index,
                           List<List<String>> result, ArrayList<String> curList) {

        if (curList.size() == dimValue.size())
            result.add(new ArrayList<>(curList));
        else
            for (int j = 0; j < dimValue.get(index).size(); j++) {
                curList.add(dimValue.get(index).get(j));
                backtrace(dimValue, index + 1, result, curList);
                curList.remove(curList.size() - 1);
            }

    }
}