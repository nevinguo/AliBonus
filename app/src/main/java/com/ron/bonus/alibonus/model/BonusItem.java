package com.ron.bonus.alibonus.model;

import android.text.TextUtils;

import java.util.Objects;

public class BonusItem {

    private String label = "unknown";
    private String bonus;

    public BonusItem(String bonus, String label) {
        this.bonus = bonus;
        if (!TextUtils.isEmpty(label)) {
            this.label = label;
        }
    }

    public String getLabel(){
        return label;
    }

    public String getBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusItem bonusItem = (BonusItem) o;
        return Objects.equals(bonus, bonusItem.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonus);
    }
}
