package Lea.potions;

import Lea.cards.Bullet;
import Lea.cards.Dash;
import Lea.characters.Lea;
import Lea.powers.JoltPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ZapPotion extends AbstractPotion {
    public static final String POTION_ID = "leacrosscode:ZapPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("leacrosscode:ZapPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTION = potionStrings.DESCRIPTIONS;

    public ZapPotion() {
        super(potionStrings.NAME, "leacrosscode:ZapPotion", PotionRarity.COMMON, PotionSize.BOTTLE, PotionEffect.NONE, Color.WHITE, Lea.COBALT, (Color)null);
        this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
        initializeData();

        this.isThrown = true;
        this.targetRequired = true;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.name = NAME;
        this.description = DESCRIPTION[0] + this.potency + DESCRIPTION[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {

        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !target.isDeadOrEscaped() && !target.isPlayer) {
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new JoltPower(target, AbstractDungeon.player, this.potency), this.potency, true, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 5;
    }

    public AbstractPotion makeCopy() {
        return new ZapPotion();
    }

}
