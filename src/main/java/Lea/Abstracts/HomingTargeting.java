package Lea.Abstracts;

import Lea.characters.Lea;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//Sert à réaliser le ciblage de la plupart des BULLET (ennemi random sauf si un ennemi a target dans ce cas c'est l'ennemi ciblé ou l'ennemi avec target le plus près
public class HomingTargeting extends TargetingHandler<AbstractCreature>{

    private static AbstractCreature hovered = null;



    public static AbstractCreature getTarget(AbstractCard card) {
        return getTarget();
    }
    public static AbstractCreature getTarget() {
        AbstractCreature cible = null ;
        if(hovered == null || hovered.isDeadOrEscaped() || !hovered.hasPower("leacrosscode:TargetPower"))
            for (AbstractCreature m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped() && m.hasPower("leacrosscode:TargetPower")) {
                    cible = m;    //On marque le 1er ennemi encore en vie
                    break;
                }
            }
        else{
            Lea.logger.info("HOVERED TROUVE" + hovered);
            cible = hovered;
        }
        Lea.logger.info("CIBLE" + cible);
        hovered = cible;
        return cible;
    }

    @Override
    public void updateHovered() {
        //hovered = null;
        /*
        AbstractDungeon.player.hb.update();

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                m.hb.update();
                if (m.hb.hovered && m.hasPower("leacrosscode:TargetPower")) {
                    hovered = m;
                    break;
                }
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

    //Keyboard support is entirely optional, but this is an example based on how the basegame implements it
    @Override
    public void setDefaultTarget() {
        hovered = getTarget();
    }
}
