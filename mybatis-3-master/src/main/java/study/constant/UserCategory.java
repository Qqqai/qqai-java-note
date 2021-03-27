package study.constant;

/**
 * @author chenaiquan
 * 用户类型 by chenaiquan 2021/03/2021/3/18 17.42
 */
public enum UserCategory implements CableEnum {
  USER(1),
  KA(2);
  private int code;

  UserCategory(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getText(String messageSource) {
    return null;
  }
}
