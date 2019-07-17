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

    @Override
    public void getCarInfo(String parameter) {

    }

    @Override
    public void getCarDistanc(String parameter) {

    }

    //请求接口a参数

}
