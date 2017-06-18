package com.vcooline.li.videoplayer.tools.download;

public interface DownloadTaskListener {
    public void updateProcess(long percent);			// 更新进度
    public void finishDownload();			// 完成下载
    public void errorDownload(String error);				// 下载错误
}
