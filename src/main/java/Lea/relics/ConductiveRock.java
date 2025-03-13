package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class ConductiveRock  extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 5, -2};
    public static final String ID = "leacrosscode:ConductiveRock";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public ConductiveRock(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.RARE, LandingSound.MAGICAL, elements);    //Peut être Relique de boss carrément
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }
}
