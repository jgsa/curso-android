package com.jgsa.controlpersonalizadojgsa;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Memo on 06/10/2015.
 */
public class ListItem {
    private int imgView;
    private String txtView;

    public ListItem(int imgView, String txtView) {
        this.imgView = imgView;
        this.txtView = txtView;
    }

    public int getImgView() {
        return imgView;
    }

    public void setImgView(int imgView) {
        this.imgView = imgView;
    }

    public String getTxtView() {
        return txtView;
    }

    public void setTxtView(String txtView) {
        this.txtView = txtView;
    }
}