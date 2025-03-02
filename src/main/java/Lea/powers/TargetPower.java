package Lea.powers;

import Lea.Abstracts.CrosscodeDamageInfo;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class TargetPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:TargetPower";
    public static final String NAME = "Target";
    public static final String[] DESCRIPTIONS = new String[] {
        "When hit by ",
        " BULLET, take 2 more",
        " damage."
    };

    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 1.5F; //Comme pour Vulnerable à changer

    public TargetPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/TargetPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
        //On ne peut appliquer l'effet qu'à 1 seul ennemi
        if(!owner.isPlayer){
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped() && m.hasPower("leacrosscode:TargetPower") && m != owner) {
                    m.powers.removeIf(p -> p.ID.equals("leacrosscode:TargetPower"));
                }
            }
        }
    }

    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:TargetPower"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "leacrosscode:TargetPower", 1));
            }
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return damage; //Si il n'y a pas d'indications du type d'attaque on ne change rien des dégâts
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if (card.tags.contains(customEnums.BULLET)) {
            return this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog") ? damage + 3 : damage + 2;
        } else {
            return damage;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] +
            this.amount + DESCRIPTIONS[2];
    }
}
