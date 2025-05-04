package Lea.cards.curses;

import Lea.Abstracts.CrosscodeCard;
import Lea.cards.consumables.BoltDrink;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MemoryLost extends CrosscodeCard {
    public static final String ID = "leacrosscode:MemoryLost";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = -2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int NBR_EXHAUST = 1; //Affecte le nombre de cartes exhaust


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public MemoryLost() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.CURSE, CardColor.CURSE,
            CardRarity.SPECIAL, CardTarget.NONE, SP_COST, SP_GAIN);
        logger.info("MemoryLost" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = this.NBR_EXHAUST;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
        }

    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;

        //Avoid to exhaust itself
        addToBot(new DiscardSpecificCardAction(this));

        if (AbstractDungeon.player.hand.size() > 0) {
            addToBot(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber, true));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("MemoryLostCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new MemoryLost();
    }

    @Override
    public void upgrade() {
    }

}
