package com.example.android.miwok;

/**
 * (@link Word} represents a vocabulary word that the user wants to learn.
 * it contains a default translation and a Miwok translation for that word.
 * Created by pmeno on 04/04/2017.
 */

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * @return Get default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * @return Get miwork translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

}
