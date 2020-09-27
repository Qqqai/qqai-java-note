package qqai.design.adapter.inter;

/**
 * 描述：接口
 *
 * @author qqai
 * @createTime 2020-09-15 8:08
 */
//笔记 顶级媒体播放器的接口
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);

    public void playMp4(String fileName);

    void playMp3(String fileName);
}