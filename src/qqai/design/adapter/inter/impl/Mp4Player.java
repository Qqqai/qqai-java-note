package qqai.design.adapter.inter.impl;

import qqai.design.adapter.inter.AdvancedMediaPlayer;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-15 8:10
 */

public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }

    @Override
    public void playMp3(String fileName) {
        //什么也不做
    }
}