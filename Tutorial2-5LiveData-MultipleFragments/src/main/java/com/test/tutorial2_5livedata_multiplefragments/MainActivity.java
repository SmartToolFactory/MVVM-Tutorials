package com.test.tutorial2_5livedata_multiplefragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.test.tutorial2_5livedata_multiplefragments.fragment.FragmentA;
import com.test.tutorial2_5livedata_multiplefragments.fragment.FragmentB;
import com.test.tutorial2_5livedata_multiplefragments.fragment.FragmentC;

import java.util.List;

/**
 * This example demonstrates how {@link FragmentTransaction#addToBackStack(String)}, and
 * LiveData with multiple observer fragments work.
 *
 * <li>
 * {@link FragmentTransaction#addToBackStack(String)} is used to store transaction states in back stack.
 * Back button pops previous state from stack and returns the previous fragment with exact state.
 * </li>
 *
 * <li>
 * {@link FragmentTransaction#add(Fragment, String)} method adds fragment, after creating new instance of
 * the fragment but it does not mean this fragments are added to back stack if {@link FragmentTransaction#addToBackStack(String)}
 * is not called before {@link FragmentTransaction#commit()}
 * </li>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity onCreate()");

        Toast.makeText(this, "MainActivity onCreate()", Toast.LENGTH_SHORT).show();

        // Listen changes to the back stack, addToBackStack should be called to listen changes
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                Toast.makeText(MainActivity.this, "onBackStackChanged() Back Stack Fragment count: " + getSupportFragmentManager().getBackStackEntryCount()
                        + ", Current FRAGMENT: " + fragment, Toast.LENGTH_SHORT).show();
            }
        });


        FragmentA fragment = new FragmentA();

        addOrReplaceFragment(fragment);

        addListeners();

    }


    private void addOrReplaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
//                   .replace(R.id.fragment_container, fragment)
                /*
                   Add creates a new instance every time it's called causing multiple fragments to exist
                   This causes multiple fragments to
                 */
                .add(R.id.fragment_container, fragment)
                // TODO addToBackStack adds the fragment to back stack, back button returns to previous fragment
                //  .addToBackStack(null)
                .commit();

        // Number of Fragments
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        Toast.makeText(MainActivity.this, "MainActivity addOrReplaceFragment() count: " + fragmentList.size(), Toast.LENGTH_SHORT).show();

        System.out.println("MainActivity addOrReplaceFragment() fragments: " + fragmentList.toString());
    }


    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }


    private void addListeners() {

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragment = new FragmentA();
                addOrReplaceFragment(fragment);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragment = new FragmentB();
                addOrReplaceFragment(fragment);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentC fragment = new FragmentC();
                addOrReplaceFragment(fragment);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity onDestroy()");
    }
}
