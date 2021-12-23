package com.example.autoframework.profile;

import org.assertj.core.util.Strings;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ProfileHolder
 * @Description
 * @date 2021/12/23 11:08
 */
public class ProfileHolder {

    private volatile AtomicBoolean isProfileSet = new AtomicBoolean(false);
    private volatile String active;

    private ProfileHolder() {

    }

    private static class ClassHolder {
        private static final ProfileHolder HOLDER = new ProfileHolder();
    }

    public static ProfileHolder of() {
        return ClassHolder.HOLDER;
    }

    public void setProfile(String profile) {
        if (isProfileSet.get()) {
            return;
        }
        this.active = profile;
        this.isProfileSet.set(true);

    }

    public String getProfile() {
        // 兜底 default
        return Strings.isNullOrEmpty(this.active) ? "default" : this.active;
    }

}
