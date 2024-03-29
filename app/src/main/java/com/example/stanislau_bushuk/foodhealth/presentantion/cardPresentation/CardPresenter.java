package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> implements CallBackCardPresenter {

    @Inject
    CardNetWorkModel netWorkModel;

    CardPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackCard(this);
    }

//    public void getRecipeFromUri(final String uri) {
//        netWorkModel.getRecipeFromUri(uri);
//    }

    public void getRecipeFromRealmUri(final String uri) {
        netWorkModel.getRecipeFromRealmUri(uri);
    }

    public void getEditData(final float number, final Data data) {
        final EditData editData = EditData.newBuilder()
                .setCalories(data.getCalories() * data.getYield() / number)
                .setENERC_KCAL(data.getENERC_KCAL().getQuantity() * data.getYield() / number)
                .setChocdf(data.getChocdf().getQuantity() * data.getYield() / number)
                .setFat(data.getFat().getQuantity() * data.getYield() / number)
                .setProt(data.getProt().getQuantity() * data.getYield() / number)
                .setYield(data.getYield())
                .build();
        getViewState().showEditData(editData);
    }

    @Override
    public void call(final Observable<Recipe> observable) {
        observable.subscribeOn(Schedulers.io())
                .map(new Function<Recipe, Data>() {
                    @Override
                    public Data apply(final Recipe recipe) {

                        return Data.newBuilder()
                                .setFat(recipe.getTotalNutrients())
                                .setProt(recipe.getTotalNutrients())
                                .setChocdf(recipe.getTotalNutrients())
                                .setCalories(recipe)
                                .setYield(recipe)
                                .setENERC_KCAL(recipe.getTotalDaily())
                                .setLabel(recipe)
                                .setImage(recipe)
                                .setIngridients(recipe)
                                .build();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                    }

                    @Override
                    public void onNext(final Data data) {
                        Timber.e("onNext");
                        getViewState().showList(data);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
