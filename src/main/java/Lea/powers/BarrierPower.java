package Lea.powers;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
//Convert prot into block at the start of your turn
public class BarrierPower extends AbstractPower {
    public static final String POWER_ID = "leacrosscode:BarrierPower";
    public static final String NAME = "Barrier";
    public static final String[] DESCRIPTIONS = new String[] {
        "Transform your Block into ",
        " Block at the start of your turn."
    };

    private boolean justApplied = false;

    public BarrierPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/TargetPower.png");   //IMAGE A CHANGER
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }

    }

    @Override
    public void atStartOfTurn() {
        int blockAmt = owner.hasPower("leacrosscode:ProtectionPower") ? owner.getPower("leacrosscode:ProtectionPower").amount * amount : 0;
        addToBot(new GainBlockAction(owner, blockAmt));
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "leacrosscode:BarrierPower"));
        }
    }

    @Override
    public void updateDescription() {
        int blockAmt = owner.hasPower("leacrosscode:ProtectionPower") ? owner.getPower("leacrosscode:ProtectionPower").amount * amount : 0;
        this.description = DESCRIPTIONS[0] + blockAmt + DESCRIPTIONS[0];
    }
}
