package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.enums.TypeDegats;
import Lea.enums.customEnums;
import Lea.patches.AbstractCardEnum;
import Lea.powers.JoltPower;
import Lea.powers.PolarityHackPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PolarityHack extends CrosscodeCard {
    public static final String ID = "leacrosscode:PolarityHack";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/VPRCharge.png";    //IMAGE A CHANGER
    private static final int COST = 1;
    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;
    private static final int UPGRADE_PLUS_SP_GAIN = 1;
    private static final int SHOCK_AMT = 2;
    private static final int UPGRAGE_PLUS_SHOCK = 2;

    private static final TypeDegats TYPE_DEGATS = TypeDegats.SHOCK;
    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public PolarityHack() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.SKILL, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, CardTarget.ENEMY, SP_COST, SP_GAIN);
        logger.info("PolarityHack" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");

        this.magicNumber = this.baseMagicNumber = SHOCK_AMT;


        tags.add(customEnums.SHOCK);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        addToBot(new ApplyPowerAction(m, p, new JoltPower(m, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new PolarityHackPower(m), 1, true, AbstractGameAction.AttackEffect.NONE));

        System.out.println("TYPE DEGATS " + this.damageTypeForTurn);
        super.use(p, m);

    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("PolarityHackCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new PolarityHack();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRAGE_PLUS_SHOCK);
            this.SP_Gain += UPGRADE_PLUS_SP_GAIN;
        }
    }
}
