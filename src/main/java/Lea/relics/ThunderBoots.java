package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import Lea.powers.JoltPower;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ThunderBoots extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 4, -3};
    public static final String ID = "leacrosscode:ThunderBoots";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public ThunderBoots(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.MAGICAL, elements);  //Rareté à changer pê jsp
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }
    @Override
    public void atBattleStart() {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped() ) {
                this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new JoltPower(monster, AbstractDungeon.player, 4), 4, true));
            }
        }
        super.atBattleStart();
    }

}

