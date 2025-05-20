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

public class PentafistStylePower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:PentafistStylePower";
    public static final String NAME = "Pentafist Style";
    public static final String[] DESCRIPTIONS = new String[] {
        "Each time you use a dash, gain ",
        " Protection."
    };

    private boolean justApplied = false;


    public PentafistStylePower(AbstractCreature owner, int amount) {
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
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(owner.isPlayer && card.hasTag(customEnums.DASH)){
            addToBot(new ApplyPowerAction(owner,
                owner, new ProtectionPower(owner, amount), amount, true, AbstractGameAction.AttackEffect.SHIELD));

        }
        super.onPlayCard(card, m);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
