package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhantomDash extends CrosscodeCard {
    public static final String ID = "leacrosscode:PhantomDash";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png";
    private static final int COST = 1;
    private static final int BLUR_AMT = 1;
    private static final int UPGRADE_PLUS_BLUR = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int BLOCK_VALUE = 3;
    //private static final int UPGRADE_BLOCK = 1;
    private static final int BLOCK_GAINED_PER_USE = 1;
    private static final int WAVE_COST = 1;

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public PhantomDash() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.UNCOMMON, CardTarget.SELF, SP_COST, SP_GAIN, WAVE_COST);
        logger.info("PhantomDash" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        this.magicNumber = this.baseMagicNumber = BLUR_AMT;
        this.misc = BLOCK_VALUE;
        this.baseBlock = this.misc;
        tags.add(customEnums.DASH);
        tags.add(customEnums.WAVE);
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlurPower(p, this.magicNumber)));
        this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, BLOCK_GAINED_PER_USE));
        super.use(p, m);
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("PhantomDashCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new PhantomDash();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_BLUR);
        }
    }
}
