package org.machine.learning.c45.utils;

import java.util.Comparator;

import org.machine.learning.c45.model.ContinuouslyVariableUnit;

/**
 * <p>
 * 通过连续变量单元的值进行比较两个连续变量单元
 * </p>
 * Create Date: 2016年6月23日
 * Last Modify: 2016年6月23日
 * 
 * @author <a href="http://weibo.com/u/5131020927">Q-WHai</a>
 * @see <a href="http://blog.csdn.net/lemon_tree12138">http://blog.csdn.net/lemon_tree12138</a>
 * @version 0.0.1
 */
public class ContinuouslyVariableUnitComparatorByValue implements Comparator<ContinuouslyVariableUnit> {

    @Override
    public int compare(ContinuouslyVariableUnit unit1, ContinuouslyVariableUnit unit2) {
        return unit1.getValue() - unit2.getValue();
    }

}
