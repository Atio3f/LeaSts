package Lea.powers;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class MarkPower  extends AbstractPower {
    public static final String POWER_ID = "MarkPower";
    public static final String NAME = "Mark";
    public static final String[] DESCRIPTIONS = new String[] {
        "Increases damage from bullets by ",
        "%. NL Lose 25% of your Mark at the end of your turn(min 1)."
    };

    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 0.1F; //Comme pour Vulnerable
    private AbstractCreature source = null;

    public MarkPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/MarkPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public MarkPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/MarkPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }
    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount < 2) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "MarkPower"));
            } else {
                if (this.amount < 4){
                    this.addToBot(new ReducePowerAction(this.owner, this.owner, "MarkPower", 1));
                }
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "MarkPower", this.amount / 4));
            }
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return damage; //Si il n'y a pas d'indications du type d'attaque on ne change rien des dégâts
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if (card.tags.contains(customEnums.BULLET)) {
            Lea.logger.info("MARK DAMAGE BOOST : "+ (damage + (damage * EFFECTIVENESS * amount))+ " base damage : "+ damage);
            return damage + (damage * EFFECTIVENESS * amount);
        } else {
            return damage;
        }
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.amount * 10) + DESCRIPTIONS[1];
    }

}
