package com.example.agrimitra.views.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.agrimitra.views.fragments.ChatFragment;
import com.example.agrimitra.views.fragments.StatusFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {


    public TabsAccessorAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : ChatFragment chatFragment = new ChatFragment();
                        return chatFragment;
            case 1 : StatusFragment statusFragment = new StatusFragment();
                return statusFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Chats";
            case 1 : return "Status";
        }
        return null;
    }
}
