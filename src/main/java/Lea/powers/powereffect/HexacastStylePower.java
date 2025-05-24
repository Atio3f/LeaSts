package Lea.powers.powereffect;

import Lea.enums.customEnums;
import Lea.powers.MarkPower;
import Lea.powers.ProtectionPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class HexacastStylePower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:HexacastStylePower";
    public static final String NAME = "Hexacast Style";
    public static final String[] DESCRIPTIONS = new String[] {
        "Apply ",
        " Mark to a random ennemi when you play a BULLET. Protection don't protect you anymore and instead gives you Block."
    };

    private boolean justApplied = false;


    public HexacastStylePower(AbstractCreature owner, int amount) {
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
        if(owner.isPlayer && card.hasTag(customEnums.BULLET)){
            AbstractMonster target = AbstractDungeon.getRandomMonster();
            addToBot(new ApplyPowerAction(target,
                owner, new MarkPower(target, owner, amount), amount, true, AbstractGameAction.AttackEffect.NONE));

        }
        super.onPlayCard(card, m);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
