package com.example.fwdemo.rv;

import android.app.Activity;
import android.view.View;

import com.example.fwdemo.R;
import com.example.fwdemo.databinding.ItemWanAndroidBinding;

/**
 * @author Alan
 * @date 2019/11/21
 */
public class TestListAdapter extends BaseBindingAdapter<TestBean,ItemWanAndroidBinding>  {

    private Activity activity;
    public TestListAdapter(Activity activity) {
        super(R.layout.item_wan_android);
        this.activity = activity;
    }

    @Override
    protected void bindView(final TestBean bean, final ItemWanAndroidBinding binding, int position) {
        if (bean !=null){
            binding.setBean(bean);
            binding.setAdapter(TestListAdapter.this);

            binding.vbCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!binding.vbCollect.isChecked()){
                        bean.setCollect(binding.vbCollect.isChecked());
                    }
                }
            });

        }
    }
}
