package Lea.cards.curses;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Shocked extends CrosscodeCard {
    public static final String ID = "leacrosscode:Shocked";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A CHANGER
    private static final int COST = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;


    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Shocked() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.CURSE, CardColor.CURSE,
            CardRarity.SPECIAL, CardTarget.NONE, SP_COST, SP_GAIN);
        logger.info("Shocked" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player, 1, true), 1));
        }

    }

    /*
    @Override
    public void triggerWhenDrawn() {
        if (!AbstractDungeon.player.hasPower(CannotPlayAttacksPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CannotPlayAttacksPower()));
        }
    }
    */

    @Override
    public AbstractCard makeCopy() {
        logger.info("ShockedCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Shocked();
    }

    @Override
    public void upgrade() {
    }

}

