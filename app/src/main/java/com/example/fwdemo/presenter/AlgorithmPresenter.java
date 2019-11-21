package com.example.fwdemo.presenter;

import android.util.Log;

import com.example.fwdemo.base.RxPresenter;
import com.example.fwdemo.contract.AlgorithmContract;
import com.face.library.utils.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * @author Alan
 * @date 2019/6/14
 */
public class AlgorithmPresenter extends RxPresenter<AlgorithmContract.View>
        implements AlgorithmContract.Presenter<AlgorithmContract.View> {


    @Inject
    public AlgorithmPresenter() {

    }

    public int[] twoSum(int[] nums, int target) {


        int size = nums.length;

        for (int i = 0; i < size; i++) {
            int temp = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > temp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = temp;

        }

        Log.i("tag", String.valueOf(nums));

        for (int i = 0; i < size - 1; i++) {
            int start = nums[i];
            for (int j = i + 1; j < size; j++) {
                int sum = start + nums[j];
                if (target == sum) {
                    Log.i("tag", i + "  " + j);
                    return new int[]{i, j};
                } else if (sum > target) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * 计算字符串中无重复字符的最长字符串长度
     * start
     */
    //滑动窗口法
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
            Log.i("char", GsonUtil.getJsonStringFromObject(set));
        }
        return ans;
    }

    /**
     * 字符串中最大无重复字符串长度，滑动窗口，1 map无重复字符，每次记录最大重复字符位置，2 移动游标 为重复字符的下一位
     *
     * @param s
     * @return
     */
    //优化  abcdbrjiklop  10  9
    public int letgthOfLongString(String s) {
        int n = s.length();
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(map.get(s.charAt(i)), j);
                Log.i("letgthOfLongString", j + " + " + i);
            }

            ans = Math.max(ans, i - j + 1);
            map.put(s.charAt(i), i + 1);
        }
        Log.i("letgthOfLongString", " = " + ans);
        return ans;
    }

    public String longestPalindrome(String s) {
        List<Character> list = new ArrayList<>();
        int n = s.length();
        int y = 0;
        String ans = "";

        while (y < n) {
            char c = s.charAt(y);
            if (!list.contains(c)) {
                list.add(c);
                y++;
            } else {
                if (list.get(0).equals(c)) {
                    ans = list.toString() + c;
                } else {
                    list.remove(0);
                }
            }
        }
        Log.i("ans", ans);
        return ans;
    }

    /**
     * 两有序数组的中位数
     * <p>
     * 123456789,256789
     */

    public double middleNumInByte(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;

    }


    /**
     * 1 单个字符为中心， 2 两个字符 为中心
     * 最长回文字符串
     *
     * @param s
     * @return cdcddcf
     */
    public String longestPalindromeS(String s) {

        int length = s.length();
        int len, start = 0, end = 0;

        for (int i = 0; i < length; i++) {
            int len1 = centerSplit(s, i, i);//单个字符中心
            int len2 = centerSplit(s, i, i + 1);//双字符中心
            len = Math.max(len2, len1);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = len / 2 + i;
            }

        }

        return s.substring(start, end + 1);

    }

    private int centerSplit(String s, int l, int r) {
        int L = l, R = r;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L + 1;

    }

    public List<List<String>> dikaer(List<List<String>> dimValue) {

        List<List<String>> res = new ArrayList<>();
        if (dimValue == null || dimValue.size() == 0) {
            return res;
        }


        backtrace(dimValue, 0, res, new ArrayList<String>());

        return res;
    }

    /**
     * @param dimValue 两个集合的合集
     * @param index    第几个集合
     * @param res      结构
     * @param curList  每次递归的集合
     */
    private void backtrace(List<List<String>> dimValue, int index,
                           List<List<String>> res, ArrayList<String> curList) {

        if (curList.size() == dimValue.size()) {
            res.add(curList);
        } else {
            for (int i = 0; i < dimValue.get(index).size(); i++) {
                curList.add(dimValue.get(index).get(i));
                backtrace(dimValue, index + 1, res, curList);
                curList.remove(curList.size() - 1);//一次轮询结束 清空集合
            }
        }

    }


    //z形排列
    public String convert(String s, int numRows) {

        if (s == null || s.length() <= 0) {
            return null;
        }

        if (numRows <= 1) {
            return s;
        }
        int le = numRows + numRows - 2;//一组长度
        int length = s.length();

        int num = length / le; //  有几组

        if (length % le != 0) {
            num++;
        }


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numRows; i++) {

            getChar(sb, s, 0, i, numRows, num, le);
        }

        Log.i("substring", sb.toString());

        return sb.toString();
    }

    private void getChar(StringBuffer sb, String s, int index, int num, int numRows, int sum, int le) {
        if (index >= sum) {
            return;
        }
        String charS = s.substring(index * le, Math.min((index + 1) * le, s.length()));

        if (num < charS.length()) {
            sb.append(charS.charAt(num));
        }
        int end = numRows + (numRows - num) - 2;
        if (end < charS.length() && end != numRows - 1) {
            sb.append(charS.charAt(end));
        }

        getChar(sb, s, ++index, num, numRows, sum, le);

    }

    public int reverse(int x) {

        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE%10)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE%10)) return 0;
            res = res * 10 + pop;
        }

        return res;
    }
}