package Lea.powers.powereffect;

import Lea.cards.Bullet;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.LeaEnum;
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
    public static final String POWER_ID = "LimitlessSpamPower";
    public static final String NAME = "Limitless Spam";
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
            if(lastCardUsed.hasTag(customEnums.BULLET)){
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount)));
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, amount)));
            }
        }
        return damageAmount;
    }
    /* NE MARCHAIT PAS
    @Override
    public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        Lea.logger.info("Carte par pouvoir : "+lastCardUsed+"------------------" +  AbstractDungeon.player.cardInUse);

        if (info.owner != null && info.owner.isPlayer && AbstractDungeon.player.cardInUse != null && AbstractDungeon.player.cardInUse.tags.contains(customEnums.BULLET)) {
            Lea.logger.info("BLABLABLA : "+AbstractDungeon.player.cardInUse+"------------------" + " AMOUNT : "+this.amount);
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount)));
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, amount)));
        }
    }*/



}
