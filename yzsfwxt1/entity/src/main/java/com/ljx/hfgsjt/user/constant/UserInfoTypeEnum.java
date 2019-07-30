package com.ljx.hfgsjt.user.constant;

/**
 * UserInfo Type
 */
public enum UserInfoTypeEnum {

    MANAGER(1, "管理用户"),
    EXPERT(2, "评标专家"),
    SUPPLIER(3, "供应商");

    private int value;
    private String label;

    UserInfoTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static UserInfoTypeEnum get(Integer value) {
        UserInfoTypeEnum[] types = UserInfoTypeEnum.values();

        for (UserInfoTypeEnum type : types) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }

}
