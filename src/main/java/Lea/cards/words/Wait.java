package Lea.cards.words;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wait extends CrosscodeCard {
    public static final String ID = "leacrosscode:Wait";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Wait.png";
    private static final int COST = 1;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_PLUS_CARD = 3;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int ENERGY_GAIN = 2;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Wait() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, AbstractCardEnum.FRONT, SP_COST, SP_GAIN);
        logger.info("WAIT" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;


        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.ENERGY_GAIN));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("WAITCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Wait();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_CARD);
        }
    }
}
