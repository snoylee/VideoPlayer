package com.vcooline.li.videoplayer.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import com.vcooline.li.videoplayer.R;

public class TabButton extends Button {
    private int normal_bg_res;
    private int selected_bg_res;
    private int type = 0;
    private Context context;

    public TabButton(Context context) {
        super(context);
        this.context = context;
    }

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typeArray = context.obtainStyledAttributes(attrs,
                R.styleable.TabButton);
        normal_bg_res = typeArray.getResourceId(
                R.styleable.TabButton_normal_bg, 0);
        selected_bg_res = typeArray.getResourceId(
                R.styleable.TabButton_selected_bg, 0);
        type = typeArray.getInt(R.styleable.TabButton_type, 1);
        typeArray.recycle();
        setSelected(false);
    }


    public void setSelected(boolean selected) {
        if (normal_bg_res == 0 || selected_bg_res == 0) {
            switch (type) {
                case 0:
                    setBgType(selected, R.drawable.tab_mid_select,
                            R.drawable.tab_left);
                    break;
                case 1:
                    setBgType(selected, R.drawable.tab_mid_select, R.drawable.tab_mid);
                    break;
                case 2:
                    setBgType(selected, R.drawable.tab_mid_select,
                            R.drawable.tab_right);
                    break;
                default:
                    break;
            }
            return;
        }
        setBgType(selected, selected_bg_res, normal_bg_res);
        System.out.print(1);
    }

    public void setBgType(boolean isSelect, int bgSelect, int bgNor) {
        if (isSelect) {
            setBackgroundResource(bgSelect);
            setTextColor(context.getResources().getColor(R.color.tab_button_text_select_color));
        } else {
            setBackgroundResource(bgNor);
            setTextColor(Color.GRAY);
        }
    }

}
