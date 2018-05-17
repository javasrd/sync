package com.zc.base.core.collections.tree.array;

import com.zc.base.core.collections.tree.Tree;
import com.zc.base.core.collections.tree.TreeFactory;

public class ArrayTreeFactory<T> extends TreeFactory<T> {
    protected Tree<T> createTree(T rootElement) {
        return new ArrayTree(rootElement);
    }
}
