package Lea.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChillPower  extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:ChillPower";
    public static final String NAME = "Chill";
    public static final String[] DESCRIPTIONS = new String[] {
        "DESC A CHANGER When hit by ",
        " Vulnerable and ",
        " Weak to the attacker."
    };

    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 1.5F; //Comme pour Vulnerable
    private AbstractCreature source = null;

    public ChillPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/ChillPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public ChillPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/ChillPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    @Override
    public void atStartOfTurn(){
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount < 2) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:ChillPower"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "leacrosscode:ChillPower", 1));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
