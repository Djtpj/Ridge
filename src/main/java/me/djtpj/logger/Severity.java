package me.djtpj.logger;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public enum Severity {
    LOG("", false),
    INFO("[INFO]", false),
    WARNING("[WARNING]", false),
    ERROR("[ERROR]", true),
    CRASH("[CRASH]", true);

    @Getter(AccessLevel.NONE)
    private final boolean USE_SERR;
    private final String TAG;

    Severity(String TAG, boolean USE_SERR) {
        this.USE_SERR = USE_SERR;
        this.TAG = TAG;
    }

    public boolean usesSerr() {
        return USE_SERR;
    }
}
