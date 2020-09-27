package qqai.design.adapter.inter.impl;

import qqai.design.adapter.inter.AdvancedMediaPlayer;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 8:08
 */

public class VlcPlayer implements AdvancedMediaPlayer {
    //笔记 这个类也可以实现对于媒体播放器的适配 加上适配器就好

    //笔记 播放器的具体实现
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp3(String fileName) {
        //什么也不做
    }
}
