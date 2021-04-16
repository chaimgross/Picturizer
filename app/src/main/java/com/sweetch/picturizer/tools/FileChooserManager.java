package com.sweetch.picturizer.tools;

import com.sweetch.PicturizerApplication;
import com.sweetch.picturizer.Constants;

public class FileChooserManager {

    /**
     * Gets next file name in array
     * @return File Name
     */
    public String getFileName() {
        int currentIndex = PicturizerApplication.preferences.getInt(Constants.SHARED_PREF_FILE_INDEX, 0);
        return Constants.FILE_NAMES_ARRAY[currentIndex];
    }

    /**
     * After image has already been shown, increment index
     */
    public void incrementIndex() {
        int newIndex;
        int currentIndex = PicturizerApplication.preferences.getInt(Constants.SHARED_PREF_FILE_INDEX, 0);
        newIndex = currentIndex + 1;
        if (newIndex == (Constants.FILE_NAMES_ARRAY.length)) {
            newIndex = 0;
        }

        PicturizerApplication.preferences.edit().putInt(Constants.SHARED_PREF_FILE_INDEX, newIndex).apply();
    }
}
