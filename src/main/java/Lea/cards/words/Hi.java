package Lea.cards.words;

import Lea.Abstracts.CrosscodeCard;
import Lea.actions.CrosscodeDrawPileToHandAction;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hi extends CrosscodeCard {
    public static final String ID = "leacrosscode:Hi";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";
    private static final int COST = 2;
    private static final int CARD_DRAW = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int ENERGY_REDUCTION = 1;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Hi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, CardTarget.SELF, SP_COST, SP_GAIN);    //pê Rare jsp encore pour la rareté de certaines cartes
        logger.info("Hi" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;


        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CrosscodeDrawPileToHandAction(this.magicNumber, -1, -1, true));//A CHANGER POUR LA PIOCHE
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("HiCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Hi();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

