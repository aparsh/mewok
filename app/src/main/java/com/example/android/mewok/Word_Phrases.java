package com.example.android.mewok;

public class Word_Phrases {

    /** Default translation for the word */
    private String mDefaultTranslation1;

    /** Miwok translation for the word */
    private String mMiwokTranslation1;

    private int Audioid;



    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word_Phrases(String defaultTranslation, String miwokTranslation, int audioid) {
        mDefaultTranslation1 = defaultTranslation;
        mMiwokTranslation1 = miwokTranslation;
        Audioid=audioid;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation1() {
        return mDefaultTranslation1;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation1() {
        return mMiwokTranslation1;
    }

    public int getAudioid1(){ return Audioid;}


}
