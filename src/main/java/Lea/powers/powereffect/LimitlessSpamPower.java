package Lea.powers.powereffect;

import Lea.characters.Lea;
import Lea.enums.customEnums;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LimitlessSpamPower  extends AbstractPower {
    public static final String POWER_ID = "BurnPower";
    public static final String NAME = "Burn";
    public static final String[] DESCRIPTIONS = new String[] {
        "Each time you hit with a bullet, gain ",
        " temp Strength."
    };

    private boolean justApplied = false;
    private AbstractCreature source = null;
    private AbstractCard lastCardUsed = null;

    public LimitlessSpamPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public LimitlessSpamPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 20;
        this.img = new Texture("img/Lea/powers/LimitlessSpamPower.png");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }



    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner.isPlayer && AbstractDungeon.player.cardInUse != null) {
            lastCardUsed = AbstractDungeon.player.cardInUse;  // Stocke la carte utilisée
            Lea.logger.info("nouvelle cible : "+lastCardUsed);
        }
        return damageAmount;
    }
    @Override
    public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        Lea.logger.info("Cible pouvoir : "+lastCardUsed+"------------------");
        if (info.owner != null && info.owner.isPlayer && lastCardUsed != null && lastCardUsed.tags.contains(customEnums.BULLET)) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, amount)));
        }
    }



}
