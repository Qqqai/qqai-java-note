package qqai.design.adapter;

import design.adapter.adapter.MediaAdapter;
import design.adapter.inter.impl.AudioPlayer;

/**
 * 描述：入口
 *
 * @author qqai
 * @createTime 2020-09-15 8:13
 */

public class Main {

    public static void main(String[] args) {
        MediaAdapter adapter = new MediaAdapter();
        //笔记 调用具体的播放方法，再由播放方法去适配播放器进行
        adapter.play("mp3", "beyond the horizon.mp3");
        adapter.play("mp4", "alone.mp4");
        adapter.play("vlc", "far far away.vlc");
        adapter.play("avi", "mind me.avi");
    }
}
