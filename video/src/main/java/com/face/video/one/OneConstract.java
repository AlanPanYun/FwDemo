package com.face.video.one;

import com.face.library.base.BaseConstract;

/**
 * @author Alan
 * @date 2019/7/5
 */
public interface OneConstract {

    interface OneView extends BaseConstract.BaseView{


    }


    interface OnePresenter<T> extends BaseConstract.BasePresenter<T>{

    }
}
