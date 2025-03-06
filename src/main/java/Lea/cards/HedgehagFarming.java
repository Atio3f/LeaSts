package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.actions.InfiniteUpgradeAction;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//Permanently upgrade a card 2 times, remove the card from your deck. Exhaust
public class HedgehagFarming extends CrosscodeCard {
    public static final String ID = "leacrosscode:HedgehagFarming";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png"; //IMAGE A CHANGER
    private static final int COST = 3;
    private static final int UPGRADE_COST = 1;
    private static final int NUMBER_UPGRADE = 2;
    private static final int REMAINING_USE = 1;
    //private static final int UPGRADE_USE = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public HedgehagFarming() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE,CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("HedgehagFarming" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = NUMBER_UPGRADE;


        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Up une carte 2 fois de manière permanente
        Lea.logger.info("TEST 1 --------------------------------------------");
        addToBot(new SelectCardsInHandAction(1,"Choose a card to upgrade permanently 2 times",(list) -> addToBot(new InfiniteUpgradeAction(list,magicNumber))));
        //Supprime la carte du deck
        Lea.logger.info("TEST 2 --------------------------------------------");

        for (AbstractCard carteDeck : AbstractDungeon.player.masterDeck.group) {
            if (carteDeck.uuid == this.uuid) {
                AbstractDungeon.player.masterDeck.removeCard(carteDeck);
                break;
            }
        }
        Lea.logger.info("TEST 3 --------------------------------------------");

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HedgehagFarmingCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new HedgehagFarming();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
