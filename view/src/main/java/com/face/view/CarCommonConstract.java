package com.face.view;

/**
 * @author Alan
 * @date 2019/6/5
 */
public interface CarCommonConstract {

    interface CarCommonView {
        void onResult(Object o);
    }

    interface CarCommonPresenterInter {

        void getCarInfo(String parameter);

        void getCarDistanc(String parameter);
    }
}
