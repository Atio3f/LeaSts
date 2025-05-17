package Lea.powers.powereffect;

import Lea.Abstracts.FrontTargeting;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class EmilienatorPower extends AbstractPower {
    public static final String POWER_ID = "EmilienatorPower";
    public static final String NAME = "Emilienator Help";
    public static final String[] DESCRIPTIONS = new String[] {
        "At the end of each turn, deal ",
        " melee damage to the front ennemi."
    };

    private boolean justApplied = false;
    private AbstractCreature source = null;
    private AbstractCard lastCardUsed = null;
    private int multiplier;
    public EmilienatorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.priority = 16;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");    //IMAGE A CHANGER
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public EmilienatorPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 16;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");    //IMAGE A CHANGER
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    @Override
    public void atEndOfRound() {
        AbstractCreature target = FrontTargeting.getTarget();
        if (target != null) {
            multiplier = 1;
            if(target.hasPower("leacrosscode:BurnPower")){
                multiplier += target.getPower("leacrosscode:BurnPower").amount * 0.1;
            }
            if (target.hasPower("Vulnerable")) {

                multiplier *= 1.5f;
            }

            addToBot(new DamageAction(target,
                new DamageInfo(target, this.amount * multiplier, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

    }
}
