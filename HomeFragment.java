package com.viralmeme.viralmeme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.viralmeme.viralmeme.Api.SignUpApi;
import com.viralmeme.viralmeme.Api.SignUpRetrofitClient;
import com.viralmeme.viralmeme.R;
import com.viralmeme.viralmeme.activity.CreditDetails;
import com.viralmeme.viralmeme.activity.UploadStories;
import com.viralmeme.viralmeme.adapters.MainViewPagerAdapter;
import com.viralmeme.viralmeme.adapters.RecyclerViewAdapter;
import com.viralmeme.viralmeme.models.ProfileResponse;
import com.viralmeme.viralmeme.models.Story;
import com.viralmeme.viralmeme.models.Storymain;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;
import io.ghyeok.stickyswitch.widget.StickySwitch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment  {
    TabLayout mainpagestabs;
    ViewPager viewPager;
    MainViewPagerAdapter viewPagerAdapter;
    LinearLayout credits;
    TextView creditAmount;
    AppCompatImageView setting,gold;
    /*ArrayList<Story> allIdeasList;*/
    private List<Story> allIdeasList = new ArrayList<>();
    Context context;
    String name;
    int aft=0,com=0;
    StickySwitch daynightstickyswitch_;
    CircleImageView circleImageView;
    TextView responseText;
    SignUpApi apiInterface;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter rogerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);



        SharedPreferences sharedPref =getActivity().getSharedPreferences("User auth",MODE_PRIVATE);
        final String token2=sharedPref.getString("KEY","No token");
        Log.d("TAG",token2+"");
        Toast.makeText(getContext(), ""+token2, Toast.LENGTH_SHORT).show();

        apiInterface = SignUpRetrofitClient.getInstance().getApi();
        /* Call<List<StroryMain>> call3 = SignUpRetrofitClient.getInstance().getApi().getallstory(token2);*/
        
        Call<List<Storymain>> call = apiInterface.getallstory(token2);
        call.enqueue(new Callback<List<Storymain>>() {
            @Override
            public void onResponse(Call<List<Storymain>> call, Response<List<Storymain>> response) {

                if(response.isSuccessful()&&response.body()!=null){
                    for(int j=0;j<response.body().get(j).getAllIdeasList().size();j++) {
                            allIdeasList.add(response.body().get(j).getAllIdeasList().get(j));

                        Toast.makeText(getContext(), ""+response.body().get(j).getAllIdeasList().get(j), Toast.LENGTH_SHORT).show();
                        Log.i("sagar",""+response.body().get(j).getAllIdeasList().size());
                            recyclerView = view.findViewById(R.id.stories);
                            allIdeasList=new ArrayList<>();
                            rogerAdapter = new RecyclerViewAdapter(getContext(), allIdeasList);
                           recyclerView.setHasFixedSize(true);
                           LinearLayoutManager llm = new LinearLayoutManager(getContext());
                           llm.setOrientation(LinearLayoutManager.VERTICAL);
                           recyclerView.setLayoutManager(llm);
                           recyclerView.setAdapter( rogerAdapter );
                        }





                    }
                    }








            @Override
            public void onFailure(Call<List<Storymain>> call, Throwable t) {
                call.cancel();
                Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });






        mainpagestabs = view.findViewById(R.id.mainpagetabs);
        circleImageView = (CircleImageView) view.findViewById(R.id.story);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent go = new Intent(context, UploadStories.class);
             context.startActivity(go);
            }
        });

        /*SharedPreferences sharedPref = getActivity().getSharedPreferences("User auth", MODE_PRIVATE);*/
        final SharedPreferences.Editor editor = sharedPref.edit();
        aft=sharedPref.getInt("AFT",0);
        com=sharedPref.getInt("COM",0);
        String token = sharedPref.getString("KEY", "No token");
        viewPager = view.findViewById(R.id.mainviewpager);
        creditAmount = view.findViewById(R.id.creditamount);
        creditAmount.setText("");
        credits = view.findViewById(R.id.credits);
        gold = view.findViewById(R.id.goldmedal);
        credits.setEnabled(false);
        daynightstickyswitch_ = view.findViewById(R.id.daynightstickyswitch);
        if (!sharedPref.getBoolean("NIGHT", false)) {
            daynightstickyswitch_.setDirection(StickySwitch.Direction.LEFT, false);
        } else {
            daynightstickyswitch_.setDirection(StickySwitch.Direction.RIGHT, false);
        }
        // setting=view.findViewById(R.id.settings);
        context = container.getContext();
        viewPagerAdapter = new MainViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        daynightstickyswitch_.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {
                Log.i("theme", direction.name());
                if (s.equals("DAY")) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            daynightstickyswitch_.setDirection(StickySwitch.Direction.LEFT);
                            editor.putBoolean("NIGHT", false);
                            editor.commit();
                        }
                    }, 500);

                }
                if (s.equals("DARK")) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            daynightstickyswitch_.setDirection(StickySwitch.Direction.RIGHT);
                            editor.putBoolean("NIGHT", true);
                            editor.commit();
                        }
                    }, 500);

                }
            }
        });
        mainpagestabs.setupWithViewPager(viewPager);

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getToken();*/
                Fragment nextFrag = new TopMemeandMemer();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentreplace, nextFrag).addToBackStack(null).commit();
            }
        });
        TabLayout.Tab tab = mainpagestabs.getTabAt(1);
        tab.select();
       /* setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nextFrag= new SettingsFragment();
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentreplace,nextFrag).addToBackStack(null).commit();
            }
       });*/
        Call<ProfileResponse> call2 = SignUpRetrofitClient.getInstance().getApi().getprofile(token);
        call2.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("credit", "prof");
                    creditAmount.setText(response.body().getCredit() + "");
                    credits.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(creditAmount.getText().toString()) == 500) {
                    startActivity(new Intent(getContext(), CreditDetails.class));
                } else if (Integer.parseInt(creditAmount.getText().toString()) > 500) {
                    startActivity(new Intent(getContext(), CreditDetails.class));
                } else {
                    CreditZeroPop popUpAwards = new CreditZeroPop();
                    popUpAwards.showPopupWindow(view.findViewById(R.id.homefragment));
                }
            }
        });
        return view;
    }

    public class Message extends FirebaseMessagingService {
        public Message() {

        }

        @Override
        public void onNewToken(@NonNull String s) {
            super.onNewToken(s);
            Log.d("prince", s + " ");
            Log.d("prince", " toke  ");
            FirebaseInstanceId.getInstance().getInstanceId().
                    addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<InstanceIdResult> task) {
                            Log.d("prince", " tokennn  ");
                            if (!task.isSuccessful()) {
                                Log.i("prince", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();

                            // Log and toast

                            Log.d("prince t", token + " ");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("prince", e.toString() + " ");
                }
            });
        }

    }
}
