package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.actions.CrosscodeDiscardPileToHandAction;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Flashback extends CrosscodeCard {
    public static final String ID = "leacrosscode:Flashback";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png"; //IMAGE A CHANGER
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int CARD_DRAW = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Flashback() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON,CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Flashback" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;


        tags.add(customEnums.NEUTRAL);
        this.isEthereal = true;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new CrosscodeDiscardPileToHandAction(this.magicNumber, -1, false));   //Il manque la partie où elle perd en coût

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("FlashbackCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Flashback();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
