package Lea.powers.powereffect;

import Lea.enums.customEnums;
import Lea.powers.JoltPower;
import Lea.powers.MarkPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class StaticAuraPower extends AbstractPower{
    public static final String POWER_ID = "leacrosscode:StaticAuraPower";
    public static final String NAME = "Static Aura";
    public static final String[] DESCRIPTIONS = new String[] {
        "At the start of your turn, apply ",
        " Jolt to ALL ennemies."
    };

    private boolean justApplied = false;


    public StaticAuraPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");    //IMAGE A CHANGER
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    @Override
    public void atStartOfTurn() {
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters){
            if(!monster.isDeadOrEscaped()) {
                addToBot(new ApplyPowerAction(monster, owner, new JoltPower(monster, owner, amount), amount, true));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}

