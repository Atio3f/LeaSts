package Lea.potions;

import Lea.cards.Bullet;
import Lea.cards.Dash;
import Lea.characters.Lea;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PanicPotion extends AbstractPotion {
    public static final String POTION_ID = "leacrosscode:PanicPotion";
    private static final PotionStrings potionStrings;

    public PanicPotion() {
        super(potionStrings.NAME, "PanicPotion", PotionRarity.UNCOMMON, PotionSize.SPIKY, PotionEffect.NONE, Color.WHITE, Lea.COBALT, (Color)null);
        this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency*2 + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractCard dash = new Dash();
        AbstractCard bullet = new Bullet();
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new MakeTempCardInHandAction(dash.makeStatEquivalentCopy(), this.potency*2));
            this.addToBot(new MakeTempCardInHandAction(bullet.makeStatEquivalentCopy(), this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new PanicPotion();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString("PanicPotion");
    }
}
