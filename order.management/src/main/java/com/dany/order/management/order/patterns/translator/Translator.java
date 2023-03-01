package com.dany.order.management.order.patterns.translator;

@FunctionalInterface
public interface Translator<I, O> {
    O translate(I var1);
}
