package Lea.Abstracts;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

public class CrosscodeDamageInfo extends DamageInfo {

    public ArrayList<AbstractCard.CardTags> tagsInfo = new ArrayList<>();
    public CrosscodeDamageInfo(AbstractCreature damageSource, int base, ArrayList<AbstractCard.CardTags> tags) {
        super(damageSource, base);
        tagsInfo = tags;
    }

    public CrosscodeDamageInfo(AbstractCreature damageSource, int base, DamageType type, ArrayList<AbstractCard.CardTags> tags) {
        super(damageSource, base, type);
        tagsInfo = tags;
    }

    public CrosscodeDamageInfo(AbstractCreature damageSource, int base, DamageType type) {
        super(damageSource, base, type);
    }

    public CrosscodeDamageInfo(AbstractCreature owner, int base) {
        super(owner, base);
    }
}
