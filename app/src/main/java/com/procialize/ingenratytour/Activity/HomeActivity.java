package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.procialize.ingenratytour.Adapter.GridViewAdapter;
import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductsPost;
import com.procialize.ingenratytour.CustomFonts.MenuFont;
import com.procialize.ingenratytour.GetterSetter.AboutTour;
import com.procialize.ingenratytour.GetterSetter.Description;
import com.procialize.ingenratytour.GetterSetter.DrawerGallery;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import javax.sql.DataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private LinearLayout contentView;
    private Button startbtn, okaybtn, cancelbtn;
    BottomSheetDialog mBottomSheetDialog;
    View sheetView;
    TextView textView;
    ImageView imageView;
    APIService mAPIService;
    Util.TransparentProgressDialog pd;
    SessionManager sessionManager;
    String token;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        MultiDex.install(this);
        initializeView();
        mAPIService = ApiUtils.getAPIService();

        sessionManager = new SessionManager(this);
        if (Util.isNetworkConnected(this)) {
            try {
                if (pd == null) {
                    pd = new Util.TransparentProgressDialog(HomeActivity.this);
                    pd.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
            Description(token);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void initializeView() {

        mBottomSheetDialog = new BottomSheetDialog(HomeActivity.this);
        sheetView = getLayoutInflater().inflate(R.layout.botomdialouge, null);
        mBottomSheetDialog.setContentView(sheetView);


        okaybtn = sheetView.findViewById(R.id.okaybtn);
        cancelbtn = sheetView.findViewById(R.id.cancelbtn);

        textView = findViewById(R.id.descriptionTv);
        imageView = findViewById(R.id.imageView);
        progressBar2 = findViewById(R.id.progressBar2);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        startbtn = findViewById(R.id.startbtn);

        contentView = findViewById(R.id.content);

        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeDrawer();
        applyfontsDrawerMenu();
        animationDrawer();


        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startbtn.startAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.onclick));
                Intent details = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                startActivity(details);
            }
        });

        okaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
    }

    private void animationDrawer() {

//        toolbar.setNavigationIcon(new DrawerArrowDrawable(this));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                                                 @Override
//                                                 public void onClick(View v) {
//                                                     if (drawerLayout.isDrawerOpen(navigationView)) {
//                                                         drawerLayout.closeDrawer(navigationView);
//                                                     } else {
//                                                         drawerLayout.openDrawer(navigationView);
//                                                     }
//                                                 }
//                                             }
//        );
//
//
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//                                     @Override
//                                     public void onDrawerSlide(View drawer, float slideOffset) {
//
//
//                                         contentView.setX(navigationView.getWidth() * slideOffset);
//                                         RelativeLayout.LayoutParams lp =
//                                                 (RelativeLayout.LayoutParams) contentView.getLayoutParams();
//                                         lp.height = drawer.getHeight() -
//                                                 (int) (drawer.getHeight() * slideOffset * 0.3f);
//                                         lp.topMargin = (drawer.getHeight() - lp.height) / 2;
//                                         contentView.setLayoutParams(lp);
//                                     }
//
//                                     @Override
//                                     public void onDrawerClosed(View drawerView) {
//                                     }
//                                 }
//        );

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void initializeDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:

                        return true;
                    case R.id.about:

//                        Intent about = new Intent(getApplicationContext(),ProductBroucherActivity.class);
//                        startActivity(about);
                        return true;

                    case R.id.gallery:

                        Intent gallery = new Intent(getApplicationContext(), GalleryActivity.class);
                        startActivity(gallery);
                        return true;

                    case R.id.policy:

                        Intent policy = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                        startActivity(policy);
                        return true;

                    case R.id.condition:

                        Intent condition = new Intent(getApplicationContext(), Condition.class);
                        startActivity(condition);
                        return true;

                    case R.id.experience:

                        Intent share = new Intent(getApplicationContext(), ShareExperienceActivity.class);
                        startActivity(share);
                        return true;

                    case R.id.subscribe:

                        Intent subscribe = new Intent(getApplicationContext(), SubscribeActivity.class);
                        startActivity(subscribe);
                        return true;

                    case R.id.contact:

                        Intent contact = new Intent(getApplicationContext(), ContactUsActivity.class);
                        startActivity(contact);
                        return true;

                    case R.id.route:

                        Intent route = new Intent(getApplicationContext(), MappingActivity.class);
                        startActivity(route);
                        return true;

                    case R.id.logout:

                        sessionManager.logoutUser();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


    }

    public void applyfontsDrawerMenu() {
        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "SiemensSansBold.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new MenuFont("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START) == true) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            bottomDialouge();
        }

    }

    public void bottomDialouge() {


        if (mBottomSheetDialog.isShowing()) {
            mBottomSheetDialog.dismiss();
        } else {

            mBottomSheetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            mBottomSheetDialog.show();

        }
    }


    public void Description(String token) {
        progressBar2.setVisibility(View.VISIBLE);
        mAPIService.IntegrityTourGet(new ProductsPost(token)).enqueue(new Callback<AboutTour>() {
            @Override
            public void onResponse(Call<AboutTour> call, Response<AboutTour> response) {

                if (response.isSuccessful()) {
                    Log.i("hit", "post submitted to API." + response.body().toString());
                    try {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showResponse(response);
                } else {
                    try {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("hit", "post submitted to API Wrong." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<AboutTour> call, Throwable t) {
                Log.e("hit", "Unable to submit post to API.");
                Log.e("hit", "Unable to submit post to API."+ " "+t.getMessage());

                try {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showResponse(Response<AboutTour> response) {

        if (response.body().getSuccess().equals("true")) {



            textView.setText(response.body().getData().getDescription());
            textView.setMovementMethod(new ScrollingMovementMethod());

            Glide.with(this).load(response.body().getData().getImagePath())
                    .apply(RequestOptions.skipMemoryCacheOf(false))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    e.printStackTrace();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                    progressBar2.setVisibility(View.GONE);
                    return false;
                }

            }).into(imageView).onLoadStarted(this.getDrawable(R.drawable.imgh));


        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
