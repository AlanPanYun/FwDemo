package com.face.view;

/**
 * @author Alan
 * @date 2019/6/5
 */
public class CarCommonPresenter
        implements CarCommonConstract.CarCommonPresenterInter {


    private CarCommonConstract.CarCommonView mView;

    public CarCommonPresenter(CarCommonConstract.CarCommonView view) {
        mView = view;
    }

    //请求接口a参数
    private void getCarInfo(String parameter) {
        ......
        Object o;// 接口获取的数据
        mView.onResult(o);
    }

    //请求接口b参数
    private void getCarDistanc(String parameter) {
        ......
        Object o;// 接口获取的数据
        mView.onResult(o);
    }
}
