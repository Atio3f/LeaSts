package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import Lea.powers.ProtectionPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.CaptainsWheel;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;

public class TurtleShell  extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 1, 0, 0};
    public static final String ID = "leacrosscode:TurtleShell";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);


    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;
    public TurtleShell(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.SHOP, LandingSound.MAGICAL, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.counter = 2;
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            --this.counter;

            if (this.counter > 0) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

                this.addToBot(new ApplyPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player, new ProtectionPower(AbstractDungeon.player, 2), 2, true, AbstractGameAction.AttackEffect.SHIELD));

            }else{
                this.grayscale = true;
            }
        }
    }

    public void onVictory() {
        this.counter = 2;
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new TurtleShell();
    }
}
