package qqai.design.adapter.inter.impl;

import qqai.design.adapter.adapter.MediaAdapter;
import qqai.design.adapter.inter.AdvancedMediaPlayer;
import qqai.design.adapter.inter.MediaPlayer;

/**
 * 描述：
 *
 * @author qqai
 * @createTime 2020-09-15 8:12
 */

public class AudioPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }

    //笔记 播放器的具体实现
    @Override
    public void playMp3(String fileName) {
        System.out.println("Playing mp3 file. Name: " + fileName);
    }
}
