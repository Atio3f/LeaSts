package Lea.relics;

import Lea.Abstracts.CrosscodeCharacter;
import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class SpheromancerHeart extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{2, 2, 2, 2};
    public static final String ID = "SpheromancerHeart";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    /*public static final String[] DESCRIPTIONS = new String[] {
        "Give you 3SP at the start of each fight.",
        "+2 of each element."

    };*/

    public SpheromancerHeart(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png"),
            RelicTier.STARTER, LandingSound.CLINK, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }


    @Override
    public void atBattleStart() {
        if(AbstractDungeon.player instanceof CrosscodeCharacter){
            ((CrosscodeCharacter)AbstractDungeon.player).gainSP(3);
            super.addElementsValue((CrosscodeCharacter)AbstractDungeon.player);
        }else{
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}
