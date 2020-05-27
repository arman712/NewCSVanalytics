package com.company.newCSVanalytics.collections;

import java.util.Comparator;
import java.util.TreeSet;

public class ProductTreeSet<E> extends TreeSet<E> {

    private long limit;
    public ProductTreeSet(Comparator<? super E> comparator, long limit) {
        super(comparator);
        this.limit = limit;
    }

    @Override
    public boolean add(E e) {

        boolean added = super.add(e);
        if (size() > limit) {
             pollLast();
            return added;
        }
        return added;
    }

}
