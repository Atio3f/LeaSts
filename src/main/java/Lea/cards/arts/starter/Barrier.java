package Lea.cards.arts.starter;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Barrier extends CrosscodeCard {
    public static final String ID = "leacrosscode:Barrier";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Hit&Block.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int BLOCK_VALUE = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int PROT_VALUE = 3;
    private static final int CONVERSION_VALUE = 2;
    private static final int UPGRADE_PLUS_CONV = 1;
    private static final int SP_COST = 3;
    private static final int SP_GAIN = 0;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.NEUTRAL;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public Barrier() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            customEnums.COMBAT_ART_STARTER, CardTarget.SELF, SP_COST, SP_GAIN);
        logger.info("Barrier" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.block=this.baseBlock = BLOCK_VALUE;
        this.magicNumber = this.baseMagicNumber = CONVERSION_VALUE;
        tags.add(customEnums.NEUTRAL);
        tags.add(customEnums.SHIELD);
        tags.add(customEnums.COMBAT_ART);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,
            p, this.block));
        addToBot(new ApplyPowerAction(p,
            p, new ProtectionPower(p, this.PROT_VALUE), this.PROT_VALUE, true, AbstractGameAction.AttackEffect.SHIELD));
        addToBot(new GainBlockAction(p,
            p, this.block));

        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("BarrierCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new Barrier();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_CONV);
        }
    }
}

