package Lea.powers;

import Lea.Abstracts.CrosscodeCharacter;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GainSPNextTurnPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:GainSPPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS = new String[] {
        "Gain ",
        " SP next turn."
    };

    public GainSPNextTurnPower(AbstractCreature owner, int SPamount) {
        this.name = NAME;
        this.ID = "leacrosscode:GainSPPower";
        this.owner = owner;
        this.amount = SPamount;
        this.updateDescription();
        this.priority = 5;
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        if(owner.isPlayer){
            ((CrosscodeCharacter) owner).gainSP(amount);
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:GainSPPower"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("leacrosscode:GainSPPower");
        NAME = powerStrings.NAME;
    }
}
