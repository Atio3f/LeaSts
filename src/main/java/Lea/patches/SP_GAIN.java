package Lea.patches;

import Lea.Abstracts.CrosscodeCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SP_GAIN extends DynamicVariable {
    @Override
    public String key() {
        return "SP_G";
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return value(card) != baseValue(card);
        // Set to true if the value is modified from the base value.
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        // Do something such that isModified will return the value v.
        // This method is only necessary if you want smith upgrade previews to function correctly.
    }

    @Override
    public int value(AbstractCard card) {
        return ((CrosscodeCard)card).getSP_Gain();
        // What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((CrosscodeCard)card).BASESP_Gain ;
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {

        return ((CrosscodeCard) card).upgraded && this.isModified(card);
        // Should return true if the card was upgraded and the value was changed
    }

}
