package com.test.tutorial2_2livedata_transformations_map.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.test.tutorial2_2livedata_transformations_map.model.User;

public class UserViewModel extends ViewModel {

    /**
     * Changes on this user LiveData triggers function that sets mUserNameLiveData String value
     */
    private MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    /**
     * This LiveData contains the data(String for this example) to be observed.
     */
    public final LiveData<String> mUserNameLiveData;

    /**
     * MediatorLiveData is what {@link Transformations#map(LiveData, Function)} does behind the scenes
     */
    public MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();


    public UserViewModel() {

        /*
         * map() method emits a value in type of destination data(String in this example) when the source LiveData is changed. In this example
         * when a new User value is set to LiveData it trigger this function that returns a String type
         *
         *              Input, Output
         * new Function<User, String>
         *
         *  public String apply(User input) { return output;}
         */

        // Result<Output>                        Source<Input>               Input, Output
        mUserNameLiveData = Transformations.map(mUserLiveData, new Function<User, String>() {
            @Override
            public String apply(User input) {
                System.out.println("UserViewModel user: " + input);
                // Output
                return input.getFirstName() + ", " + input.getLastName();
            }
        });

        /*
         * map() function is actually does this
         */
  /*
        // Result                   LiveData<X> source
        mediatorLiveData.addSource(mUserLiveData, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

                // This is same as -> result.setValue(func.apply(x))
                mediatorLiveData.setValue(user.getFirstName() + ", " + user.getLastName());
            }
        });
        */
    }

    /**
     * @param source a {@code LiveData} to listen to
     * @param func   a function to apply
     * @param <X>    a type of {@code source} LiveData
     * @param <Y>    a type of resulting LiveData.
     * @return a LiveData which emits resulting values
     */

    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> source,
                                         @NonNull final Function<X, Y> func) {

        final MediatorLiveData<Y> result = new MediatorLiveData<>();

        result.addSource(source, new Observer<X>() {

            @Override
            public void onChanged(@Nullable X x) {
                result.setValue(func.apply(x));
            }

        });

        return result;
    }


    public void setUser(User user) {
        mUserLiveData.setValue(user);
    }


}