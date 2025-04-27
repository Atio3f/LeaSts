package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Patience extends CrosscodeCard {
    public static final String ID = "leacrosscode:Patience";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png";
    private static final int COST = 2;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 4;
    private static final int UPGRADE_PLUS_SP_GAIN = 1;

    private static final int BLOCK_VALUE = 12;
    private static final int UPGRADE_BLOCK = 2;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Patience() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.BASIC, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Patience" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        //this.magicNumber = this.baseMagicNumber = CARD_DRAW;
        this.baseBlock = BLOCK_VALUE;

        tags.add(customEnums.DASH);
        tags.add(customEnums.NEUTRAL);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("PatienceCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Patience();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
            this.SP_Gain += UPGRADE_PLUS_SP_GAIN;
        }
    }
}
