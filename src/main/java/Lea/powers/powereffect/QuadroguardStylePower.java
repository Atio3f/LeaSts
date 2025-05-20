package Lea.powers.powereffect;

import Lea.enums.customEnums;
import Lea.powers.ProtectionPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QuadroguardStylePower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:QuadroguardStylePower";
    public static final String NAME = "Quadroguard Style";
    public static final String[] DESCRIPTIONS = new String[] {
        "Protection are divide by ",
        " at the start of your turn."
    };

    private boolean justApplied = false;


    public QuadroguardStylePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");    //IMAGE A CHANGER
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
