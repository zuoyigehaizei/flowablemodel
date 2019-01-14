package com.example.flowableactivity.constant;

public enum WorkflowStepTypeEnum {

    START("开始", 1),
    GENERAL("普通", 2),
    AUDIT_DETERMINE("审核判定", 3),
    CONDITION_DETERMINE("条件判定", 4),
    COUNTERSIGN("会签", 5),
    FINISH("结束", 6);


    private String name;
    private int value;

    WorkflowStepTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 转换为说明文字
     * @return
     */
    public String toName() {
        return name;
    }

    /**
     * 转换为说明文字
     * @param value
     * @return
     */
    public static String toName(int value) {
        for (WorkflowStepTypeEnum c : WorkflowStepTypeEnum.values()) {
            if (c.toValue() == value) {
                return c.name;
            }
        }
        return String.valueOf(value);
    }

    /**
     * 转换为数值
     * @return
     */
    public int toValue() {
        return value;
    }

    public String toValueString() {
        return String.valueOf(value);
    }

    public static WorkflowStepTypeEnum toEnum(int value) {
        for (WorkflowStepTypeEnum e : WorkflowStepTypeEnum.values()) {
            if (e.toValue() == value) {
                return e;
            }
        }
        return null;
    }

    public static WorkflowStepTypeEnum toEnum(String string) {
        if (string != null) {
            try {
                return Enum.valueOf(WorkflowStepTypeEnum.class, string.trim());
            } catch (IllegalArgumentException ex) {
            }
        }
        return null;
    }

}
