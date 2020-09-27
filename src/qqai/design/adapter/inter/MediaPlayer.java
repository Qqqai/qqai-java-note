package qqai.design.adapter.inter;

/**
 * 描述：为媒体播放器和更高级的媒体播放器创建接口。
 *
 * @author qqai
 * @createTime 2020-09-15 8:07
 */
//笔记 顶级媒体文件接口 两个参数  一个媒体对象的类型 一个文件名字
public interface MediaPlayer {
    public void play(String audioType, String fileName);
}
