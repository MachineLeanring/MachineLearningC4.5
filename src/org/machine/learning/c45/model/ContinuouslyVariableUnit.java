package org.machine.learning.c45.model;

/**
 * <p>
 * 连续变量单元
 * 此类用于保存一个属性的连续变量，以及此变量下的结果
 * 设计此类的目的在于方便计算连续变量的阈值
 * </p>
 * Create Date: 2016年6月23日
 * Last Modify: 2016年6月23日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.0.1
 */
public class ContinuouslyVariableUnit {

    private int value = 0;
    private String classify = "";
    
    public ContinuouslyVariableUnit() {
    }
    
    public ContinuouslyVariableUnit(int value, String classify) {
        this.value = value;
        this.classify = classify;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
    
    @Override
    public String toString() {
        return "[" + value + ", " + classify + "]";
    }
}
