package Lea.cards;

import Lea.Abstracts.CrosscodeCard;
import Lea.characters.Lea;
import Lea.patches.AbstractCardEnum;
import Lea.powers.ProtectionPower;
import Lea.powers.powereffect.QuadroguardStylePower;
import Lea.powers.powereffect.TribladerStylePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuadroguardStyle extends CrosscodeCard {
    public static final String ID = "leacrosscode:QuadroguardStyle";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESC = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "img/Lea/cards/Dash.png"; //IMAGE A MODIFIER
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    private static final int SP_COST = 0;
    private static final int SP_GAIN = 0;

    private static final int PROTECTION_AMT = 3;
    private static final int PROT_DIVIDE = 2;   //With 2, prot are reduced by 2 each turn(amoun/2), with 4 by 4 (amoun/4) etc..

    public static final Logger logger = LogManager.getLogger(Lea.class.getName());
    public QuadroguardStyle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
            AbstractCard.CardType.POWER, AbstractCardEnum.LEA_COBALT,
            CardRarity.RARE, CardTarget.SELF, SP_COST, SP_GAIN); //Censé être légendaire on verra si c'est trop fort
        logger.info("QuadroguardStyle" + DESCRIPTION + "---------------------------------------------------------------------------------------------------------------------------------\n\n");
        magicNumber = baseMagicNumber = PROTECTION_AMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new QuadroguardStylePower(p, PROT_DIVIDE), PROT_DIVIDE, true, AbstractGameAction.AttackEffect.SHIELD));
        super.use(p, m);
    }

    @Override
    public AbstractCard makeCopy() {
        logger.info("QuadroguardStyleCOPY---------------------------------------------------------------------------------------------------------------------------------\n\n");
        return new QuadroguardStyle();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
