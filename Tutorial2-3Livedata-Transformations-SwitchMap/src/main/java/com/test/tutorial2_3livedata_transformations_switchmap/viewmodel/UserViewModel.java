package com.test.tutorial2_3livedata_transformations_switchmap.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.tutorial2_3livedata_transformations_switchmap.model.User;
import com.test.tutorial2_3livedata_transformations_switchmap.repository.Repository;

public class UserViewModel extends ViewModel {

    /**
     * This is like query LiveData<String> to get LiveData<User> when changed
     */
    private MutableLiveData<String> mUserNameLiveData = new MutableLiveData<>();

    /**
     * Changes on mUserNameLiveData changes mUserLiveData
     */
    public final LiveData<User> mUserLiveData;


    public MediatorLiveData<User> userMediatorLiveData = new MediatorLiveData<>();

    public MutableLiveData<Integer> counterLiveData = new MutableLiveData<>();


    public UserViewModel() {
        // Dummy Repository that returns a LiveData<User>
        Repository repository = new Repository();

        /*
         *              Input, Output
         * new Function<String, LiveData<User>>
         *
         *  public LiveData<User> apply(String input) { return output;}
         */

        // Result<Output>                         Source<Input>                   Input,  Result<Output>
        mUserLiveData = Transformations.switchMap(mUserNameLiveData, new Function<String, LiveData<User>>() {
            @Override

            // Result<Output>
            public LiveData<User> apply(String input) {
                return repository.getUserLiveData(input);
            }
        });

        /*
         * With MediatorLiveData it can be expressed like this
         */

        userMediatorLiveData.addSource(mUserNameLiveData, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                User user = repository.getUserLiveData(s).getValue();
                userMediatorLiveData.setValue(user);
            }
        });


        userMediatorLiveData.addSource(counterLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

            }
        });
    }


    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> trigger,
                                               @NonNull final Function<X, LiveData<Y>> func) {

        final MediatorLiveData<Y> result = new MediatorLiveData<>();

        System.out.println("UserViewModel switchMap() TRIGGER: " + trigger);
        System.out.println("UserViewModel switchMap() RESULT: " + result);

        result.addSource(trigger, new Observer<X>() {

            LiveData<Y> mSource;

            @Override
            public void onChanged(@Nullable X x) {

                LiveData<Y> newLiveData = func.apply(x);

                System.out.println("UserViewModel switchMap()->onChanged() newLiveData: " + newLiveData);

                if (mSource == newLiveData) {
                    return;
                }

                if (mSource != null) {
                    result.removeSource(mSource);
                }

                mSource = newLiveData;

                if (mSource != null) {
                    result.addSource(mSource, new Observer<Y>() {
                        @Override
                        public void onChanged(@Nullable Y y) {
                            result.setValue(y);
                        }
                    });
                }

                System.out.println("UserViewModel switchMap()->onChanged() mSource: " + mSource);

            }
        });

        return result;
    }

    /**
     * Setting value of {@link UserViewModel#mUserNameLiveData} triggers update on {@link UserViewModel#mUserLiveData}
     *
     * @param userName to trigger update
     */
    public void setUserName(String userName) {
        mUserNameLiveData.setValue(userName);
    }


}