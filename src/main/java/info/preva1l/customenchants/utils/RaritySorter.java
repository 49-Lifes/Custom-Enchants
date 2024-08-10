package info.preva1l.customenchants.utils;

import info.preva1l.customenchants.enchants.EnchantReference;

import java.util.Comparator;

public class RaritySorter implements Comparator<EnchantReference> {
    @Override
    public int compare(EnchantReference o1, EnchantReference o2) {
        return o1.enchant().getRarity().compareTo(o2.enchant().getRarity());
    }
}
