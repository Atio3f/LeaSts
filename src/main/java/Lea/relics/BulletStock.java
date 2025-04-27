package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import Lea.cards.Bullet;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BulletStock extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 0, 1};
    public static final String ID = "leacrosscode:BulletStock";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    private static Integer amtBullet = 2;

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public BulletStock(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }
    @Override
    public void atBattleStart() {
        Bullet b = new Bullet();
        this.addToBot(new MakeTempCardInHandAction(b, amtBullet));
        super.atBattleStart();
    }

}
