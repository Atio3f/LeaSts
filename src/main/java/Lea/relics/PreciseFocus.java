package Lea.relics;

import Lea.Abstracts.CrosscodeRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;

//Target increases the damage a lot more
public class PreciseFocus extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{0, 0, 0, 1};
    public static final String ID = "leacrosscode:PreciseFocus";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public PreciseFocus(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER Changer pour FocusSenses plus tard
            RelicTier.COMMON, LandingSound.MAGICAL, elements);
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;

    }
}
