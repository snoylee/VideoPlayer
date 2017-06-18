package com.vcooline.li.videoplayer.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Trace on 2014/9/21.
 */
public class GroupBean  implements Serializable{
    public String title = "";
    @SuppressWarnings("rawtypes")
    public ArrayList<SketchBean> childrenList = new ArrayList<SketchBean>();
}
