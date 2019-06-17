package com.example.fwdemo.presenter;

import android.util.Log;

import com.example.fwdemo.base.RxPresenter;
import com.example.fwdemo.contract.AlgorithmContract;

import javax.inject.Inject;

/**
 * @author Alan
 * @date 2019/6/14
 */
public class AlgorithmPresenter extends RxPresenter<AlgorithmContract.View>
implements AlgorithmContract.Presenter<AlgorithmContract.View>{


    @Inject
    public AlgorithmPresenter() {

    }

    public int[] twoSum(int[] nums, int target) {


        int size = nums.length;

        for (int i = 0; i < size; i++) {
            int temp = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] >temp){
                    nums[j+1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j+1] = temp;

        }

        Log.i("tag",String.valueOf(nums));

        for (int i = 0; i < size - 1; i++) {
            int start = nums[i];
            for (int j = i+1; j < size; j++) {
                int sum = start+nums[j];
                if (target == sum){
                    Log.i("tag",i+"  "+j);
                    return new int[]{i,j};
                }else if (sum>target){
                    break;
                }
            }
        }
        return null;
    }

}
