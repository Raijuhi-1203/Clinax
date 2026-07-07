package codesgesture.app.clinax;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import codesgesture.app.clinax.Models.UserModel;
import codesgesture.app.clinax.Services.UserUtil;
import codesgesture.app.clinax.Utils.SessionManage;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private ViewPager viewPager;
    LinearLayout help_layout,appoinment_layout,consult_layout,home_layout;
    ImageView imghome,imgconsult,imgappointment,imghelp;
    TextView uname,btnedit;
    UserModel userModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        userModel=(UserModel)SessionManage.getCurrentUser(getApplicationContext());

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.getMenu().getItem(0).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(7).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(8).setActionView(R.layout.menu_image);
//        navigationView.getMenu().getItem(9).setActionView(R.layout.menu_image);
        View vw=navigationView.getHeaderView(0);
        btnedit=vw.findViewById(R.id.btnedit);
        uname=vw.findViewById(R.id.uname);

        uname.setText(userModel.getPatient_name());
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3,true);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        viewPager = findViewById(R.id.viewpager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        final Adapter adapter = new Adapter(fragmentManager);
        viewPager.setAdapter(adapter);

        home_layout=findViewById(R.id.home_layout);
        consult_layout=findViewById(R.id.consult_layout);
        appoinment_layout=findViewById(R.id.appoinment_layout);
        help_layout=findViewById(R.id.help_layout);

        imghelp=findViewById(R.id.imghelp);
        imgappointment=findViewById(R.id.imgappointment);
        imgconsult=findViewById(R.id.imgconsult);
        imghome=findViewById(R.id.imghome);

        home_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imghome.setBackgroundResource(R.drawable.yellowbt);
                imgconsult.setBackgroundResource(R.drawable.roundgreybt);
                imgappointment.setBackgroundResource(R.drawable.roundgreybt);
                imghelp.setBackgroundResource(R.drawable.roundgreybt);
                viewPager.setCurrentItem(0,true);
            }
        });

        consult_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgconsult.setBackgroundResource(R.drawable.yellowbt);
                imgappointment.setBackgroundResource(R.drawable.roundgreybt);
                imghelp.setBackgroundResource(R.drawable.roundgreybt);
                imghome.setBackgroundResource(R.drawable.roundgreybt);
                viewPager.setCurrentItem(1,true);
            }
        });

        appoinment_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgappointment.setBackgroundResource(R.drawable.yellowbt);
                imghelp.setBackgroundResource(R.drawable.roundgreybt);
                imghome.setBackgroundResource(R.drawable.roundgreybt);
                imgconsult.setBackgroundResource(R.drawable.roundgreybt);
                viewPager.setCurrentItem(2,true);
            }
        });

        help_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imghelp.setBackgroundResource(R.drawable.yellowbt);
                imghome.setBackgroundResource(R.drawable.roundgreybt);
                imgconsult.setBackgroundResource(R.drawable.roundgreybt);
                imgappointment.setBackgroundResource(R.drawable.roundgreybt);
                viewPager.setCurrentItem(3,true);
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Are you sure you want to exit from Clinax!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.headerstrip), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_apoinment) {
            startActivity(new Intent(Dashboard.this, PageDoctors.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(Dashboard.this,PageContact.class));
        } else if (id == R.id.nav_consult) {
            startActivity(new Intent(Dashboard.this,PageHealthProblems.class));
        } else if (id == R.id.nav_myappoinment) {
            startActivity(new Intent(Dashboard.this, PageMyAppoinments.class));
        } else if (id == R.id.nav_condition) {
            startActivity(new Intent(Dashboard.this, PageTerm.class));

        }else if (id == R.id.nav_privacy) {
            startActivity(new Intent(Dashboard.this, PagePrivacy.class));

        }else if (id == R.id.nav_refund) {
            startActivity(new Intent(Dashboard.this, PageRefund.class));
        }else if (id == R.id.nav_shareapp) {
            UserUtil.ShareApp(Dashboard.this);
        }
//        else if (id == R.id.nav_refundamt) {
//           // startActivity(new Intent(Dashboard.this, CreditRefund.class));
//        }
        else if (id == R.id.nav_developer) {
            startActivity(new Intent(Dashboard.this, PageDeveloper.class));
        }else if (id == R.id.nav_logout) {
            SessionManage.ClearSession(getApplicationContext());
            startActivity(new Intent(Dashboard.this,MainActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class Adapter extends FragmentStatePagerAdapter {
        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new HomeFragment();

                case 1:
                    return new ConsultFragment();

                case 2:
                    return new AppoinmentFragment();

                case 3:
                    return new ProfileFragment();

                default:
                    new HomeFragment();
            }
            return null ;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
            {
                return "A";
            }
            if(position==1)
            {
                return "B";
            }
            if(position==2)
            {
                return "C";
            }
            if(position==3) {
                return "D";
            }
            return null;
        }
    }

}
