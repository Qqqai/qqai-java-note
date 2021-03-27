package study.constant;

/**
 * 具有唯一编码的 enum
 *
 * @author Lonre Wang
 */
public interface CableEnum {
  /**
   * 根据唯一编码获取 enum 类型
   */
  static <E extends CableEnum> E ofCode(Class<E> enumType, Integer code) {
    if (code == null) {
      return null;
    }
    E[] enums = enumType.getEnumConstants();
    for (E enm : enums) {
      if (code == enm.getCode()) {
        return enm;
      }
    }
    return null;
  }

  int getCode();

  String getText(String messageSource);
}
