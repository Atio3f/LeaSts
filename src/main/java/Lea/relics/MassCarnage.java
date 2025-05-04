package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.blue.Equilibrium;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;

public class MassCarnage extends CrosscodeRelic {

    private static Integer[] elements = new Integer[]{1, 0, 0, 0};
    public static final String ID = "leacrosscode:MassCarnage";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public MassCarnage(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.UNCOMMON, LandingSound.MAGICAL, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 3)));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
        }

    }

}
