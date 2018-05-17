package com.zc.base.core.collections.tree;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class TreeFactory<T> {
    public Tree<T> buildTree(Collection<T> allElements, String idProperty, Object rootId, String pidProperty)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<T> allElementsCopy = new ArrayList();
        allElementsCopy.addAll(allElements);
        T rootElement = getConllection(allElementsCopy, idProperty, rootId);
        Tree<T> tree = createTree(rootElement);
        addChildNode(tree.getRoot(), allElementsCopy, idProperty, pidProperty);
        return tree;
    }


    T getConllection(Collection<T> elements, String idProperty, Object idValue)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (T element : elements) {
            if (idValue.equals(PropertyUtils.getProperty(element, idProperty))) {
                elements.remove(element);
                return element;
            }
        }
        return null;
    }


    protected List<T> getChildConllection(Collection<T> elements, String pidProperty, Object idValue)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ArrayList<T> childElements = new ArrayList();
        for (T element : elements) {
            if (idValue.equals(PropertyUtils.getProperty(element, pidProperty))) {
                childElements.add(element);
            }
        }

        for (T element : childElements) {
            elements.remove(element);
        }
        return childElements;
    }

    protected void addChildNode(TreeNode<T> treeNode, Collection<T> elements, String idProperty, String pidProperty) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<T> childElements = getChildConllection(elements, pidProperty, PropertyUtils.getProperty(treeNode.getElement(), idProperty));
        for (T childElement : childElements) {
            treeNode.addElement(childElement);
        }
        Collection<TreeNode<T>> childNodes = treeNode.getChildren();
        for (TreeNode<T> childNode : childNodes) {
            addChildNode(childNode, elements, idProperty, pidProperty);
        }
    }

    protected abstract Tree<T> createTree(T paramT);
}
