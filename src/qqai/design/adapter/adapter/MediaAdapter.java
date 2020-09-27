package qqai.design.adapter.adapter;

import qqai.design.adapter.inter.AdvancedMediaPlayer;
import qqai.design.adapter.inter.MediaPlayer;
import qqai.design.adapter.inter.impl.AudioPlayer;
import qqai.design.adapter.inter.impl.Mp4Player;
import qqai.design.adapter.inter.impl.VlcPlayer;

/**
 * 描述：创建实现了 MediaPlayer 接口的适配器类。
 *
 * @author qqai
 * @createTime 2020-09-15 8:10
 */
//笔记 媒体文件适配类
public class MediaAdapter implements MediaPlayer {
    //笔记 这里是对于媒体播放类的实现播放的类
    AdvancedMediaPlayer advancedMusicPlayer;

    //笔记 调用具体实现的播放方法
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
            advancedMusicPlayer.playMp4(fileName);
        } else if (audioType.equalsIgnoreCase("mp3")) {
            advancedMusicPlayer = new AudioPlayer();
            advancedMusicPlayer.playMp3(fileName);
        }
    }
}
