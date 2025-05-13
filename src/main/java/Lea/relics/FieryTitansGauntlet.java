package Lea.relics;

import Lea.Abstracts.CrosscodeCard;
import Lea.Abstracts.CrosscodeRelic;
import Lea.cards.FieryHit;
import Lea.characters.Lea;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class FieryTitansGauntlet extends CrosscodeRelic {
    private static Integer[] elements = new Integer[]{4, -3, 0, 0};
    public static final String ID = "leacrosscode:FieryTitansGauntlet";
    private static RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = relicStrings.NAME;
    public static final String DESCRIPTION = relicStrings.DESCRIPTIONS[0];
    public static final String FLAVOR = relicStrings.FLAVOR;

    public FieryTitansGauntlet(){
        super(ID, new Texture("img/Lea/relics/SpheromancerHeart.png" ),//IMAGE A CHANGER
            RelicTier.UNCOMMON, LandingSound.SOLID, elements);    //Peut être Relique de boss carrément
        this.description = DESCRIPTION;
        this.flavorText = FLAVOR;
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
        Logger logger = LogManager.getLogger(Lea.class.getName());
        logger.info("FIERY TITANS GAUNTLET TEST" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        int i;
        for(i = masterDeck.size() - 1; i >= 0; --i) {
            AbstractCard card = masterDeck.get(i);
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE) && card.cardID!="leacrosscode:FieryHit") {
                logger.info("BOUCLE FIERY TITANS" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
                CrosscodeCard newHit = new FieryHit();  //Could be change when I will add different elemental hits
                //If the hit is upgraded we upgrade the new Fiery Hit obtained
                if(card.upgraded){
                    newHit.upgrade();
                }
                AbstractDungeon.player.masterDeck.removeCard(card);
                //Add a fiery hit
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(newHit, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));

            }
        }
        super.onObtainCard(c);
    }
}

