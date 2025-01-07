package Lea.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BurnPower  extends AbstractPower {
    public static final String POWER_ID = "BurnPower";
    public static final String NAME = "Burn";
    public static final String[] DESCRIPTIONS = new String[] {
        "At the end of your",
        " turn, take 1% damage ",
        " per stack. When hit by ",
        " MELEE attack take 10% ",
        " more damage/stack "
    };

    private boolean justApplied = false;
    private static final float EFFECTIVENESS = 0.1F; //Comme pour Vulnerable
    private AbstractCreature source = null;

    public BurnPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/BurnPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public BurnPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/BurnPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

}
