package Lea.Abstracts;

import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrontTargeting extends TargetingHandler<AbstractCreature>{

    private AbstractCreature hovered = null; //Les hovers ne servent à rien puisque l'on cible l'ennemi le + proche

    public static AbstractCreature getTarget(AbstractCard card) {
        AbstractMonster cible = null ;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                cible = m;    //On marque le 1er ennemi encore en vie
                break;
            }
        }

        return cible;
    }

    //We don't need it because the target will be the front ennemi
    @Override
    public void updateHovered() {
        hovered = null;
/*
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                hovered = m;    //On marque le 1er ennemi encore en vie
                break;
            }
        }
*/

    }

    @Override
    public AbstractCreature getHovered() {
        return hovered;
    }

    @Override
    public void clearHovered() {
        hovered = null;
    }


    @Override
    public boolean hasTarget() {
        return hovered != null;
    }
}
