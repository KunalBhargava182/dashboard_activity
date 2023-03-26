package com.gjr.dashboard_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Button mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mLogoutButton = findViewById(R.id.btn_logout);

        // Set up the ActionBarDrawerToggle to open and close the drawer when the hamburger icon is clicked
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the navigation menu
        ArrayList<String> navItems = new ArrayList<>();
        navItems.add("Item 1");
        navItems.add("Item 2");
        navItems.add("Item 3");

        RecyclerView navRecyclerView = findViewById(R.id.nav_list);
        navRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        navRecyclerView.setAdapter(new NavAdapter(navItems));

        // Set up the logout button
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                // TODO: Implement logout functionality here
            }
        });
    }

    // Handle clicks on navigation items
    private class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {

        private ArrayList<String> mItems;

        NavAdapter(ArrayList<String> items) {
            mItems = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(mItems.get(position));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView mTitleView;

            ViewHolder(View itemView) {
                super(itemView);
                mTitleView = itemView.findViewById(R.id.nav_item_title);
                itemView.setOnClickListener(this);
            }

            void bind(String title) {
                mTitleView.setText(title);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, mItems.get(getAdapterPosition()) + " clicked", Toast.LENGTH_SHORT).show();
                // TODO: Implement navigation item click functionality here
            }
        }
    }

    // Handle clicks on the hamburger icon
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
