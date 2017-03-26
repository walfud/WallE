package com.walfud.walle.misc;

import android.text.TextUtils;

import com.walfud.walle.collection.CollectionUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href="http://semver.org/">Semantic Versioning 2.0.0</a>
 * Created by walfud on 2015/11/8.
 */
public class Version {

    public static final String TAG = "Version";
    public static final String ALPHA = "alpha";
    public static final String BETA = "beta";
    public static final String RC = "rc";
    public static final String REL = "rel";
    private static final Set<String> sPreReleaseSet = new HashSet<>();
    static {
        sPreReleaseSet.add(ALPHA);
        sPreReleaseSet.add(BETA);
        sPreReleaseSet.add(RC);
        sPreReleaseSet.add(REL);
    }

    private int mMajor = 0;
    private int mMinor = 0;
    private int mPatch = 0;
    private String mRelStatus = REL;
    private String mBuildMetadata = "";

    private Version() {
    }
    private Version(int major, int minor, int patch, String status) {
        mMajor = major;
        mMinor = minor;
        mPatch = patch;
        mRelStatus = status;
    }

    // Function
    public int getMajor() {
        return mMajor;
    }
    public int getMinor() {
        return mMinor;
    }
    public int getPatch() {
        return mPatch;
    }

    // Get pre release status
    public boolean isPreRelease() {
        return isAlpha()
                || isBeta()
                || isRc();
    }
    public boolean isAlpha() {
        return ALPHA.equals(mRelStatus);
    }
    public boolean isBeta() {
        return BETA.equals(mRelStatus);
    }
    public boolean isRc() {
        return RC.equals(mRelStatus);
    }
    public boolean isRelease() {
        return REL.equals(mRelStatus);
    }

    // Get build metadata

    /**
     *
     * @return "" if no metadata
     */
    public String getBuildMetadata() {
        return mBuildMetadata;
    }

    // Internal

    /**
     * Parse '1.2.3' => 'mMajor.mMinor.mPatch'
     * @param version
     * @param strVer
     */
    private static void valueOfVer(Version version, String strVer) {
        String[] vers = strVer.split(Pattern.quote("."));
        version.mMajor = Integer.valueOf(vers[0]);
        version.mMinor = Integer.valueOf(vers[1]);
        version.mPatch = Integer.valueOf(vers[2]);
    }

    /**
     * If exists, must be one of:
     *      alpha
     *      beta
     *      rc
     *      rel
     *
     * @param version
     * @param strPreRelease
     */
    private static void valueOfPreRelease(Version version, String strPreRelease) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(strPreRelease)) {
            if (CollectionUtils.find(sPreReleaseSet, strPreRelease) != null) {
                version.mRelStatus = strPreRelease;
            }

            throw new IllegalArgumentException("pre release must be one of: 'alpha', 'beta', 'rc', 'rel'");
        }
    }
    private static void valueOfBuildMetadata(Version version, String strBuildMetadata) {
        version.mBuildMetadata = strBuildMetadata;
    }

    // Helper

    /**
     * We support following pattern:
     *      vMAJOR.MINOR.PATCH
     *      vMAJOR.MINOR.PATCH-pre release       (pre release MUST comprise only ASCII alphanumerics and hyphen [0-9A-Za-z-])
     *      vMAJOR.MINOR.PATCH+build metadata    (metadata MUST comprise only ASCII alphanumerics and hyphen [0-9A-Za-z-])
     *      vMAJOR.MINOR.PATCH-pre release+build metadata
     * For example:
     *
     *      v1.2.3             : same as 'v1.2.3.rel'
     *
     * pre release is fix with the following pattern:
     *      v1.2.3-alpha
     *      v1.2.3-beta
     *      v1.2.3-rc
     *      v1.2.3-rel
     *
     * Build metadata is whatever you want:
     *      v1.2.3+20130313144700
     *      v1.2.3+exp.sha.5114f85
     *      v1.2.3+bla...bla...
     *
     * @param ver
     * @return
     * @throws IllegalArgumentException
     */
    public static Version parse(String ver) throws IllegalArgumentException {
        if (ver == null) {
            throw new IllegalArgumentException("`ver` can not be null");
        }

        Pattern pattern = Pattern.compile("^(v\\d+\\.\\d+\\.\\d+)(-.+)?(\\+.+)?$");
        Matcher matcher = pattern.matcher(ver);
        if (matcher.matches()) {
            MatchResult matchResult = matcher.toMatchResult();
            String strVer = matchResult.group(1);
            // Remove prefix 'v'
            if (strVer != null) {
                strVer = strVer.substring(1);
            }
            String strPreRelease = matchResult.group(2);
            // Remove prefix '-'
            if (strPreRelease != null) {
                strPreRelease = strPreRelease.substring(1);
            }
            String strBuildMetadata = matchResult.group(3);
            // Remove prefix '+'
            if (strBuildMetadata != null) {
                strBuildMetadata = strBuildMetadata.substring(1);
            }

            Version version = new Version();
            valueOfVer(version, strVer);
            valueOfPreRelease(version, strPreRelease);
            valueOfBuildMetadata(version, strBuildMetadata);

            return version;
        }

        throw new IllegalArgumentException("wrong version pattern");
    }

    public static class Builder {
        private int mMajor = 0;
        private int mMinor = 0;
        private int mPatch = 0;
        private String mStatus = REL;
        private String mBuildMetadata = "";

        public Builder(int major, int minor, int patch) {
            setMajor(major).setMinor(minor).setPatch(patch);
        }

        public Builder setMajor(int major) {
            mMajor = major;
            return this;
        }
        public Builder setMinor(int minor) {
            mMinor = minor;
            return this;
        }
        public Builder setPatch(int patch) {
            mPatch = patch;
            return this;
        }

        public Builder setAlpha() {
            mStatus = ALPHA;
            return this;
        }
        public Builder setBeta() {
            mStatus = BETA;
            return this;
        }
        public Builder setRc() {
            mStatus = RC;
            return this;
        }
        public Builder setRelease() {
            mStatus = REL;
            return this;
        }

        public Builder setBuildMetadata(String metadata) {
            mBuildMetadata = metadata != null ? metadata : "";
            return this;
        }

        public Version build() {
            return new Version(mMajor, mMinor, mPatch, mStatus);
        }
    }
}