package org.machine.learning.c45.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.machine.learning.c45.core.C45Core;
import org.machine.learning.c45.model.AttributeNode;
import org.machine.learning.c45.utils.C45Utils;
import org.machine.learning.c45.utils.DecisionTreeUtils;

public class C45Client {

    public static void main(String[] args) throws IOException {
        new C45Client().start();
    }
    
    private void start() throws IOException {
        List<List<String>> rawData = DecisionTreeUtils.getTrainingData("./data/PlayGolf.txt");
        C45Utils.transformContinuouslyVariables(rawData);
        C45Core core = new C45Core();
        createDecisionTree(core, rawData);
    }
    
    private void createDecisionTree(C45Core core, List<List<String>> currentData) {
        Map<String, Double> maxIGRatioMap = core.maxInformationGainRatio(currentData);
        AttributeNode rootNode = new AttributeNode(maxIGRatioMap.keySet().iterator().next());
        setAttributeNodeStatus(core, currentData, rootNode);
        DecisionTreeUtils.showDecisionTree(rootNode, "");
    }
    
    /**
     * 设置特征属性节点的分支及子节点
     * 
     * @param core
     * @param currentData
     * @param node
     */
    private void setAttributeNodeStatus(C45Core core, List<List<String>> currentData, AttributeNode node) {
        List<String> attributeBranchList = DecisionTreeUtils.getAttributeBranchList(currentData, node.getAttributeName());
        int attributeIndex = DecisionTreeUtils.getAttributeIndex(currentData.get(0), node.getAttributeName());
        
        for (String attributeBranch : attributeBranchList) {
            List<List<String>> splitAttributeDataList = DecisionTreeUtils.splitAttributeDataList(currentData, attributeBranch, attributeIndex);
            buildDecisionTree(core, attributeBranch, splitAttributeDataList, node);
        }
    }
    
    /**
     * 构建 C4.5 决策树
     * 
     * @param core
     * @param attributeBranch
     * @param splitAttributeDataList
     * @param node
     */
    private void buildDecisionTree(C45Core core, String attributeBranch, List<List<String>> splitAttributeDataList, AttributeNode node) {
        Map<String, Double> maxIGRatioMap = core.maxInformationGainRatio(splitAttributeDataList);
        
        String attributeName = maxIGRatioMap.keySet().iterator().next();
        double maxIG = maxIGRatioMap.get(attributeName);
        if (maxIG == 0.0) {
            List<String> singleLineData = splitAttributeDataList.get(splitAttributeDataList.size() - 1);
            
            AttributeNode leafNode = new AttributeNode(singleLineData.get(singleLineData.size() - 1));
            leafNode.setLeaf(true);
            leafNode.setParentStatus(attributeBranch);
            node.addChildNodes(leafNode);
            return;
        }
        
        AttributeNode attributeNode = getNewAttributeNode(attributeName, attributeBranch, node);
        
        setAttributeNodeStatus(core, splitAttributeDataList, attributeNode);
    }
    
    private AttributeNode getNewAttributeNode(String attributeName, String attributeBranch, AttributeNode node) {
        AttributeNode attributeNode = new AttributeNode(attributeName);
        attributeNode.setParentStatus(attributeBranch);
        node.addChildNodes(attributeNode);
        
        return attributeNode;
    }
}
